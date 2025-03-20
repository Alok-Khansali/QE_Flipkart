package com.flipkart.pages;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPages {
  
    WebDriver driver;
    WebDriverWait wait;
	
    @FindBy(how = How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div/div/div/div/div[1]/div/div/div/div[1]/div[1]/header/div[1]/div[2]/form/div/div/input")
    @CacheLookup
    WebElement searchbar; 
	
    WebElement selectproduct; 
	
    @FindBy(how= How.XPATH, using = "//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input")
    @CacheLookup
    WebElement search;
	
    public SearchPages(WebDriver d) {
        this.driver = d;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
	
    public void SearchQuery(String query) {
            System.out.println("Current Page URL: " + driver.getCurrentUrl());

            // Reload homepage if necessary
            if (!driver.getCurrentUrl().equalsIgnoreCase("flipkart.com")) {
                driver.get("https://www.flipkart.com");
            }

            // Wait for the search bar and type query
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            searchbar.clear();
            searchbar.sendKeys(query);
            searchbar.submit();
            System.out.println("✅ Search successful!");
            // Wait for search results to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'product-title')]")));
            System.out.println("✅ Search results loaded!");

    }


    
    public void SearchInvalid(String query) {
        searchbar.sendKeys(query);
        searchbar.submit();
        
        if (isElementPresent(By.className("BHPsUQ"))) {
            System.out.println("❌ No products found. Try a different search term.");
        } else {
            System.out.println("✅ Search is successful!");
        }
    }
    
    public void validateSearch(String query) {
        search.clear();
        search.sendKeys(query);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    	
        if (isElementPresent(By.xpath("//*[@id=\"container\"]/div/div[1]/div[1]/div[2]/div[2]/form/div/div/input"))) {
            System.out.println("✅ Auto-suggestions appear matching entered characters.");
        }
    }
    
    public void selectProduct() {
        try {
            System.out.println("Current Page URL: " + driver.getCurrentUrl());
            
            // Wait for the first product to appear and fetch the URL
            WebElement firstProduct = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.CGtC98")));
            String productUrl = firstProduct.getAttribute("href");
            System.out.println(productUrl);

            // Open the product in a new tab
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.open(arguments[0])", productUrl);

            // Switch to the new tab
            Set<String> handles = driver.getWindowHandles();
            for (String handle : handles) {
                if (!handle.equals(driver.getWindowHandle())) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
            System.out.println("✅ Switched to product page: " + driver.getCurrentUrl());

        } catch (Exception e) {
            System.out.println("❌ Failed to open product page: " + e.getMessage());
        }
    }



    
    public void selectSortOption() {
        List<WebElement> sortingOptions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//div[@class='sHCOk2']/div[contains(@class, 'zg-M3Z')]")
        ));

        for (WebElement option : sortingOptions) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(option)).click();
                System.out.println("✅ Selected: " + option.getText());
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("Retrying stale element...");
                sortingOptions = driver.findElements(By.xpath("//div[@class='sHCOk2']/div[contains(@class, 'zg-M3Z')]"));
            }
        }
    }

    private boolean isElementPresent(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}