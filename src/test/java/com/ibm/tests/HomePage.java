package com.ibm.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
/**
 * Page Object Model for Flipkart Home page.
 */
public class HomePage {
    static WebElement searchBox;
    static WebElement searchButton;
    
    static WebDriver driver;
    public static void setup() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    /**
     * Searches for a product on the home page.
     * @param query Search term (e.g., "phone")
     */
    public static void searchProduct(String query) {
    	driver.get("https://www.flipkart.com/");
    	searchBox=driver.findElement(By.xpath(""));
        searchBox.sendKeys(query);
        searchBox.submit();
        
    }
    public static void selectProduct() throws Exception {
    	
    	Thread.sleep(1000);
        WebElement selectphn = driver.findElement(By.xpath(""));

		/*
		 * // Use JavaScript Executor to click JavascriptExecutor js =
		 * (JavascriptExecutor) driver; js.executeScript("arguments[0].click();",
		 * selectphn);
		 */
        selectphn.click();

        System.out.println("Product clicked using JavaScriptExecutor.");
    }
    
    public static void main(String[] args) throws Exception {
    	setup();
    	searchProduct("Poco");
    	selectProduct();
    	//driver.quit();
    }
}
