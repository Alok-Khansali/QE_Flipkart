package com.flipkart.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SignUpPage;

public class FlipkartLoginTest {
    
    WebDriver driver;
    LoginPage loginPage;
    SignUpPage signUpPage;

    @BeforeTest
    public void setup() throws InterruptedException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://www.flipkart.com");
        Thread.sleep(3000);
        loginPage = new LoginPage(driver);
    }

    @Test(priority = 1)
    public void testLoginWithOtp()  {
		/* loginPage.clickLogin(); */
    	//kk
        loginPage.enterMobileNumber("6396762877");
    	//loginPage.enterMobileNumber("6396762877");
    	// vg
    	//loginPage.enterMobileNumber("9092314213");
    }

	/*
	 * @AfterTest public void teardown() { driver.quit(); }
	 */
}
