package com.sph.driverFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.*;

import org.apache.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.remote.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Based on the LocalDriverFactory found at: onrationaleemotions.wordpress.com
 * 
 * @author: Confusions Personified
 * @src: http://rationaleemotions.wordpress.com/2013/07/31/parallel-webdriver-executions-using-testng/
 */

public class LocalDriverFactory {
	public static final String USERNAME = "pchopra";
	public static final String ACCESS_KEY = "";
	static Logger log = Logger.getLogger(LocalDriverFactory.class);
	public static WebDriver createInstance(String driverHost, String driverPort, String browserName,
			String configFilePath, String signingID, String teamID, String accessKey, String devicename,
			String bundleID, String appPath, String udID, String appPackage, String projectName, String appActivity)
			throws MalformedURLException {
		WebDriver driver;
		DesiredCapabilities capability;
		String localUrl = "http://" + driverHost + ":" + driverPort + "/wd/hub";
		String remoteUrl = "https://" + driverHost + ":" + driverPort + "/wd/hub";
		browserName = (browserName != null) ? browserName : "chromelocal";
	 
		switch (Browser.valueOf(browserName.toUpperCase())) {

			case SAFARILOCAL:
				driver = new SafariDriver();
				break;
			case FIREFOXLOCAL:
				//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "/src/test/resources/appDrivers/geckodriver");
				driver = new FirefoxDriver();
				break;
			case SAFARIREMOTE:
				capability = DesiredCapabilities.safari();
				capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.SAFARI);
				capability.setCapability(CapabilityType.VERSION, "Any");
				capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
				capability.setCapability("accessKey", accessKey);
				capability.setCapability("testName", "Quick Start Safari Browser Demo");
				driver = new RemoteWebDriver(new URL(remoteUrl), capability);
				break;

			case IE:
				driver = new InternetExplorerDriver();
				break;
			case CHROMEREMOTE:
				capability = DesiredCapabilities.chrome();
				capability.setCapability("testName", "Launch Chrome driver for testing");
				capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
				capability.setCapability(CapabilityType.VERSION, "Any");
				capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
				capability.setCapability("accessKey", accessKey);
				System.out.printf("Remote URL is "+remoteUrl);
				driver = new RemoteWebDriver(new URL(remoteUrl), capability);

				System.out.printf("Remote Chrome Driver is returned");
				break;
			case FIREFOXREMOTE:
				capability = DesiredCapabilities.chrome();
				capability.setCapability("testName", "Launch FireFox driver for testing");
				capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
				capability.setCapability(CapabilityType.VERSION, "Any");
				capability.setCapability(CapabilityType.PLATFORM, Platform.ANY);
				capability.setCapability("accessKey", accessKey);
				System.out.printf("Remote URL is "+remoteUrl);
				driver = new RemoteWebDriver(new URL(remoteUrl), capability);
				System.out.printf("Remote FireFox Driver is returned");
				break;
			case CHROMELOCAL:
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/resources/appDrivers/chromedriver");
				driver = new ChromeDriver();
				System.out.printf("Local Chrome Driver is returned");
				break;
			case CHROMEHEADLESS:
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "/src/test/resources/appDrivers/chromedriver");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("--headless");
				driver = new ChromeDriver(chromeOptions);
				System.out.printf("Local Chrome Driver is returned");
				break;
			case ANDROIDLOCAL:
				capability = DesiredCapabilities.android();
				System.out.println("Browser is "+browserName);
				capability.setCapability(MobileCapabilityType.PLATFORM_NAME, "android");
				capability.setCapability(MobileCapabilityType.NO_RESET, true);
				capability.setCapability(MobileCapabilityType.TAKES_SCREENSHOT,
						true);
				capability.setCapability(MobileCapabilityType.ACCEPT_SSL_CERTS,
						true);
				capability.setCapability("deviceName",
						devicename);
				capability.setCapability(MobileCapabilityType.APP,appPath);
				capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_PACKAGE,appPackage);
				capability.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, appActivity);

				System.out.println("URL is "+localUrl);
				driver = new AndroidDriver<>(new URL(localUrl), capability);
				System.out.printf("Local Android driver is returned");
				break;
			case ANDROIDREMOTE:
				capability = DesiredCapabilities.android();
				capability.setCapability("testName", "Launch Android device to test the application");
				capability.setCapability("deviceQuery", "@os='android'");
				capability.setCapability("reportDirectory", "reports");
				capability.setCapability("reportFormat", "xml");
				capability.setCapability("accessKey", accessKey);
				capability.setCapability("project", projectName);
				capability.setCapability(MobileCapabilityType.APP, appPath);
				capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, appPackage);
				capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, appActivity);
				//capability.setCapability("appVersion","3.22.2");
				capability.setCapability("instrumentApp", true);
				driver = new AndroidDriver<>(new URL(remoteUrl), capability);

				System.out.printf("Remote Android driver is returned");
				break;
			case ANDROIDLOCALBROWSER:
				capability = DesiredCapabilities.android();
				// set the capability to execute test in chrome browser
				capability.setCapability("browserName","CHROME");
				capability.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
				capability.setCapability("deviceName",
						devicename);
				System.setProperty("webdriver.chrome.driver", "/appDrivers/chromedriver");
				driver = new AppiumDriver<>(new URL(remoteUrl), capability);
				System.out.printf("Local Android Browser is returned");
				break;
			case ANDROIDREMOTEBROWSER:
				capability = DesiredCapabilities.android();
				capability.setCapability("testName", "Quick Start Android Browser Demo");
				capability.setCapability("accessKey", accessKey);
				//capability.setCapability("project", projectName);
				capability.setCapability("deviceQuery", "@os='android' and @category='PHONE'");
				capability.setBrowserName(MobileBrowserType.CHROMIUM);
				driver = new AndroidDriver<>(new URL(remoteUrl), capability);
				System.out.printf("Remote Android Browser is returned");
				break;

			case IOSREMOTEBROWSER:
				capability = DesiredCapabilities.iphone();
				capability.setCapability("testName", "Quick Start iOS Browser Demo");
				capability.setCapability("accessKey", accessKey);
				capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
				capability.setBrowserName(MobileBrowserType.SAFARI);
				driver = new IOSDriver<>(new URL(remoteUrl), capability);
				System.out.printf("Remote iOS Browser is returned");
				break;

			case IOSLOCAL:
				capability = DesiredCapabilities.iphone();
				capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
				//capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
				capability.setCapability(MobileCapabilityType.FULL_RESET, true);
				capability.setCapability(MobileCapabilityType.APP, appPath);
				capability.setCapability(MobileCapabilityType.UDID,udID);
				capability.setCapability("securityToken", "myToken");
				capability.setCapability("deviceName", devicename);
				capability.setCapability("automationName","XCUITest");
				capability.setCapability("xcodeConfigFile", configFilePath);
				capability.setCapability("xcodeOrgId",teamID);
				capability.setCapability("instrumentApp", true);
				capability.setCapability("xcodeSigningId",signingID);
				capability.setCapability("showXcodeLog","true");
				capability.setCapability("agentPath", "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent/WebDriverAgent.xcodeproj");
				capability.setCapability("bootstrapPath", "/Applications/Appium.app/Contents/Resources/app/node_modules/appium/node_modules/appium-xcuitest-driver/WebDriverAgent");
				driver = new AppiumDriver<>(new URL(localUrl), capability);
				System.out.printf("Local iOSdriver is returned");
				break;
			case IOSREMOTE:
				capability = DesiredCapabilities.iphone();
				capability.setCapability("reportDirectory", "reports");
				capability.setCapability("reportFormat", "xml");
				capability.setCapability("accessKey", accessKey);
				//capability.setCapability("deviceQuery", "@os='ios' and @category='PHONE'");
				capability.setCapability(MobileCapabilityType.APP, appPath);
				capability.setCapability("instrumentApp", true);
				capability.setCapability("project", projectName);
				capability.setCapability(IOSMobileCapabilityType.BUNDLE_ID, bundleID);
				capability.setCapability(MobileCapabilityType.FULL_RESET, true);
				capability.setCapability("autoGrantPermissions", "true");
				capability.setCapability("autoAcceptAlerts", "true");
				capability.setCapability(MobileCapabilityType.ORIENTATION, "Portrait");
				capability.setCapability(MobileCapabilityType.UDID, udID);
				capability.setCapability("accessKey",accessKey);
				driver = new IOSDriver<>(new URL (remoteUrl), capability);
				System.out.printf("Remote iOSdriver is returned");
				break;
			

		default:
			driver = new ChromeDriver();
			break;
		}
		return driver;

	}
	

	private static enum Browser {
		SAFARILOCAL, SAFARIREMOTE,FIREFOXLOCAL,FIREFOXREMOTE, CHROMEHEADLESS, CHROMEREMOTE, IE, ANDROIDLOCAL, ANDROIDREMOTE, CHROMELOCAL, IOSREMOTEBROWSER, IOSLOCAL, IOSREMOTE, IOSLOCALSIMULATOR, ANDROIDREMOTEBROWSER, ANDROIDLOCALBROWSER;
	}
}