package com.github.acticfox.extension.register;

import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.github.acticfox.jpf.api.Extension;
import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * 
 * @Description: TODO
 * @author kfy Jun 22, 2022 2:01:08 PM
 * @version V1.0
 */
@Component
public class ExtensionBootstrap implements ApplicationContextAware {

    private Logger logger = LoggerFactory.getLogger(ExtensionBootstrap.class);

    @Resource
    private ExtensionRegister extensionRegister;

    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        Map<String, Object> extensionBeans = applicationContext.getBeansWithAnnotation(Extension.class);
        // 初始化本地扩展插件
        extensionBeans.values().forEach(extension -> extensionRegister.doRegistration((ExtensionPoint)extension));
        // 远程下载并初始化扩展插件
        extensionRegister.loadJspfPlugin();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
