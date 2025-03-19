package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.pages.FilteringPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPages;

public class LoginFunctinality {
	
	WebDriver driver;
	SearchPages home;
	FilteringPage filters;
	LoginPage login;
	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		home = new SearchPages(driver);
		filters = new FilteringPage(driver, "Poco");
		login = new LoginPage(driver);
	}
	
	@Test
	public void login() {
		login.enterMobileNumber("9092314213");
		login.enterOtpManually();
		home.SearchQuery("Poco");
		
	}
	
	@Test
	public void FilterFuntions() throws Exception {
		filters.filteroptions();
	}
	
	@AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
