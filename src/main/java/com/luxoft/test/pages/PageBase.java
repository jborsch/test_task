package com.luxoft.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author yborsch
 * @version 1.0
 * @since 29.04.2016
 */
public class PageBase {
    private static WebDriver driver;
    private WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        wait = initWait(10L);
    }

    public WebDriverWait initWait(long TIME) {
        return new WebDriverWait(driver, TIME);
    }
}

