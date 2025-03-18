package com.flipkart.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignUpPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath = "//input[@type='text' and contains(@class, 'signup-phone')]") 
    private WebElement phoneInput;

    @FindBy(xpath = "//input[@type='text' and contains(@class, 'signup-name')]")
    private WebElement nameInput;

    @FindBy(xpath = "//input[@type='password' and contains(@class, 'signup-password')]")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
    private WebElement signUpButton;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void enterDetails(String phone, String name, String password) {
        wait.until(ExpectedConditions.visibilityOf(phoneInput)).sendKeys(phone);
        nameInput.sendKeys(name);
        passwordInput.sendKeys(password);
    }

    public void submitSignUp() {
        signUpButton.click();
    }

    public boolean isSignUpSuccessful() {
        return driver.getCurrentUrl().contains("flipkart.com");
    }
}
