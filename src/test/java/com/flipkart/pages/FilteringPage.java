package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class FilteringPage {
	
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input")
	@CacheLookup
	WebElement searchbar;
	
	
	
	public FilteringPage(WebDriver d, String product) {
		driver = d;
		driver.get("https://www.flipkart.com/");
		PageFactory.initElements(driver, this);
		
		
		searchbar.sendKeys(product);
		searchbar.click();
	}
	
	public void filteroptions() {
		
		WebElement cat1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[1]/div/span"));
		if (cat1.isDisplayed()){
			WebElement leftSlider = driver.findElement(By.className("iToJ4v Kaqq1s")); 
	        WebElement rightSlider = driver.findElement(By.className("iToJ4v D0puJn"));

	       
	        Actions actions = new Actions(driver);

	        // Move the left slider right by 50 pixels
	        actions.clickAndHold(leftSlider).moveByOffset(50, 0).release().perform();

	        // Move the right slider left by 50 pixels
	        actions.clickAndHold(rightSlider).moveByOffset(-50, 0).release().perform();
	        
	        WebElement maxvalue = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]/div[3]/select/option[4]"));
	        
	        if (maxvalue.isDisplayed())
	        	System.out.println(maxvalue.getText()+" is the max value");
		}
	}

}
