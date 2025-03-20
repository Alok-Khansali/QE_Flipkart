package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	
	public void filterprice() throws Exception {
		
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
	        Thread.sleep(5000);
	        // Move the right slider left by 50 pixels
	        actions.clickAndHold(rightSlider).moveByOffset(-50, 0).release().perform();
	        Thread.sleep(5000);
	        WebElement maxvalue = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[2]/div[4]"));
	        
	        if (maxvalue.isDisplayed())
	        	System.out.println(maxvalue.getText());
		}
	}
	
	public void selectfassured() throws Exception{
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,50)");
		WebElement fassured = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[4]/label/div[1]"));
	    
		
		fassured.click();
		if(driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[4]/label/input")).isSelected()) {
			System.out.println("Fassured is selected");
		}
	}
	
	public void selectram() throws Exception{
		
		Thread.sleep(1000);
		//driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[7]/div/div")).click();
		
		Thread.sleep(1000);
		WebElement checkbox1 = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div/div[1]/div/section[7]/div[2]/div/div[1]/div/label/div[1]"));
		WebElement checkbox2 = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div/div[1]/div/section[7]/div[2]/div/div[2]/div/label/div[1]"));
		
		checkbox1.click();
		
		Thread.sleep(3000);
		checkbox2.click();
		Thread.sleep(3000);
		if(driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div/div[1]/div/section[7]/div[2]/div/div[3]/div/label/input")).isSelected()) {
			if (driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div/div[1]/div/section[7]/div[2]/div/div[2]/div/label/input")).isSelected())
				System.out.println("4GB, 8GB & Above are selected");
		}
		
	}
	
	public void selectrating() throws Exception{
		Thread.sleep(1000);
		
		//driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[5]/div/div")).click();
		
		Thread.sleep(1000);
		WebElement checkbox = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[5]/div[2]/div/div[1]/div/label/div[1]"));
		
		checkbox.click();
		
		
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,100)");
		if(driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[1]/div/div[1]/div/section[5]/div[2]/div/div[2]/div/label/input")).isSelected()) {
			System.out.println("rating is selected");
		}
	}
	
public void selectROM() throws Exception{
		
		Thread.sleep(1000);
		
		
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0,800)");
		WebElement internal_storage =  driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[8]/div/div"));
		internal_storage.click(); 
		Thread.sleep(2000);
		
		
		WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[8]/div[2]/div/div[1]/div/label/div[2]"));
		
		
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * WebElement element =
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//*[@id='container']/div/div[3]/div[1]/div[1]/div/div[1]/div/section[8]/div[2]/div/div[1]/div/label/div[2]"
		 * ))); element.click();
		 */
		checkbox1.click();
		
		Thread.sleep(3000);
		WebElement battery = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div[1]/div[1]/div/div[1]/div/section[9]/div/div"));
		battery.click();
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * WebElement element
		 * =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//*[@id='container']/div/div[3]/div[1]/div[1]/div/div[1]/div/section[8]/div[2]/div/div[1]/div/label/div[2]"
		 * ))); element.click();
		 */
		WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[9]/div[2]/div/div[2]/div/label/div[2]"));
		checkbox2.click();
		Thread.sleep(3000);
		internal_storage.click();
		battery.click();
		if(driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[8]/div[2]/div/div[2]/div/label/input")).isSelected()) {
			if (driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[3]/div/div[1]/div/div[1]/div/section[9]/div[2]/div/div[2]/div/label/input")).isSelected())
				System.out.println("Storage and Battery are selected");
		}
		
	}
}
