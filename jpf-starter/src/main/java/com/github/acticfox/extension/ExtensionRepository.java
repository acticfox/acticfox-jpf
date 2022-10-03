package com.github.acticfox.extension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.github.acticfox.jpf.api.ExtensionPoint;

/**
 * 
 * 租户实现扩展点容器，存储到HashMap
 * 
 * @Description: TODO
 * @author kfy Jun 23, 2022 2:01:54 PM
 * @version V1.0
 */
@Component
public class ExtensionRepository {

    public Map<ExtensionCoordinate, ExtensionPoint> getExtensionRepo() {
        return extensionRepo;
    }

    private Map<ExtensionCoordinate, ExtensionPoint> extensionRepo =
        new ConcurrentHashMap<ExtensionCoordinate, ExtensionPoint>();

}
