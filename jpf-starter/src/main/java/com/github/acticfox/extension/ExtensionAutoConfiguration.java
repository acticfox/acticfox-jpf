package com.github.acticfox.extension;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.acticfox.extension.properties.JspfConfigProperties;
import com.github.acticfox.extension.register.ExtensionBootstrap;
import com.github.acticfox.extension.register.ExtensionRegister;
import com.github.acticfox.extension.util.ApplicationContextHelper;

/**
 * 
 * @Description: TODO
 * @author kfy Jun 23, 2022 2:00:19 PM
 * @version V1.0
 */
@EnableConfigurationProperties(JspfConfigProperties.class)
@Configuration
public class ExtensionAutoConfiguration {

    @Autowired
    private JspfConfigProperties jspfConfigProperties;

    @Bean
    @ConditionalOnMissingBean(ApplicationContextHelper.class)
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }

    @Bean(initMethod = "init")
    @ConditionalOnMissingBean(ExtensionBootstrap.class)
    public ExtensionBootstrap bootstrap() {
        return new ExtensionBootstrap();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionRepository.class)
    public ExtensionRepository repository() {
        return new ExtensionRepository();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionExecutor.class)
    public ExtensionExecutor executor() {
        return new ExtensionExecutor();
    }

    @Bean
    @ConditionalOnMissingBean(ExtensionRegister.class)
    public ExtensionRegister register() {
        return new ExtensionRegister(jspfConfigProperties);
    }

}
