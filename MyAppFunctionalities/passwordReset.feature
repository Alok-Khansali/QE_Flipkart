Feature: Flipkart Password Reset Functionality

  Scenario: Reset password with OTP
    Given I am on Flipkart login page
    When I click on "Forgot Password"
    And I enter registered email "valid@email.com"
    And I complete OTP verification
    And I enter new password "NewPassword123"
    Then I should see a success message "Your password has been successfully reset."
