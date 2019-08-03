package com.time.tracking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Scanner;

public class FileReader {

    public static String receiveResourceString(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try (Scanner sc = new Scanner(Objects.requireNonNull(loader.getResourceAsStream(path)))) {
            return sc.useDelimiter("\\A").next();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "";
    }

    public static Properties receiveResourceProperties(String path) {
        Properties properties = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            properties.load(loader.getResourceAsStream(path));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }


}
