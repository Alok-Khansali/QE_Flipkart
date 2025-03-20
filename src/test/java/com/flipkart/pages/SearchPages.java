package com.flipkart.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPages {
  
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input")
	@CacheLookup
	WebElement searchbar; 
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
	@CacheLookup
	WebElement selectproduct; 
	
	@FindBy(how= How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
	@CacheLookup
	WebElement search;
	
	public SearchPages(WebDriver d) {
		driver = d;
		driver.get("https://www.flipkart.com/");
		
		PageFactory.initElements(driver, this); //this
	}
	
    public void SearchQuery(String query) {
        searchbar.sendKeys(query);
        searchbar.submit();
        
        System.out.println("Search is successfull!");
    }
    
    public void SearchInvalid(String query) {
        searchbar.sendKeys(query);
        searchbar.submit();
        
        if(driver.findElement(By.className("BHPsUQ")).isDisplayed())
        	System.out.println("No products found. Try a different search term");
        else
        	System.out.println("Search is successfull!");
    }
    
    public void validateSearch(String query) {
        search.clear();
        
    	search.sendKeys(query);
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        if(driver.findElement(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")).isDisplayed()) {
        	System.out.println("Auto-suggestions appear matching entered characters.");
        }
        
    }
    
    public void selectProduct() throws Exception {
    	
    	Thread.sleep(1000);
        selectproduct.click();

        System.out.println("Product is selected!");
    }
    
    public void selectSortOption() throws Exception {
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    	Thread.sleep(1000);

        
        List<WebElement> sortingOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='sHCOk2']/div[contains(@class, 'zg-M3Z')]")
        ));

        for (WebElement option : sortingOptions) {
            try {
            	Thread.sleep(1000);
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                System.out.println("✅ Selected: " + option.getText());
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying stale element...");
                sortingOptions = driver.findElements(By.xpath("//div[@class='sHCOk2']/div[contains(@class, 'zg-M3Z')]"));
                
            }
        }
    }

	public void selectProductByName(String productName) {
		// TODO Auto-generated method stub
		
	}
}
