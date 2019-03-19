package com.sph.pageObjects.web;

import com.sph.driverFactory.LocalWebDriverListener;
import com.sph.utilities.WebElements;

import org.apache.log4j.Logger;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;


/**
 * Created by jay on 10/04/14.
 */
public class Web {

    private WebDriver driver;
    private WebDriverWait wait;
    private String Read_Story_Headline;
    Logger log = Logger.getLogger(Web.class);
    String browserName = LocalWebDriverListener.browserName;
    String connectionWebURL = "http://monitor:K8bHQcWEG2pA@push.sphdigital.com/connection_tester/connection_test";
    String READ_SUMMARY;

    @FindBy(xpath = WebElements.PAGE_HEADING)
    private WebElement page_headline;

    @FindBy(id = WebElements.PRODUCTION_BUTTON)
    private WebElement prod_button;

    @FindBy(id = WebElements.RUN_CHECKER_BUTTON)
    private WebElement run_checker_button;

    @FindBy(xpath = WebElements.SUMMARY)
    private WebElement read_summary;

    @FindBy(xpath = WebElements.CHECK_RUN_STATUS)
    private WebElement run_status;

    @FindBy(xpath = WebElements.EMAIL_RESULT_BUTTON)
    private WebElement email_button;


    public Web(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void launch_Page() throws InterruptedException {
        this.driver.get(connectionWebURL);
        //driver.manage().window().maximize();

    }

    public void verify_Heading() throws InterruptedException {
        Thread.sleep(5000);
        String PAGE_TITLE = page_headline.getText();
        System.out.println("Page headline is: " + PAGE_TITLE);
        Assert.assertEquals("Servers Status Checker", PAGE_TITLE);
    }

    public void select_production() throws InterruptedException {
        Thread.sleep(5000);
        prod_button.click();

    }


    public void click_Run_Checker() throws InterruptedException {
        Thread.sleep(5000);
        run_checker_button.click();
    }

    public void read_Summary() throws InterruptedException {
        Thread.sleep(5000);
        String RUN_STATUS = run_status.getText();
        if (RUN_STATUS.contains("Completed")) {
            READ_SUMMARY = read_summary.getText();

        }
    }

    public void send_Email() throws InterruptedException {
        Thread.sleep(5000);
            String SERVER_NUMBER = READ_SUMMARY.substring(READ_SUMMARY.length() - 1);
            System.out.println("Server Number is:" +SERVER_NUMBER);
            Integer CONVERT_SERVER_NUMBER = Integer.parseInt(SERVER_NUMBER);
            System.out.println("Server number is: "+CONVERT_SERVER_NUMBER);
            System.out.println("Number of servers ran successfuly are : "+CONVERT_SERVER_NUMBER);
            if (CONVERT_SERVER_NUMBER < 8) {
                email_button.click();

                Thread.sleep(10000);
                driver.switchTo().alert().accept();
            }
        }


    }


