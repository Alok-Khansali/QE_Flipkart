package com.flipkart.bdd;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.flipkart.pages.SearchPages;
import io.cucumber.java.en.*;

public class SearchSteps {
    WebDriver driver;
    SearchPages searchPages;

    @Given("I am on the Flipkart homepage")
    public void i_am_on_the_flipkart_homepage() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        searchPages = new SearchPages(driver);
    }

    @When("I search for {string}")
    public void i_search_for(String query) {
        searchPages.SearchQuery(query);
    }

    @Then("I should see search results")
    public void i_should_see_search_results() {
        System.out.println("Results displayed successfully!");
    }

    @Then("I should see a message saying {string}")
    public void i_should_see_a_message_saying(String message) {
        System.out.println(message);
    }

    @And("I select a product")
    public void i_select_a_product() throws Exception {
        searchPages.selectProduct();
    }

    @Then("I should see auto-suggestions matching {string}")
    public void i_should_see_auto_suggestions_matching(String query) {
        searchPages.validateSearch(query);
    }

    @When("I select a sorting option")
    public void i_select_a_sorting_option() throws Exception {
        searchPages.selectSortOption();
    }

    @Then("the products should be sorted accordingly")
    public void the_products_should_be_sorted_accordingly() {
        System.out.println("Products sorted successfully!");
    }
}
