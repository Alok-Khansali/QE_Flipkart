package com.flipkart.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;
    SearchPages sp;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button")
    private WebElement addToCartButton;

    @FindBy(how = How.CSS, using = "a[href*='viewcart']")
    private WebElement viewCartButton;

    @FindBy(how = How.CLASS_NAME, using = "cart-item")
    private List<WebElement> cartItems;

    @FindBy(how = How.ID, using = "remove-item")
    private WebElement removeButton;

    @FindBy(how = How.ID, using = "increase-quantity")
    private WebElement increaseQuantityButton;

    @FindBy(how = How.ID, using = "decrease-quantity")
    private WebElement decreaseQuantityButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        sp = new SearchPages(driver);
    }

    public boolean addToCart(String productName) {
        try {
            sp.selectProduct();
            
            // Switch to new tab if product opens there
            Set<String> handles = driver.getWindowHandles();
            String newTab = handles.toArray(new String[0])[handles.size() - 1];
            driver.switchTo().window(newTab);
			/*
			 * WebElement cart = driver.findElement(By.xpath(
			 * "/html/body/div[1]/div/div[3]/div[1]/div[1]/div[2]/div/ul/li[1]/button"));
			 * 
			 * // Wait for 'Add to Cart' button & click if(cart != null) cart.click(); else
			 */
            Thread.sleep(4000);
            addToCartButton.click();
            
            driver.switchTo().window(handles.toArray(new String[0])[0]);
            
            System.out.println("✅ Product added to cart successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("❌ Failed to add product to cart: " + e.getMessage());
            return false;
        }
    }

    public void viewCart() {
        viewCartButton.click();
        System.out.println("✅ Navigated to Cart page.");
        driver.get("https://www.flipkart.com/viewcart");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'cart-item')]")));
    }

    public void increaseQuantity(String productName, String variant, int quantity) {
        viewCart();
        for (int i = 1; i < quantity; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(increaseQuantityButton)).click();
        }
        System.out.println("✅ Quantity increased to: " + quantity);
    }
}
