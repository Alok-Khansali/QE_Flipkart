package com.flipkart.pages;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @FindBy(xpath = "//a[@title='Login']")
    private WebElement loginButton;

    private WebElement mobileNumberInput;

    @FindBy(xpath = "//input[@type='text' and contains(@class, 'otp-input-class')]") // Change class if needed
    private WebElement otpInput;

    @FindBy(xpath = "//div[@class='error-message-class']") // Update based on Flipkart's actual error message class
    private WebElement errorMessage;

    @FindBy(xpath = "//a[@href='/account/signup']")
    private WebElement signUpLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterMobileNumber(String mobileNumber) {
        try {
            // Step 1: Click the Login button
            System.out.println("Clicking the login button...");
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();

            // Step 2: Wait for the correct login input field (Avoiding search box)
            System.out.println("Waiting for the correct login input field...");
            mobileNumberInput = driver.findElement(By.cssSelector(".r4vIwl"));

            // Step 3: Click to focus the input field
            mobileNumberInput.click();

            // Step 4: Clear and enter the mobile number
            mobileNumberInput.clear();
            mobileNumberInput.sendKeys(mobileNumber);
            System.out.println("✅ Successfully entered Mobile Number: " + mobileNumber);

            // Click "Request OTP"
            WebElement RequestOTP = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/form/div[3]/button"));
            RequestOTP.click();

            // Step 5: Check if "Max Attempts Reached" message appears
            if (isMaxAttemptsReached()) {
                System.out.println("🚨 Maximum OTP attempts reached. Please try again after 24 hours.");
                return; // Stop execution here
            }

            // Proceed to enter OTP manually
            enterOtpManually();

        } catch (NoSuchElementException e) {
            System.out.println("❌ Error: Mobile input field not found.");
        }
    }

    /**
     * Checks if Flipkart shows "Maximum attempts reached. Retry in 24 hours." error.
     */
    private boolean isMaxAttemptsReached() {
        try {
            WebElement maxAttemptsError = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(text(),'Maximum attempts reached. Retry in 24 hours.')]")
            ));
            return maxAttemptsError.isDisplayed();
        } catch (Exception e) {
            return false; // No max attempts error found, continue normally
        }
    }


    public void enterOtpManually() {
        // Wait until OTP fields appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.I-qZ4M > input")));

        // Ask user to enter OTP manually
        System.out.print("✅ Please enter the 6-digit OTP: ");
        Scanner scanner = new Scanner(System.in);
        String otp = scanner.nextLine();

        // Check if OTP is exactly 6 digits
        if (otp.length() != 6) {
            System.out.println("❌ Error: OTP must be exactly 6 digits.");
            return;
        }

        // Retry mechanism to handle stale elements
        boolean success = false;
        int attempts = 0;

        while (!success && attempts < 3) {
            try {
                // Re-fetch OTP input elements every attempt
                List<WebElement> otpInputs = driver.findElements(By.cssSelector("div.I-qZ4M > input"));

                // Ensure correct number of OTP fields
                if (otpInputs.size() != 6) {
                    System.out.println("❌ Error: Expected 6 OTP input fields, found " + otpInputs.size());
                    return;
                }

                // Enter each digit into the correct OTP field
                for (int i = 0; i < 6; i++) {
                    otpInputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
                    System.out.println("Typed digit " + (i + 1) + ": " + otp.charAt(i));
                }

                success = true;
                System.out.println("✅ OTP entered successfully!");
            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ StaleElementReferenceException encountered. Retrying...");
                attempts++;
            }
        }
        
        WebElement ClickVerify = driver.findElement(By.xpath("/html/body/div/div/div[3]/div/div[2]/div/div/form/button"));
        ClickVerify.click();
    }



    public String getErrorMessage() {
        try {
            return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
        } catch (Exception e) {
            return "No error message found.";
        }
    }

    public SignUpPage goToSignUpPage() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)).click();
        return new SignUpPage(driver);
    }
}
