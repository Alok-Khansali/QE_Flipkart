package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.pages.SearchPages;

public class WebDriverManager {
	WebDriver driver;
	SearchPages home;
	
	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		home = new SearchPages(driver);
	}
	
	
	
	@Test(priority = 0)
	public void search() throws Exception {
		home.SearchQuery("Poco");
		home.selectSortOption();
		home.selectProduct();
	}
	
	@Test(priority = 1)
	public void Partialsearch() throws Exception {
		home.SearchQuery("Samsu");
		home.validateSearch("Casi");
		
	}
	
	@Test(priority = 2)
	public void Invalidsearch() throws Exception {
		home.SearchInvalid("ajknakj");
		
	}
	
	@AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}