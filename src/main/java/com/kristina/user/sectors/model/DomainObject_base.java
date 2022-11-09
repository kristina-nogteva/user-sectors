package com.kristina.user.sectors.model;

public abstract class DomainObject_base implements DomainObject {

    // Needed for JSF converters
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName());
        sb.append("@");
        sb.append(this.hashCode());
        sb.append("[id=");
        sb.append(this.getId());
        sb.append("]");
        return sb.toString();
    }
}
