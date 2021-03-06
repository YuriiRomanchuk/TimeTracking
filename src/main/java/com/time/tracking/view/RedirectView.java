package com.time.tracking.view;

import java.util.Map;

public class RedirectView implements View {

    private View view;

    public RedirectView(View view) {
        this.view = view;
    }

    @Override
    public String getPageUrl() {
        return view.getPageUrl();
    }

    @Override
    public void addParameter(String key, Object value) {
        view.addParameter(key, value);
    }

    @Override
    public void removeParameter(String key) {
        view.removeParameter(key);
    }

    @Override
    public Map<String, Object> getParameters() {
        return view.getParameters();
    }

    @Override
    public View getView() {
        return view;
    }
}
