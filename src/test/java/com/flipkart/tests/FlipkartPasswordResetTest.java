package com.flipkart.tests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.flipkart.pages.LoginPage;
import com.flipkart.pages.PasswordResetPage;

import com.flipkart.pages.LoginPage;
import com.flipkart.pages.PasswordResetPage;

import com.flipkart.pages.LoginPage;
import com.flipkart.pages.PasswordResetPage;

public class FlipkartPasswordResetTest {

    WebDriver driver;
    LoginPage loginPage;
    PasswordResetPage resetPage;

    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://www.flipkart.com");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testPasswordReset() {
        resetPage = loginPage.clickForgotPassword();
        resetPage.enterEmailOrPhone("valid@email.com");

        if (resetPage.isOtpFieldPresent()) {
            resetPage.enterOtp();
        }

        resetPage.enterNewPassword("NewPassword123");

        Assert.assertTrue(resetPage.isResetSuccessMessageDisplayed(), "Password reset failed.");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }
}
