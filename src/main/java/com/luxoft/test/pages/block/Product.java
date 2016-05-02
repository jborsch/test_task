package com.luxoft.test.pages.block;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

/**
 * @author yborsch
 * @version 1.0
 * @since 29.04.2016
 */
public class Product {
    private final static By countInputSelector = By.cssSelector("div.price-countable-service__controls div.bloko-control-group__main input");
    private final static By addToCartButtonSelector = By.cssSelector("div.price-countable-service__controls div.bloko-control-group__minor button");
    private final static By titleSelector = By.cssSelector("h2");
    private final static By priceSelector = By.cssSelector("span.price-countable-service__cost span.HH-Price-CountableService-TotalCostLabel.price-countable-service__cost-amount");
    private String title;
    private int count = 0;

    private WebElement rootElement;
    private WebElement countInput;
    private WebElement addToCartButton;
    private WebElement priceElement;

    public Product(WebElement element) {
        rootElement = element;
        try {
            countInput = rootElement.findElement(countInputSelector);
            addToCartButton = rootElement.findElement(addToCartButtonSelector);
            title = rootElement.findElement(titleSelector).getText();
            priceElement = rootElement.findElement(priceSelector);
        } catch (NoSuchElementException nsee) {
            //Got incorrect root element.
        }

    }

    public int parsePrice() {
        return Integer.valueOf(priceElement.getText().replaceAll(" ", ""));
    }

    public int getCount() {
        return count;
    }

    public void updateCount(int count) {
        countInput.clear();
        countInput.sendKeys(String.valueOf(count));
        this.count = count;
    }

    public void addToCart() {
        addToCartButton.click();
    }
}
