package com.sph.driverFactory;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.appium.java_client.MobileDriver;

import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * A generic WebDriver manager, it works with local and remote instances of
 * WebDriver.
 *
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */
public class DriverManager {

	/*
	 * This simple line does all the mutlithread magic. For more details please
	 * refer to the src link above :)
	 */
	protected static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	static Logger log;

	static {
		log = Logger.getLogger(DriverManager.class);
	}

	public static WebDriver getDriver() throws MalformedURLException {
		if (driver.get() == null) {
			// this is need when running tests from IDE
			log.info("Thread has no WebDriver, creating new one");
			setWebDriver(LocalDriverFactory.createInstance(null, null, null, null, null, null, null, null, null, null,
					null, null, null, null));
		}
		log.debug("Getting instance of remote driver" + driver.get().getClass());
		return driver.get();
	}

	public static void setWebDriver(WebDriver driver) {
		// driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		DriverManager.driver.set(driver);
	}

	/**
	 * Returns a string containing current browser name, its version and OS name.
	 * This method is used in the the *WebDriverListeners to change the test name.
	 */
	public static String getBrowserInfo() throws MalformedURLException {
		log.debug("Getting browser info");
		// we have to cast WebDriver object to RemoteWebDriver here, because the first
		// one does not have a method
		// that would tell you which browser it is driving. (sick!)
		Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
		String b = cap.getBrowserName();
		String os = cap.getPlatform().toString();
		String v = cap.getVersion();
		return String.format("%s v:%s %s", b, v, os);
	}
}