package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.pages.FilteringPage;
import com.flipkart.pages.SearchPages;

public class WebDriverManager {
	WebDriver driver;
	SearchPages home;
	FilteringPage filters;
	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		home = new SearchPages(driver);
		filters = new FilteringPage(driver, "Poco");
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
	
	@AfterMethod
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}