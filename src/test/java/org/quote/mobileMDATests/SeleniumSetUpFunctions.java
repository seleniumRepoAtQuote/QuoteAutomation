package org.quote.mobileMDATests;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.DataTypes.PlatformConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessMda;
import org.testng.ITestContext;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SeleniumSetUpFunctions {
	private static final String USERNAME = "deeps_Y4a10G";
	private static final String AUTOMATE_KEY = "oTmoPxYJBscsisz9KRqj";
	private static AppiumDriver<AndroidElement> driver;
	ArrayList<PlatformConfig> platforms;
	ProcessMda mDAs;
	private static String username = "info%40360quotellc.com"; // Your username
	private static String authkey = "u03ccde45a3cdabe"; // Your authkey
	protected static String testScore = "fail";

	public static String getTestScore() {
		return testScore;
	}

	public static void setTestScore(String testScore) {
		SeleniumSetUpFunctions.testScore = testScore;
	}

	public static AppiumDriver<AndroidElement> initializeRemoteEnv(String browserName, String deviceName, String platformVersion,
			String platformName, String deviceOrientation, ITestContext ctx) throws IOException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("name", ctx.getCurrentXmlTest().getName());
		caps.setCapability("browserName", browserName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("platformName", platformName);
		caps.setCapability("deviceOrientation", deviceOrientation);
		caps.setCapability("record_video", "true");

		URL url = new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub");

		if (platformName.equalsIgnoreCase("iOs")) {
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("safariAllowPopups", true);
			caps.setCapability("safariIgnoreFraudWarning", true);
			caps.setCapability("safariOpenLinksInBackground", true);
			caps.setCapability("nativeWebTap", true);
			driver = new IOSDriver<AndroidElement>(url, caps);
		} else {
			driver = new AndroidDriver<AndroidElement>(url, caps);
		}

		/*
		 * driver = (AppiumDriver) new RemoteWebDriver( new URL("http://" + username +
		 * ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		 */

		System.out.println(driver.getSessionId());
		return driver;

	}

	public static AppiumDriver<AndroidElement> initializeRemoteEnvBrowserStack(String browserName, String deviceName,
			String platformVersion, String platformName, String deviceOrientation, ITestContext ctx)
			throws IOException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("name", ctx.getCurrentXmlTest().getName());
		caps.setCapability("browserName", browserName);
		caps.setCapability("deviceName", deviceName);
		caps.setCapability("platformVersion", platformVersion);
		caps.setCapability("platformName", platformName);
		caps.setCapability("deviceOrientation", deviceOrientation);
		caps.setCapability("record_video", "true");

		URL url = new URL("https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub");

		if (platformName.equalsIgnoreCase("iOs")) {
			caps.setCapability("autoAcceptAlerts", true);
			caps.setCapability("safariAllowPopups", true);
			caps.setCapability("safariIgnoreFraudWarning", true);
			caps.setCapability("safariOpenLinksInBackground", true);
			caps.setCapability("nativeWebTap", true);
			driver = new IOSDriver<AndroidElement>(url, caps);
		} else {
			driver = new AndroidDriver<AndroidElement>(url, caps);
		}

		/*
		 * driver = (AppiumDriver) new RemoteWebDriver( new URL("http://" + username +
		 * ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		 */

		System.out.println(driver.getSessionId());
		return driver;

	}

	public static void closedriver() throws UnirestException {

		driver.close();
		driver.quit();
	}

	public static JsonNode setScore(String seleniumTestId, String score) throws UnirestException {
		// Mark a Selenium test as Pass/Fail
		HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId).field("action", "set_score")
				.field("score", score).asJson();
		return response.getBody();
	}

}
