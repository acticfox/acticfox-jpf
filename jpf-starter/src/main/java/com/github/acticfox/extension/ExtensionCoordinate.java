package com.github.acticfox.extension;

import com.github.acticfox.jpf.api.BizScenario;

/**
 * Extension Coordinate(扩展坐标) is used to uniquely position an Extension
 * 
 * @author fanyong.kfy
 */
public class ExtensionCoordinate {

    private String extensionPointName;
    private String bizScenarioUniqueIdentity;

    // Wrapper
    private Class extensionPointClass;
    private BizScenario bizScenario;

    public Class getExtensionPointClass() {
        return extensionPointClass;
    }

    public BizScenario getBizScenario() {
        return bizScenario;
    }

    public static ExtensionCoordinate valueOf(Class extPtClass, BizScenario bizScenario) {
        return new ExtensionCoordinate(extPtClass, bizScenario);
    }

    public ExtensionCoordinate(Class extPtClass, BizScenario bizScenario) {
        this.extensionPointClass = extPtClass;
        this.extensionPointName = extPtClass.getName();
        this.bizScenario = bizScenario;
        this.bizScenarioUniqueIdentity = bizScenario.getUniqueIdentity();
    }

    /**
     * @param extensionPoint
     * @param bizScenario
     */
    public ExtensionCoordinate(String extensionPoint, String bizScenario) {
        this.extensionPointName = extensionPoint;
        this.bizScenarioUniqueIdentity = bizScenario;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((bizScenarioUniqueIdentity == null) ? 0 : bizScenarioUniqueIdentity.hashCode());
        result = prime * result + ((extensionPointName == null) ? 0 : extensionPointName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ExtensionCoordinate other = (ExtensionCoordinate)obj;
        if (bizScenarioUniqueIdentity == null) {
            if (other.bizScenarioUniqueIdentity != null)
                return false;
        } else if (!bizScenarioUniqueIdentity.equals(other.bizScenarioUniqueIdentity))
            return false;
        if (extensionPointName == null) {
            if (other.extensionPointName != null)
                return false;
        } else if (!extensionPointName.equals(other.extensionPointName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ExtensionCoordinate [extensionPointName=" + extensionPointName + ", bizScenarioUniqueIdentity="
            + bizScenarioUniqueIdentity + "]";
    }

}
