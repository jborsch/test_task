package com.luxoft.test.pages;

import com.luxoft.test.pages.block.Cart;
import com.luxoft.test.pages.block.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yborsch
 * @version 1.0
 * @since 29.04.2016
 */
public class PricePage extends PageBase {
    private WebDriver driver;

    private List<Product> productBlocs = new ArrayList<Product>();

    @FindBy(css = "div.HH-MainContent div.price-content div.flat-tabs div.nopaddings a:nth-child(4)")
    private WebElement talentTab;

    @FindBy(css = "div.HH-PriceCart")
    private WebElement cartElement;

    private Cart cart;

    private By bloc = By.cssSelector("li.flat-tabs__content.HH-PriceCart-Tab.g-expand div.price-countable-service");

    public PricePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PricePage getEvaluationOfTalentFrom() {
        talentTab.click();
        loadProductBloc();
        return new PricePage(driver);
    }

    private void loadProductBloc() {
        for (WebElement product : driver.findElements(bloc)) {
            Product pb = new Product(product);
            productBlocs.add(pb);
        }
    }

    private void updateCart() {
        if (cart == null) {
            cart = new Cart(driver, cartElement);
        } else {
            cart.updateCartState();
        }
    }

    public void updateCountOfTests(int numOfProduct, int number) {
        productBlocs.get(numOfProduct).updateCount(number);
    }

    public int parseProductPrice(int numOfProduct) {
        return productBlocs.get(numOfProduct).parsePrice();
    }

    public void addToCart(int numOfProduct) {
        productBlocs.get(numOfProduct).addToCart();
    }

    public int parseTotalFromCart() {
        updateCart();
        return cart.parseTotalSemFromCart();
    }

    @Deprecated
    private boolean checkProductContainsOnCart(int numOfProduct) {
        updateCart();
        return cart.contains(productBlocs.get(numOfProduct));
    }

    public int pushDeleteButtonOnCart(int numOfButton) {
        updateCart();
        cart.clickDeleteButton(numOfButton);
        return (int) cart.getItemsCount();
    }


}
