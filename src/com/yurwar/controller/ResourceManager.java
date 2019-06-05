package com.yurwar.controller;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public enum ResourceManager {
    USER_INTERFACE("resources.languageResources"),
    REGEXP("resources.regexpResources");
    private ResourceBundle resourceBundle;
    private String resourceName;

    ResourceManager(String resourceName) {
        this.resourceName = resourceName;
        resourceBundle = ResourceBundle.getBundle(this.resourceName,
                Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName,
                locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public Enumeration<String> getSetKey() {
        return resourceBundle.getKeys();
    }
}
