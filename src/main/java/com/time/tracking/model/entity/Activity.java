package com.time.tracking.model.entity;

import java.util.Objects;

public class Activity {

    private int id;
    private String name;
    private String englishName;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id == activity.id &&
                Objects.equals(name, activity.name) &&
                Objects.equals(englishName, activity.englishName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, englishName);
    }
}
