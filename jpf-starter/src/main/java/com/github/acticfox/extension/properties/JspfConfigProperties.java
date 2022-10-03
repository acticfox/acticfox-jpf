package com.github.acticfox.extension.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @author kfy
 * @date 2022/08/19
 */
@ConfigurationProperties(prefix = "jspf")
@Data
public class JspfConfigProperties {

    private boolean enableLoadPlugin;

    private String pluginLocalPath;

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;
}
