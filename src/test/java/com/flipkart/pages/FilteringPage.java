package com.flipkart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	}
	
	public void filteroptions() {
		WebElement cat1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[1]/div/span"));
		if (cat1.isDisplayed()){
			
		}
	}

}
