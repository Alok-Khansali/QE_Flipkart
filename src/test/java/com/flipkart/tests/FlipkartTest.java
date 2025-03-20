package com.flipkart.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.flipkart.pages.CartPage;
import com.flipkart.pages.FilteringPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPages;

public class FlipkartTest {
    WebDriver driver;
    LoginPage loginPage;
    SearchPages searchPages;
    FilteringPage filterPage;
    CartPage cartPage;

    @SuppressWarnings("deprecation")
	@BeforeClass
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com/");

        loginPage = new LoginPage(driver);
        searchPages = new SearchPages(driver);
        filterPage = new FilteringPage(driver, "Poco");
        cartPage = new CartPage(driver);
    }

    @Test(priority = 1)
    public void loginTest() {
        System.out.println("Logging in...");
        loginPage.enterMobileNumber("6302215235");
        loginPage.enterOtpManually();
    }

    @Test(priority = 2)
    public void searchProductTest() {
        System.out.println("Searching for 'Poco'...");
        searchPages.SearchQuery("Poco");
    }

    @Test(priority = 3)
    public void applyFiltersTest() throws Exception {
        System.out.println("Applying filters...");
        filterPage.filterprice();
        filterPage.selectfassured();
        filterPage.selectram();
        filterPage.selectrating();
        filterPage.selectROM();
    }

    @Test(priority = 4)
    public void selectProductAndAddToCartTest() throws Exception {
        System.out.println("Selecting product...");
        searchPages.selectProduct();
        cartPage.addToCart("Smartphone");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("✅ Test Completed Successfully!");
    }
}