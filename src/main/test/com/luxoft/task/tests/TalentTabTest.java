package com.luxoft.task.tests;

import com.luxoft.test.pages.PricePage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author yborsch
 * @version 1.0
 * @since 29.04.2016
 */
public class TalentTabTest {
    private WebDriver driver = new ChromeDriver();
    private PricePage pricePage;

    @Before
    public void init() {
        driver.get("https://omsk.hh.ru/price");
        pricePage = new PricePage(driver);
        pricePage.getEvaluationOfTalentFrom();
    }

    @Test
    public void checkInput() {
        pricePage.updateCountOfTests(0, 100);
        Assert.assertEquals(65000, pricePage.parseProductPrice(0));

        pricePage.updateCountOfTests(1, 3);
        Assert.assertEquals(1950, pricePage.parseProductPrice(1));

        pricePage.updateCountOfTests(2, 1);
        Assert.assertEquals(2600, pricePage.parseProductPrice(2));
    }

    @Test
    public void addToCart() {
        pricePage.updateCountOfTests(0, 2);
        pricePage.addToCart(0);
        pricePage.updateCountOfTests(1, 1);
        pricePage.addToCart(1);
        pricePage.updateCountOfTests(2, 3);
        pricePage.addToCart(2);
        Assert.assertEquals(9750, pricePage.parseTotalFromCart());
    }

    @Test
    public void deleteFromCart() {
        pricePage.updateCountOfTests(0, 3);
        pricePage.addToCart(0);
        pricePage.updateCountOfTests(1, 10);
        pricePage.addToCart(1);
        Assert.assertEquals(1, pricePage.pushDeleteButtonOnCart(0));
        Assert.assertEquals(0, pricePage.pushDeleteButtonOnCart(0));
    }

    @After
    public void shutDown() {
        driver.close();
    }
}
