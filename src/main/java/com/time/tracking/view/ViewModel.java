package com.time.tracking.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ViewModel implements View {

    private String pageUrl;
    private Map<String, Object> parameters = new HashMap<>();

    public ViewModel() {

    }

    public ViewModel(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    @Override
    public String getPageUrl() {
        return pageUrl;
    }

    @Override
    public void addParameter(String key, Object value) {
        parameters.put(key, value);
    }

    @Override
    public void removeParameter(String key) {
        parameters.remove(key);
    }

    @Override
    public Map<String, Object> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

}
