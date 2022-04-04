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

public class SampleMobileTestRemote1 {
	static String username = "info%40360quotellc.com"; // Your username
	static String authkey = "u03ccde45a3cdabe"; // Your authkey
	String testScore = "unset";
	private static By allMdas = By.tagName("section");
	private static JsonSiteStructure jsonSiteObject;
	private static ArrayList<String> urlStringList;
	private static MDAConfig mdaConfig;
	private static ProcessMobileDropDownMDA mDAs1;
	private processMobileMDA mDAs;

	public static void main(String[] args) throws Exception {
		SampleMobileTestRemote myTest = new SampleMobileTestRemote();

		DesiredCapabilities caps = new DesiredCapabilities();

		/*
		 * caps.setCapability("name", "Check MDA on Android");
		 * caps.setCapability("build", "1.0"); caps.setCapability("browserName",
		 * "Chrome"); caps.setCapability("deviceName", "Galaxy S10");
		 * caps.setCapability("platformVersion", "10.0");
		 * caps.setCapability("platformName", "Android");
		 * caps.setCapability("deviceOrientation", "portrait");
		 * caps.setCapability("record_video", "true");
		 */

		caps.setCapability("name", "Check MDA on iPhone");
		caps.setCapability("build", "1.0");
		caps.setCapability("browserName", "Safari");
		caps.setCapability("deviceName", "iPhone 12 Pro Max");
		caps.setCapability("platformVersion", "14.0");
		caps.setCapability("platformName", "iOS");
		caps.setCapability("deviceOrientation", "portrait");
		caps.setCapability("record_video", "true");
		caps.setCapability("autoAcceptAlerts", true);

		caps.setCapability("safariAllowPopups", true);
		caps.setCapability("safariIgnoreFraudWarning", true);
		caps.setCapability("safariOpenLinksInBackground", true);
		caps.setCapability("nativeWebTap", true);

		URL url = new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub");
		AppiumDriver<IOSElement> driver = new IOSDriver<IOSElement>(url, caps);// new
																						// AndroidDriver<AndroidElement>(url,
																						// caps);
		// IOSDriver<AndroidElement> driver = new IOSDriver<AndroidElement>(url, caps);
		// RemoteWebDriver driver = new RemoteWebDriver(
//				new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		// System.out.println(driver.getSessionId());

		// we wrap the test in a try catch loop so we can log assert failures in our
		// system
		try {
			// load the page url
			System.out.println("Loading Url");
			driver.get("http://crossbrowsertesting.github.io/selenium_example_page.html");

			// Check the page title (try changing to make the assertion fail!)
			System.out.println("Checking title");
			assertEquals(driver.getTitle(), "Selenium Test Example Page");
			LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
			HashMap<String, JsonSiteStructure> jsonSitesList = LoadJsonData.parseJson();
			jsonSiteObject = jsonSitesList.get("insuranceproviders.xlsx");
			mdaConfig = dataFromExcel.populateMdaConfigs(jsonSiteObject.getConfig());
			urlStringList = jsonSiteObject.getUrlsList();
			ProcessCBTMDA mDAs;
			/* driver.get("https://www.autoinsurance.org/best-companies/"); */
			driver.get("https://www.insuranceproviders.com/which-home-insurance-policy-is-best/");
			//mDAs1 = new ProcessMobileDropDownMDA(driver, allMdas, 1);
			WebElement section = driver.findElement(By.xpath("(//section[@class='responsive-mda mda'])[12]"));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
			String sectionMdaID = section.getAttribute("id");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);

			WebElement dropDown = driver.findElement(By.xpath("(//select[@name='type'])[18]"));
			dropDown.click();
			
			dropDown.sendKeys("Auto");
		//	List<AndroidElement> allOptions = driver.findElements(By.xpath("//section[@id='"+sectionMdaID+"']//select[@name='type']//option"));
	       // System.out.println(allOptions.size());
	         
	                 
		/*
		 * for(int i = 0; i<=allOptions.size()-1; i++) {
		 * 
		 * String option = allOptions.get(i).getText(); if(option.contains("Auto")) {
		 * 
		 * allOptions.get(i).click(); break;
		 * 
		 * } }
		 */
			
			
			
			
			
			
			
			
			//WebElement dropDown = driver.findElement(By.xpath("(//select[@name='type'])[18]"));
			//WebElement dropDown = driver.findElement(By.xpath("//section[@id='"+sectionMdaID+"']//select[@name='type']"));
			
			Select dropdown = new Select(dropDown);
			System.out.println("DDOption ==> " + "Auto");
			dropdown.selectByValue("Auto");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);

			driver.quit();

			//mDAs = new ProcessCBTMDA(driver, allMdas, 1);
			ProcessMda.execute_partial_BottomMDA = true;
			//mDAs.processMobileHeaderMDA(mdaConfig, driver);

			// if we get to this point, then all the assertions have passed
			// that means that we can set the score to pass in our system
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
