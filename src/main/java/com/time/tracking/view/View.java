package view;

import java.util.Map;

public interface View {

    String getPageUrl();

    void addParameter(String key, Object value);

    void removeParameter(String key);

    Map<String, Object> getParameters();

    default View getView() {
        return this;
    }
}
