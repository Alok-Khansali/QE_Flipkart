package com.flipkart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class SearchPages {
  
	WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input")
	@CacheLookup
	WebElement searchbar; 
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[3]/div[1]/div[2]/div[2]/div/div/div/a/div[2]/div[1]/div[1]")
	@CacheLookup
	WebElement selectproduct; 
	
	@FindBy(how = How.CLASS_NAME, using = "zg-M3Z")
    @CacheLookup
    List<WebElement> sortOptions;
	
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
    
    public void selectProduct() throws Exception {
    	
    	Thread.sleep(1000);
        selectproduct.click();

        System.out.println("Product is selected!");
    }
    
    public void selectSortOption() {
    	List<WebElement> sortingOptions = driver.findElements(By.xpath("//div[@class='sHCOk2']/div[contains(@class, 'zg-M3Z')]"));

        // Iterate through each sorting option
        for (WebElement option : sortingOptions) {
            String optionText = option.getText();
            boolean isClickable = option.isDisplayed() && option.isEnabled();

            if (isClickable) {
            	System.out.println("✅ Option: " + optionText + " is SELECTABLE");
            	option.click();
            } else {
                System.out.println("❌ Option: " + optionText + " is NOT SELECTABLE");
            }
        }
    }
}
