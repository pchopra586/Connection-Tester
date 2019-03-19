package com.sph.driverFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.BaseTestMethod;

import java.lang.reflect.Field;
import java.net.MalformedURLException;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * 
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class LocalWebDriverListener implements IInvokedMethodListener {

	public static String browserName;

	static Logger log = Logger.getLogger(LocalWebDriverListener.class);

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		log.debug("BEGINNING: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");
		if (method.isTestMethod()) {
			// get browser name specified in the TestNG XML test suite file
			browserName = method.getTestMethod().getXmlTest().getLocalParameters().get("browserName");
			String driverHost = method.getTestMethod().getXmlTest().getLocalParameters().get("driverHost");
			String driverPort = method.getTestMethod().getXmlTest().getLocalParameters().get("driverPort");
			String devicename = method.getTestMethod().getXmlTest().getLocalParameters().get("devicename");
			String appPath = method.getTestMethod().getXmlTest().getLocalParameters().get("appPath");
			String udID = method.getTestMethod().getXmlTest().getLocalParameters().get("udID");
			String hubUrl = method.getTestMethod().getXmlTest().getLocalParameters().get("hubUrl");
			String configFilePath = method.getTestMethod().getXmlTest().getLocalParameters().get("configFilePath");
			String signingID = method.getTestMethod().getXmlTest().getLocalParameters().get("signingID");
			String teamID = method.getTestMethod().getXmlTest().getLocalParameters().get("teamID");
			String accessKey = method.getTestMethod().getXmlTest().getLocalParameters().get("accessKey");
			String bundleID = method.getTestMethod().getXmlTest().getLocalParameters().get("bundleID");
			String appPackage = method.getTestMethod().getXmlTest().getLocalParameters().get("appPackage");
			String appActivity = method.getTestMethod().getXmlTest().getLocalParameters().get("appActivity");
			String projectName = method.getTestMethod().getXmlTest().getLocalParameters().get("projectName");
			// get and set new instance of local WebDriver
			log.info("getting driver for: " + browserName);
			WebDriver driver = null;
			try {
				driver = LocalDriverFactory.createInstance(driverHost, driverPort, browserName, configFilePath,
						signingID, teamID, accessKey, devicename, bundleID, appPath, udID, appPackage, projectName,
						appActivity);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			DriverManager.setWebDriver(driver);
			log.info("Done! Created " + browserName + " driver!");
		} else {
			log.warn("Creating the Driver!!!");
		}
		log.debug("END: org.stng.jbehave.LocalWebDriverListener.beforeInvocation");
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		log.debug("BEGINNING: org.stng.jbehave.LocalWebDriverListener.afterInvocation");
		if (method.isTestMethod()) {
			try {
				String browser = DriverManager.getBrowserInfo();
				// change the name of the test method that will appear in the report to one that
				// will contain
				// also browser name, version and OS.
				// very handy when analysing results.
				BaseTestMethod bm = (BaseTestMethod) testResult.getMethod();
				Field f = bm.getClass().getSuperclass().getDeclaredField("m_methodName");
				f.setAccessible(true);
				String newTestName = testResult.getTestContext().getCurrentXmlTest().getName() + " - "
						+ bm.getMethodName() + " - " + browser;
				log.info("Renaming test method name from: '" + bm.getMethodName() + "' to: '" + newTestName + "'");
				f.set(bm, newTestName);
			} catch (Exception ex) {
				log.error("afterInvocation exception:\n" + ex.getMessage());
				ex.printStackTrace();
			} finally {
				// close the browser
				WebDriver driver = null;
				try {
					driver = DriverManager.getDriver();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				if (driver != null) {
					driver.quit();
				}
			}
		}
		log.debug("END: org.stng.jbehave.LocalWebDriverListener.afterInvocation");
	}
}