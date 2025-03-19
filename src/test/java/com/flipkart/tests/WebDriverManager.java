package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.flipkart.pages.CartPage;
import com.flipkart.pages.FilteringPage;
import com.flipkart.pages.SearchPages;

public class WebDriverManager {
    WebDriver driver;
    SearchPages home;
    FilteringPage filters;
    CartPage cart;

    @BeforeMethod
    public void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        home = new SearchPages(driver);
        filters = new FilteringPage(driver, "Poco");
        cart = new CartPage(driver);
    }

    @Test(priority = 0)
    public void search() throws Exception {
        home.SearchQuery("Poco");
        home.selectSortOption();
        home.selectProduct();
    }

    @Test(dependsOnMethods = "search")
    public void Partialsearch() throws Exception {
        home.SearchQuery("Samsu");
        home.validateSearch("Casi");
    }

    @Test(dependsOnMethods = "search")
    public void Invalidsearch() throws Exception {
        home.SearchInvalid("ajknakj");
    }

    @Test
    public void FilterFuntions() {
        filters.filteroptions();
    }

    @Test(dataProvider = "cartData", dependsOnMethods = "search")
    public void addToCartTest(String productName, String variant, int quantity) {
        home.SearchQuery(productName);
        home.selectProductByName(productName);
        cart.addToCart(productName, variant, quantity);
        Assert.assertTrue(cart.verifyItemInCart(productName), "Product not found in cart: " + productName);
    }

    @DataProvider(name = "cartData")
    public Object[][] getCartData() {
        return TestData.getCartData();
    }

    @AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
