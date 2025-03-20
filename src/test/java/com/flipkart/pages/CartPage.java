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

// Put variables in the functions
public class CartPage {
    WebDriver driver;
    WebDriverWait wait;
    SearchPages sp;

    @FindBy(how = How.CSS, using = ".In9uk2")
    private WebElement addToCartButton;

    @FindBy(how = How.XPATH, using = "/html/body/div[1]/div/div[1]/div[1]/div[2]/div[6]/div/div/a")
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

    public boolean addToCart(String productName, String variant, int quantity) {
        try 
        {
        	sp.selectProduct();
            // Proceed with adding to cart if in stock
        	// 5️⃣ Switch to new tab
        	Set<String> handles = driver.getWindowHandles();
            String newTab = handles.toArray(new String[0])[handles.size() - 1];
            driver.switchTo().window(newTab);
            // 6️⃣ Wait for 'Add to Cart' button & click
            WebElement addToCartBtn = driver.findElement(By.cssSelector(".In9uk2"));
            addToCartBtn.click();

            // 7️⃣ Close new tab and switch back
            driver.close();
            driver.switchTo().window(handles.toArray(new String[0])[0]);
            System.out.println("✅ Product added to cart successfully.");

//            for (int i = 1; i < quantity; i++) {
//                wait.until(ExpectedConditions.elementToBeClickable(increaseQuantityButton)).click();
//            }
            return true;
        } catch (Exception e) {
            System.out.println("❌ Failed to add product to cart: " + e.getMessage());
            return false;
        }
    }


    public void viewCart() {
        try {
            // Navigate to Cart Page
            driver.get("https://www.flipkart.com/viewcart");
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item")));

            // Fetch cart items
            List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));

            // Iterate through each item and print details
            for (WebElement item : cartItems) {
                try {
                    String productName = item.findElement(By.cssSelector(".product-name")).getText();
                    String productPrice = item.findElement(By.cssSelector(".product-price")).getText();
                    System.out.println("🛒 Product: " + productName + " | Price: " + productPrice);
                } catch (NoSuchElementException e) {
                    System.out.println("⚠ Some elements not found, skipping...");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error viewing cart: " + e.getMessage());
        }
    }


//    public boolean isProductInCart() {
//        viewCart();
//        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems));
//        return !cartItems.isEmpty();
//    }

//    public void removeFromCart() {
//        viewCart();
//        while (isProductInCart()) {
//            wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
//            System.out.println("✅ One unit removed from cart.");
//        }
//        System.out.println("✅ Product completely removed from cart.");
//    }

    public void increaseQuantity(String productName, String variant, int quantity) {
        viewCart();
        for (int i = 1; i < quantity; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(increaseQuantityButton)).click();
        }
        System.out.println("✅ Quantity increased to: " + quantity);
    }

	/*
	 * public void decreaseQuantity(String productName, String variant, int
	 * quantityToRemove) { viewCart();
	 * 
	 * for (int i = 0; i < quantityToRemove; i++) { if (isProductInCart()) {
	 * wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
	 * System.out.println("✅ Removed one unit of " + productName + " - " + variant);
	 * } else {
	 * System.out.println("❌ Product not found in cart or already removed."); break;
	 * } }
	 * 
	 * System.out.println("✅ Requested quantity removed for " + productName + " - "
	 * + variant); }
	 */

}