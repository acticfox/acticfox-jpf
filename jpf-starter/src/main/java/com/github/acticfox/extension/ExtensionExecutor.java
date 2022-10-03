package com.github.acticfox.extension;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.github.acticfox.extension.register.AbstractComponentExecutor;
import com.github.acticfox.jpf.api.BizScenario;

/**
 * 基于扩展器实现的执行器
 * 
 * @Description: TODO
 * @author kfy Jun 21, 2022 2:01:28 PM
 * @version V1.0
 */
@Component
public class ExtensionExecutor extends AbstractComponentExecutor {

    private Logger logger = LoggerFactory.getLogger(ExtensionExecutor.class);

    @Resource
    private ExtensionRepository extensionRepository;

    @Override
    protected <C> C locateComponent(Class<C> targetClz, BizScenario bizScenario) {
        C extension = locateExtension(targetClz, bizScenario);
        logger.debug("[Located Extension]: " + extension.getClass().getSimpleName());
        return extension;
    }

    /**
     * if the bizScenarioUniqueIdentity is "ali.tmall.supermarket"
     *
     * the search path is as below: 1、first try to get extension by "ali.tmall.supermarket", if get, return it. 2、loop
     * try to get extension by "ali.tmall", if get, return it. 3、loop try to get extension by "ali", if get, return it.
     * 4、if not found, try the default extension
     * 
     * @param targetClz
     */
    protected <Ext> Ext locateExtension(Class<Ext> targetClz, BizScenario bizScenario) {
        checkNull(bizScenario);

        logger.debug("BizScenario in locateExtension is : " + bizScenario.getUniqueIdentity());

        Ext extension =
            (Ext)extensionRepository.getExtensionRepo().get(new ExtensionCoordinate(targetClz, bizScenario));

        if (extension == null) {
            bizScenario = BizScenario.valueOf(BizScenario.DEFAULT_TENANT_ID);
            extension =
                (Ext)extensionRepository.getExtensionRepo().get(new ExtensionCoordinate(targetClz, bizScenario));
        }

        if (extension == null) {
            throw new RuntimeException("Can not find extension with ExtensionPoint: " + targetClz + " BizScenario:"
                + bizScenario.getUniqueIdentity());
        }

        return extension;

    }

    private void checkNull(BizScenario bizScenario) {
        if (bizScenario == null) {
            throw new IllegalArgumentException("BizScenario can not be null for extension");
        }
    }

}
