package com.flipkart.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//button[contains(text(),'Add to Cart')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//a[contains(@href,'/viewcart')]")
    private WebElement viewCartButton;

    @FindBy(xpath = "//div[contains(@class,'cart-item')]")
    private List<WebElement> cartItems;

    @FindBy(xpath = "//button[contains(text(),'Remove')]")
    private WebElement removeButton;

    @FindBy(xpath = "//button[contains(text(),'Increase Quantity')]")
    private WebElement increaseQuantityButton;

    @FindBy(xpath = "//button[contains(text(),'Decrease Quantity')]")
    private WebElement decreaseQuantityButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
        System.out.println("✅ Product added to cart successfully.");
    }

    public void viewCart() {
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton)).click();
        System.out.println("✅ Navigated to Cart page.");
    }

    public boolean isProductInCart() {
        viewCart();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
        return !cartItems.isEmpty();
    }

    public void removeFromCart() {
        viewCart();
        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
        System.out.println("✅ Product removed from cart.");
    }

    public void increaseQuantity() {
        viewCart();
        wait.until(ExpectedConditions.elementToBeClickable(increaseQuantityButton)).click();
        System.out.println("✅ Quantity increased.");
    }

    public void decreaseQuantity() {
        viewCart();
        wait.until(ExpectedConditions.elementToBeClickable(decreaseQuantityButton)).click();
        System.out.println("✅ Quantity decreased.");
    }
}
