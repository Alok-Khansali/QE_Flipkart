package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.pages.CartPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPages;
import com.flipkart.testdata.RemoveTestData;
import com.flipkart.testdata.TestData;

public class CartTest {

    WebDriver driver;
    WebElement selectproduct;
    LoginPage loginPage;
    SearchPages searchPage;
    CartPage cartPage;

    @BeforeClass  // Load Flipkart only once before all tests
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("flipkart.com"));

        // Initialize page objects once and reuse them
        //loginPage = new LoginPage(driver);
        searchPage = new SearchPages(driver);
        cartPage = new CartPage(driver);

        // Perform login before all tests
        //loginPage.enterMobileNumber("9092314213");
    }

    @DataProvider(name = "cartData")
    public Object[][] getCartData() {
        return TestData.getCartData();
    }

	/*
	 * @DataProvider(name = "removeData") public Object[][] getRemoveData() { return
	 * RemoveTestData.getRemoveData(); }
	 */

    @Test(dataProvider = "cartData")
    public void testAddToCart(String productName, String variant, int quantity) throws InterruptedException {
        System.out.println("Testing Add to Cart for: " + productName + " - " + variant + " (Quantity: " + quantity + ")");

        // Search for the product
        searchPage.SearchQuery(productName);

        // Try adding product to cart
        boolean itemAdded = cartPage.addToCart(productName, variant, quantity);

        if (!itemAdded) {
            System.out.println("⚠️ Skipping remaining tests for " + productName + " (Out of Stock)");
            
        }

//        // Verify item in cart
//        boolean cartVerified = cartPage.isProductInCart();
//        Assert.assertTrue(cartVerified, "Item verification failed for " + productName);
    }

	/*
	 * @Test(dependsOnMethods = "testAddToCart", dataProvider = "cartData") public
	 * void testIncreaseQuantity(String productName, String variant, int quantity) {
	 * cartPage.increaseQuantity(productName, variant, quantity); }
	 * 
	 * @Test(dependsOnMethods = "testIncreaseQuantity", dataProvider = "cartData")
	 * public void testDecreaseQuantity(String productName, String variant, int
	 * quantity) { cartPage.decreaseQuantity(productName, variant, quantity); }
	 * 
	 * @Test(dependsOnMethods = "testDecreaseQuantity", dataProvider = "removeData")
	 * public void testRemoveFromCart() { cartPage.removeFromCart(); }
	 */
}
