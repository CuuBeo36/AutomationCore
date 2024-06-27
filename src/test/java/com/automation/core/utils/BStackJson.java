package com.automation.core.utils;

import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BStackJson {
    public static DesiredCapabilities getCapabilitiesFromJson(String filePath, String deviceName) {
        // Create DesiredCapabilities object
        DesiredCapabilities capabilities = new DesiredCapabilities();
        try {
            String content = new String(Files.readAllBytes(Path.of(filePath)));
            JSONObject devices = new JSONObject(content);
            JSONObject device = devices.getJSONObject(deviceName);

            // Use entrySet().iterator() to iterate over the device6
            Iterator<Map.Entry<String, Object>> it = device.toMap().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> pair = it.next();
                if (pair.getKey().equals("bstack:options")) {
                    Object value = pair.getValue();
                    JSONObject bstackOptions;
                    if (value instanceof JSONObject) {
                        bstackOptions = (JSONObject) value;
                    } else if (value instanceof HashMap) {
                        bstackOptions = new JSONObject((HashMap) value);
                    } else {
                        throw new IllegalArgumentException("Value is not a JSONObject or HashMap");
                    }
                    HashMap<String, Object> bstackMap = new HashMap<>(bstackOptions.toMap());
                    capabilities.setCapability(pair.getKey(), bstackMap);
                } else {
                    capabilities.setCapability(pair.getKey(), pair.getValue());
                }
            }

            // Now you can use the capabilities with BrowserStack
//            WebDriver driver = new RemoteWebDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);

            // Your test code goes here

//            driver.quit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return capabilities;
    }
}

