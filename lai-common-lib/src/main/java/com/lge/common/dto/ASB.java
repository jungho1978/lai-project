package com.lge.common.dto;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ComparisonChain;

public class ASB extends Base {
    public long id;
    public String className;
    public String actionName;

    public ASB(String versionName, String type, String desc, String packageName, String className,
            String actionName, String updatedBy) {
        super(versionName, type, desc, packageName, updatedBy);
        this.className = className;
        this.actionName = actionName;
    }

    public ASB(String versionName, String type, String desc, String packageName, String className,
            String actionName, String updatedBy, long id) {
        super(versionName, type, desc, packageName, updatedBy);
        this.id = id;
        this.className = className;
        this.actionName = actionName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    @Override public String toString() {
        return MoreObjects.toStringHelper(this).add("versionName", versionName).add("type", type)
                .add("desc", desc).add("packageName", packageName).add("className", className)
                .add("actionName", actionName).add("updatedBy", updatedBy).toString();
    }

    @Override public boolean equals(Object obj) {
        if (obj instanceof ASB) {
            ASB other = (ASB)obj;
            return ComparisonChain.start().compare(versionName, other.versionName)
                    .compare(type, other.type).compare(packageName, other.packageName)
                    .compare(className, other.className).compare(actionName, other.actionName)
                    .result() == 0;
        } else {
            return false;
        }
    }
}
