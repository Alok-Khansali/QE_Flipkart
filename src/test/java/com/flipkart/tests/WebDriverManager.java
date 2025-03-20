package com.flipkart.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.flipkart.pages.CartPage;
import com.flipkart.pages.FilteringPage;
import com.flipkart.pages.LoginPage;
import com.flipkart.pages.SearchPages;

public class WebDriverManager {
	WebDriver driver;
	SearchPages home;
	FilteringPage filters;
	LoginPage login;
	CartPage cart;
	@BeforeMethod
	public void setup() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
		home = new SearchPages(driver);
		filters = new FilteringPage(driver, "Poco");
		login = new LoginPage(driver);
		cart = new CartPage(driver);
	}
	
	
	
	@Test(priority = 1)
	public void search() throws Exception {
		home.SearchQuery("Poco");
		home.selectSortOption();
		home.selectProduct();
	}
	
	@Test(priority = 2)
	public void Partialsearch() throws Exception {
		home.SearchQuery("Samsu");
		home.validateSearch("Casi");
		
	}
	
	@Test(priority = 3)
	public void Invalidsearch() throws Exception {
		home.SearchInvalid("ajknakj");
		
	}
	
	@Test(priority = 4)
	public void FilterFuntions() throws Exception {
		//login.enterMobileNumber("9092314213");
		filters.filterprice();
		filters.selectfassured();
		filters.selectrating();
		filters.selectram();
		filters.selectROM();
		home.selectProduct();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,50)");
	    driver.findElement(By.className("NwyjNT")).click();
		
	}
	
	@Test(priority = 5)
	public void Wishlist() throws Exception{
		home.SearchQuery("Poco");
		Thread.sleep(2000);
		//driver.findElement(By.xpath("")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[1]/div[3]/div/svg/path")));
		element.click();
	}
	
	
	@AfterMethod public void quitDriver() { 
		if (driver != null) { 
			//driver.quit();
			} 
		}
	
}
