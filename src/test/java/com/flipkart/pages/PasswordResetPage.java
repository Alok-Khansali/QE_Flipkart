package com.flipkart.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.Scanner;

public class PasswordResetPage {

    WebDriver driver;

    @FindBy(css = "input[type='text']")  // Adjust selector based on Flipkart's Forgot Password page
    private WebElement emailOrPhoneField;

    @FindBy(css = "button[type='submit']")  // Submit button to request OTP
    private WebElement requestOtpButton;

    @FindBy(css = "input[placeholder='Enter OTP']")
    private WebElement otpField;

    @FindBy(css = "button[class*='_2KpZ6l']")  // OTP verification submit button
    private WebElement submitOtpButton;

    @FindBy(css = "input[type='password']")  // New password field
    private WebElement newPasswordField;

    @FindBy(css = "button[type='submit']")  // Reset button
    private WebElement resetPasswordButton;

    @FindBy(css = "div[class='success-message']")
    private WebElement successMessage;

    public PasswordResetPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmailOrPhone(String emailOrPhone) {
        emailOrPhoneField.clear();
        emailOrPhoneField.sendKeys(emailOrPhone);
        requestOtpButton.click();
    }

    public boolean isOtpFieldPresent() {
        try {
            return otpField.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void enterOtp() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter OTP: ");
        String otp = scanner.nextLine();
        otpField.sendKeys(otp);
        submitOtpButton.click();
    }

    public void enterNewPassword(String newPassword) {
        newPasswordField.clear();
        newPasswordField.sendKeys(newPassword);
        resetPasswordButton.click();
    }

    public boolean isResetSuccessMessageDisplayed() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
