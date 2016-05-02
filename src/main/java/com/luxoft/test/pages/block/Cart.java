package com.luxoft.test.pages.block;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author yborsch
 * @version 1.0
 * @since 29.04.2016
 */
public class Cart {
    private WebDriver driver;
    private final static By totalAmount = By.cssSelector("div.price-cart__total-summary span.HH-PriceCart-TotalCost-Actual");
    private final static By cartItem = By.cssSelector("li.price-cart__item");
    private final static By deleteButtons = By.cssSelector("small.HH-PriceCart-ItemRemover.link-switch");
    private WebElement rootElement;
    private WebElement total;
    private List<WebElement> items = new ArrayList<WebElement>();
    private List<WebElement> removeButtons = new ArrayList<WebElement>();

    public Cart(WebDriver driver, WebElement element) {
        this.driver = driver;
        rootElement = element;
        updateCartState();
    }

    public void updateCartState() {
        items.clear();
        removeButtons.clear();
        try {
            total = rootElement.findElement(totalAmount);
            items.addAll(rootElement.findElements(cartItem));
            removeButtons.addAll(rootElement.findElements(deleteButtons));
        } catch (NoSuchElementException nsee) {
            System.out.println("NSEE: Not found total.");

        }
    }

    public int parseTotalSemFromCart() {
        return Integer.valueOf(total.getText().replaceAll(" ", ""));
    }

    public boolean contains(Product product) {
        //TODO: cart item parse
        return true;
    }

    public void clickDeleteButton(int numOfButton) {
        removeButtons.get(numOfButton).click();
        new WebDriverWait(driver, 3, 1000);
        updateCartState();
    }

    public long getItemsCount() {
        updateCartState();
        return items.stream().filter((i) -> i.isDisplayed()).count();
    }


}
