package com.time.tracking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class FileReader {

    public static String receiveResourceString(String path) {
        try (Scanner sc = new Scanner(new FileInputStream(path))) {
            return sc.useDelimiter("\\A").next();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return "";
    }

    public static Properties receiveResourceProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(path));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return properties;
    }


}
