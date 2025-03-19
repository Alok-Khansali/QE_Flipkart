Feature: Flipkart Search Functionality

  Scenario: Search for a valid product
    Given I am on the Flipkart homepage
    When I search for "Poco"
    Then I should see search results
    And I select a product

  Scenario: Search for an invalid product
    Given I am on the Flipkart homepage
    When I search for "ajknakj"
    Then I should see a message saying "No products found"

  Scenario: Validate auto-suggestions for partial search
    Given I am on the Flipkart homepage
    When I search for "Samsu"
    Then I should see auto-suggestions matching "Casi"

  Scenario: Select a sorting option
    Given I am on the Flipkart search results page
    When I select a sorting option
    Then the products should be sorted accordingly

    
    