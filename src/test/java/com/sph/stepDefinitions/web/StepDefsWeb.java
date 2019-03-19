package com.sph.stepDefinitions.web;


import com.sph.listeners.Reporter;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import com.sph.driverFactory.DriverManager;
import com.sph.pageObjects.web.Web;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Simple steps mapping class that always fails.
 * I've added it to show how failed story will look like in the final Cucumber HTML report.
 * @author jk
 */
public class StepDefsWeb {

    static Logger log;
    WebDriver driver = DriverManager.getDriver();
    private Web loginPage;
    private List<WebElement> searchResults;

    static {
        log = Logger.getLogger(StepDefsWeb.class);
    }

    public StepDefsWeb() throws MalformedURLException {
    }


    @Given("^I want to launch the Connection Tester website$")
    public void i_want_to_launch_connection_tester_website() throws InterruptedException {
        loginPage = new Web(this.driver);
        loginPage.launch_Page();
        Reporter.addStepLog("User wants to launch the Connection Tester website");
        Reporter.addScenarioLog("User wants to launch the Connection Tester website");

    }
    @When("^I want to verify the headline of the page$")
    public void i_want_to_verify_the_headline() throws InterruptedException {
        loginPage.verify_Heading();
        Reporter.addStepLog("User wants to verify the headline of the page");
        Reporter.addScenarioLog("User wants to verify the headline of the page");

    }

    @Then("^I want to select the production environment$")
    public void i_want_to_select_the_production_environment() throws InterruptedException {
        loginPage.select_production();
        Reporter.addStepLog("User wants to select the production environment");
        Reporter.addScenarioLog("User wants to select the production environment");
    }

    @And("^I want to click on run checker button$")
    public void i_want_to_click_runchecker_button() throws InterruptedException {
        loginPage.click_Run_Checker();
        Reporter.addStepLog("User wants to click on run checker button");
        Reporter.addScenarioLog("User wants to click on run checker button");

    }

    @And("^I want to read the summary after the run$")
    public void i_want_to_read_the_summary_after_the_run() throws InterruptedException {
        loginPage.read_Summary();
        Reporter.addStepLog("User wants to read the summary after the run");
        Reporter.addScenarioLog("User wants to read the summary after the run");

    }

    @And("^I want to send email if there is any server failure$")
    public void i_want_to_send_email_if_there_is_any_server_failure() throws InterruptedException {
        loginPage.send_Email();
        Reporter.addStepLog("User wants to send email if there is any server failure");
        Reporter.addScenarioLog("User wants to read the summary after the run");
    }


}