package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FilteringPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input")
	@CacheLookup
	WebElement searchbar;
	
	
	
	public FilteringPage(WebDriver d, String product) {
		driver = d;
		driver.get("https://www.flipkart.com/");
		PageFactory.initElements(driver, this);
		
		WebElement popup = driver.findElement(By.xpath("/html/body/div[3]/div/span"));
		if(popup.isDisplayed()) {
			popup.click();
		}
		searchbar.sendKeys(product);
		searchbar.submit();
	}
	
	public void filteroptions() throws Exception {
		
		WebElement cat1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[1]/div/span"));
		
		System.out.println("selected");
		if (cat1.isDisplayed()){
			Thread.sleep(2000);
			
	        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	        WebElement rightSlider = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[3]/div[1]/div[2]/div"));
	        
	        
	        WebElement leftSlider = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[3]/div[1]/div[1]/div"));

	       //driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[3]/div[2]/div[2]")).click();
	        Actions actions = new Actions(driver);

	        // Move the left slider right by 50 pixels
	        actions.clickAndHold(leftSlider).moveByOffset(100, 0).release().perform();
	        Thread.sleep(10000);
	        // Move the right slider left by 50 pixels
	        actions.clickAndHold(rightSlider).moveByOffset(-50, 0).release().perform();
	        Thread.sleep(5000);
	        WebElement maxvalue = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]"));
	        
	        if (maxvalue.isDisplayed())
	        	System.out.println(maxvalue.getText());
		}
	}

}
