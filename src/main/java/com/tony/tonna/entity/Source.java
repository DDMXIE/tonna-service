package com.tony.tonna.entity;

import java.util.List;

public class Source {
    private String SOURCE_ID;
    private String SOURCE_PATTERN;
    private List<Role> SOURCE_ROLES;

    public String getSOURCE_ID() {
        return SOURCE_ID;
    }

    public void setSOURCE_ID(String SOURCE_ID) {
        this.SOURCE_ID = SOURCE_ID;
    }

    public String getSOURCE_PATTERN() {
        return SOURCE_PATTERN;
    }

    public void setSOURCE_PATTERN(String SOURCE_PATTERN) {
        this.SOURCE_PATTERN = SOURCE_PATTERN;
    }

    public List<Role> getSOURCE_ROLES() {
        return SOURCE_ROLES;
    }

    public void setSOURCE_ROLES(List<Role> SOURCE_ROLES) {
        this.SOURCE_ROLES = SOURCE_ROLES;
    }
}
