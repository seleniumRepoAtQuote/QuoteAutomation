package org.temp.sampleTests;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessCBTMDA;
import org.quote.MdaTypes.ProcessMda;
import org.quote.MdaTypes.ProcessMobileDropDownMDA;
import org.quote.MdaTypes.processMobileMDA;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class SampleMobileTestRemote {
	static String username = "info%40360quotellc.com"; // Your username
	static String authkey = "u03ccde45a3cdabe"; // Your authkey
	String testScore = "unset";
	private static By allMdas = By.tagName("section");
	private static JsonSiteStructure jsonSiteObject;
	private static ArrayList<String> urlStringList;
	private static MDAConfig mdaConfig;
	private static ProcessMobileDropDownMDA mDAs1;
	private static ProcessMobileDropDownMDA mDAs;

	public static void main(String[] args) throws Exception {
		SampleMobileTestRemote myTest = new SampleMobileTestRemote();

		DesiredCapabilities capabilities = new DesiredCapabilities();

		/*
		 * caps.setCapability("name", "Check MDA on Android");
		 * caps.setCapability("build", "1.0"); caps.setCapability("browserName",
		 * "Chrome"); caps.setCapability("deviceName", "Galaxy S10");
		 * caps.setCapability("platformVersion", "10.0");
		 * caps.setCapability("platformName", "Android");
		 * caps.setCapability("deviceOrientation", "portrait");
		 * caps.setCapability("record_video", "true");
		 */

		capabilities.setCapability("name", "Check MDA on iPhone");
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone 12 Pro Max");
		capabilities.setCapability("platformVersion", "14.0");
		capabilities.setCapability("browserName", "Safari");
		capabilities.setCapability("build", "First Test");
		capabilities.setCapability("name", "Sample Test");
		capabilities.setCapability("network", true); // To enable network logs
		capabilities.setCapability("visual", true); // To enable step by step screenshot
		capabilities.setCapability("video", true); // To enable video recording
		capabilities.setCapability("console", true); // To capture console logs
		capabilities.setCapability("autoAcceptAlerts", true);
		capabilities.setCapability("safariAllowPopups", true);
		capabilities.setCapability("safariIgnoreFraudWarning", true);
		capabilities.setCapability("safariOpenLinksInBackground", true);

		URL url = new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub");
		AppiumDriver<AndroidElement> driver = new IOSDriver<AndroidElement>(url, capabilities);// new
																						// AndroidDriver<AndroidElement>(url,
																						// caps);
		// IOSDriver<AndroidElement> driver = new IOSDriver<AndroidElement>(url, caps);
		// RemoteWebDriver driver = new RemoteWebDriver(
//				new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		// System.out.println(driver.getSessionId());

		// we wrap the test in a try catch loop so we can log assert failures in our
		// system
		try {
			LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
			MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs("insuranceproviders.xlsx");
			driver.get("https://www.insuranceproviders.com/which-home-insurance-policy-is-best/");
			/*
			 * mDAs = new ProcessCBTMDA(driver, allMdas, 1);
			 * mDAs.processMobileGrayMDA(mdaConfig, driver);
			 */System.out.println(
					"validate_blueMDA " + "https://www.insuranceproviders.com/which-home-insurance-policy-is-best/");
			// driver.get(urlString);
			Thread.sleep(5000);
			// driver.manage().window().maximize();
			mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
			ProcessMda.execute_partial_BottomMDA = true;
			// mDAs.processMobileGrayMDA(mdaConfig, driver, "Auto");
			mDAs.processMobileGrayLikeBlueMDA(mdaConfig, driver, "Auto");
			System.out.println("Passed TestCAse");
			driver.quit();
			myTest.testScore = "pass";

		} catch (AssertionError ae) {

			// if we have an assertion error, take a snapshot of where the test fails
			// and set the score to "fail"
			String snapshotHash = myTest.takeSnapshot(driver.getSessionId().toString());
			myTest.setDescription(driver.getSessionId().toString(), snapshotHash, ae.toString());
			myTest.testScore = "fail";
		} finally {

			System.out.println("Test complete: " + myTest.testScore);

			// here we make an api call to actually send the score
			myTest.setScore(driver.getSessionId().toString(), myTest.testScore);

			// and quit the driver
			// driver.quit();
		}
	}

	public JsonNode setScore(String seleniumTestId, String score) throws UnirestException {
		// Mark a Selenium test as Pass/Fail
		HttpResponse<JsonNode> response = Unirest.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId).field("action", "set_score")
				.field("score", score).asJson();
		return response.getBody();
	}

	public String takeSnapshot(String seleniumTestId) throws UnirestException {
		/*
		 * Takes a snapshot of the screen for the specified test. The output of this
		 * function can be used as a parameter for setDescription()
		 */
		HttpResponse<JsonNode> response = Unirest
				.post("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId).asJson();
		// grab out the snapshot "hash" from the response
		String snapshotHash = (String) response.getBody().getObject().get("hash");

		return snapshotHash;
	}

	public JsonNode setDescription(String seleniumTestId, String snapshotHash, String description)
			throws UnirestException {
		/*
		 * sets the description for the given seleniemTestId and snapshotHash
		 */
		HttpResponse<JsonNode> response = Unirest
				.put("http://crossbrowsertesting.com/api/v3/selenium/{seleniumTestId}/snapshots/{snapshotHash}")
				.basicAuth(username, authkey).routeParam("seleniumTestId", seleniumTestId)
				.routeParam("snapshotHash", snapshotHash).field("description", description).asJson();
		return response.getBody();
	}

}
