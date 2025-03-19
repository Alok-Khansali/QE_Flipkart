package com.flipkart.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.pages.CartPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPages;
import com.flipkart.testdata.TestData;

public class CartTest {

    WebDriver driver;
    LoginPage loginPage;
    SearchPages searchPage;
    CartPage cartPage;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");
        loginPage = new LoginPage(driver);
        searchPage = new SearchPages(driver);
        cartPage = new CartPage(driver);

        // Perform login before adding items to cart
        loginPage.enterMobileNumber("your-mobile-number");
    }

    @DataProvider(name = "cartData")
    public Object[][] getCartData() {
        return TestData.getCartData();
    }

    @Test(dataProvider = "cartData")
    public void testAddToCart(String productName, String variant, int quantity) throws InterruptedException {
        System.out.println("Testing Add to Cart for: " + productName + " - " + variant + " (Quantity: " + quantity + ")");
        
        // Search for the product first
        searchPage.SearchQuery(productName);
        searchPage.selectProductByName(productName);
        
        // Add product to cart
        boolean itemAdded = cartPage.addToCart(productName, variant, quantity);
        Assert.assertTrue(itemAdded, "Failed to add " + productName + " to cart!");
        
        // Verify item in cart
        boolean cartVerified = cartPage.verifyItemInCart(productName, variant, quantity);
        Assert.assertTrue(cartVerified, "Item verification failed for " + productName);
    }
}
