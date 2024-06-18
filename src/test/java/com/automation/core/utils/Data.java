package com.automation.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Data {
    private static Properties data = new Properties();

    public Data(String filePath) {
        try (InputStream input = new FileInputStream(filePath)) {
            // load a properties file
            data.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return data.getProperty(key);
    }
}
