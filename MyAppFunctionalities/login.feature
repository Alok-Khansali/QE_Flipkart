Feature: Flipkart Login Functionality

  Scenario: Login with valid credentials and OTP
    Given I open Flipkart login page
    When I enter username "valid@email.com" and password "valid_password"
    And I click on login button
    Then If OTP is required, I enter it
    And I should be redirected to the homepage

  Scenario: Login with invalid credentials
    Given I open Flipkart login page
    When I enter username "wrong@email.com" and password "wrong_password"
    And I click on login button
    Then I should see an error message "Invalid credentials. Please try again."

  Scenario: Password reset with OTP
    Given I am on Flipkart login page
    When I click on "Forgot Password"
    And I enter registered email "valid@email.com"
    And I complete OTP verification
    Then I should be able to reset my password successfully
