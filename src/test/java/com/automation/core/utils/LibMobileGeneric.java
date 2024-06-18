package com.automation.core.utils;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class LibMobileGeneric {
    public static void scrollDown(AppiumDriver driver) {
        // Get the size of screen.
        Dimension size = driver.manage().window().getSize();

// Find starty point which is at bottom side of screen.
        int starty = (int) (size.height * 0.80);

// Find endy point which is at top side of screen.
        int endy = (int) (size.height * 0.20);

// Find horizontal point where you wants to swipe. It is in middle of screen width.
        int startx = size.width / 2;

// Create object of Actions class.
        Actions action = new Actions(driver);

// Create a PointerInput object for touch interactions
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

// Create a sequence of actions that simulates the swipe.
        Sequence swipe = new Sequence(finger, 0);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startx, starty));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), startx, endy));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

// Perform the swipe
        driver.perform(Arrays.asList(swipe));
    }
    public static void waitForElementVisible(AppiumDriver driver, WebElement element, Duration duration) {
        WebDriverWait wait = new WebDriverWait(driver, duration);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
