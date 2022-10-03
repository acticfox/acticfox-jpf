package com.github.acticfox.extension.register;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ListObjectsV2Result;
import com.aliyun.oss.model.OSSObjectSummary;
import com.github.acticfox.extension.ExtensionCoordinate;
import com.github.acticfox.extension.ExtensionRepository;
import com.github.acticfox.extension.properties.JspfConfigProperties;
import com.github.acticfox.jpf.DefaultPluginManager;
import com.github.acticfox.jpf.JarPluginManager;
import com.github.acticfox.jpf.PluginWrapper;
import com.github.acticfox.jpf.api.BizScenario;
import com.github.acticfox.jpf.api.Extension;
import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * 实现扩展点实现注册
 * 
 * @Description: TODO
 * @author kfy Jun 22, 2022 11:27:15 AM
 * @version V1.0
 */
@Component
public class ExtensionRegister {

    private static Logger logger = LoggerFactory.getLogger(ExtensionRegister.class);

    private JspfConfigProperties jspfConfigProperties;

    @Resource
    private ExtensionRepository extensionRepository;

    // 所有扩展点实现必须以ExtPt结尾
    public final static String EXTENSION_EXTPT_NAMING = "ExtPt";

    public ExtensionRegister(JspfConfigProperties jspfConfigProperties) {
        this.jspfConfigProperties = jspfConfigProperties;

        if (jspfConfigProperties.isEnableLoadPlugin()) {
            Runnable runable = new Runnable() {
                public void run() {
                    loadJspfPlugin();
                    logger.info("刷新重新加载jspf plugin");
                }
            };
            long delay = 10; // 延迟执行时间（分）
            long period = 10; // 执行的时间间隔（分）
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
            service.scheduleAtFixedRate(runable, delay, period, TimeUnit.MINUTES);
        }
    }

    public void doRegistration(ExtensionPoint extensionObject) {
        Class<?> extensionClz = extensionObject.getClass();
        if (AopUtils.isAopProxy(extensionObject)) {
            extensionClz = ClassUtils.getUserClass(extensionObject);
        }
        Extension extensionAnn = AnnotationUtils.findAnnotation(extensionClz, Extension.class);
        BizScenario bizScenario = BizScenario.valueOf(extensionAnn.tenantId());
        ExtensionCoordinate extensionCoordinate =
            new ExtensionCoordinate(calculateExtensionPoint(extensionClz), bizScenario.getUniqueIdentity());
        ExtensionPoint preVal = extensionRepository.getExtensionRepo().put(extensionCoordinate, extensionObject);
        if (preVal != null) {
            throw new RuntimeException("Duplicate registration is not allowed for :" + extensionCoordinate);
        }
    }

    public void loadJspfPlugin() {
        if (!jspfConfigProperties.isEnableLoadPlugin()) {
            return;
        }
        List<Path> pathList = downLoadJspfPlugin();
        for (Path path : pathList) {
            DefaultPluginManager pluginManager = new JarPluginManager();
            // 初始化JAR扩展插件
            pluginManager.loadPlugin(path);
            List<PluginWrapper> pluginWrapperList = pluginManager.getPlugins();
            for (PluginWrapper pluginWrapper : pluginWrapperList) {
                String pluginId = pluginWrapper.getPluginId();
                String tenantId = pluginWrapper.getDescriptor().getTenantId();
                pluginManager.startPlugin(pluginId);
                List<Class<?>> extensionList = pluginManager.getExtensionClasses(pluginId);
                for (Class<?> targetClz : extensionList) {
                    ExtensionPoint extPoint = null;
                    try {
                        extPoint = (ExtensionPoint)targetClz.newInstance();
                    } catch (InstantiationException | IllegalAccessException e) {
                        logger.error("初始化插件扩展点出错tenant:{},扩展插件:{}", e, tenantId, pluginId);
                    }
                    doRegistrationJar(extPoint, tenantId);
                    logger.info("初始化成功jar插件 tenant:{},扩展插件:{}", tenantId, extPoint.getClass().getName());
                }
                // 停用插件
                pluginManager.stopPlugin(pluginId);
                // 卸载插件包
                pluginManager.unloadPlugin(pluginId);
            }

        }

    }

    public List<Path> downLoadJspfPlugin() {
        OSS ossClient = new OSSClientBuilder().build(jspfConfigProperties.getEndpoint(),
            jspfConfigProperties.getAccessKeyId(), jspfConfigProperties.getAccessKeySecret());
        String jarFileName = null;
        List<Path> pathList = new ArrayList<Path>();
        try {
            // 列举bucket文件
            ListObjectsV2Result result = ossClient.listObjectsV2(jspfConfigProperties.getBucketName());
            List<OSSObjectSummary> ossObjectSummaries = result.getObjectSummaries();
            String pluginLocalPath = jspfConfigProperties.getPluginLocalPath();
            File file = new File(pluginLocalPath);
            // 自动创建目录
            if (!file.exists()) {
                file.mkdirs();// 目录不存在的情况下，创建目录。
            }

            File pluginFile = new File(pluginLocalPath);
            // 删除目录下所有扩展jar插件
            for (File jarFile : pluginFile.listFiles()) {
                jarFile.delete();
            }
            for (OSSObjectSummary s : ossObjectSummaries) {
                jarFileName = s.getKey();
                // 下载oss插件存储到本地
                String localFile = pluginLocalPath + jarFileName;
                ossClient.getObject(new GetObjectRequest(jspfConfigProperties.getBucketName(), jarFileName),
                    new File(localFile));
                pathList.add(Paths.get(localFile));
            }
        } catch (OSSException oe) {
            logger.info("oss 操作出错Error Message:{},Error Code:{}", oe.getErrorMessage(), oe.getErrorCode(), oe);
        } catch (ClientException ce) {
            logger.info("oss 操作出错", ce);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return pathList;
    }

    public void doRegistrationJar(ExtensionPoint extensionObject, String tenantId) {
        Class<?> extensionClz = extensionObject.getClass();
        if (AopUtils.isAopProxy(extensionObject)) {
            extensionClz = ClassUtils.getUserClass(extensionObject);
        }
        BizScenario bizScenario = BizScenario.valueOf(tenantId);
        ExtensionCoordinate extensionCoordinate =
            new ExtensionCoordinate(calculateExtensionPoint(extensionClz), bizScenario.getUniqueIdentity());
        ExtensionPoint preVal = extensionRepository.getExtensionRepo().put(extensionCoordinate, extensionObject);
        if (preVal != null) {
            throw new RuntimeException("Duplicate registration is not allowed for :" + extensionCoordinate);
        }
    }

    /**
     * @param targetClz
     * @return
     */
    private String calculateExtensionPoint(Class<?> targetClz) {
        Class<?>[] interfaces = ClassUtils.getAllInterfacesForClass(targetClz);
        if (interfaces == null || interfaces.length == 0)
            throw new RuntimeException("Please assign a extension point interface for " + targetClz);
        for (Class intf : interfaces) {
            String extensionPoint = intf.getSimpleName();
            if (extensionPoint.contains(EXTENSION_EXTPT_NAMING))
                return intf.getName();
        }
        throw new RuntimeException(
            "Your name of ExtensionPoint for " + targetClz + " is not valid, must be end of " + EXTENSION_EXTPT_NAMING);
    }

}
