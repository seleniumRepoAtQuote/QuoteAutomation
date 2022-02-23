package org.temp.sampleTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.DataTypes.PlatformConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessMda;
import org.testng.Assert;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SampleTestRemote {
	static String username = "info%40360quotellc.com"; // Your username
	static String authkey = "u03ccde45a3cdabe"; // Your authkey
	String testScore = "unset";
	private static By allMdas = By.tagName("section");
	private static MDAConfig mdaConfig;
	private static ArrayList<String> urlStringList;
	private static ProcessMda mDAs;
	private static JsonSiteStructure jsonSiteObject;

	public static void main(String[] args) throws UnirestException, IOException, InterruptedException {
		SampleTestRemote demoMDA = new SampleTestRemote();
		LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();

		DesiredCapabilities caps = new DesiredCapabilities();
		HashMap<String, JsonSiteStructure> jsonSitesList = LoadJsonData.parseJson();
		ArrayList<PlatformConfig> platformConfigs = dataFromExcel.populatePlatformAndBrowsers();

		for (PlatformConfig platformConfig : platformConfigs) {

			System.out.println(platformConfig.getBrowser() + "\n" + platformConfig.getPlatform());

			caps.setCapability("name", "Check MDA on Chrome");
			caps.setCapability("record_video", "true");
			// caps.setCapability("record_network", "false");
			caps.setCapability("browserName", platformConfig.getBrowser());
			caps.setCapability("version", "latest");
			caps.setCapability("platform", platformConfig.getPlatform());
			caps.setCapability("screenResolution", "1366x768");
			RemoteWebDriver driver = new RemoteWebDriver(
					new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
			System.out.println(driver.getSessionId());
			try {

				// load the page url
				System.out.println("Loading Url");
				driver.get("http://crossbrowsertesting.github.io/login-form.html");

				driver.manage().window().maximize();

				// complete a short login form
				// first by entering the username
				System.out.println("Entering username");
				driver.findElementByName("username").sendKeys("tester@crossbrowsertesting.com");

				// then by entering the password
				System.out.println("Entering password");
				driver.findElementByName("password").sendKeys("test123");

				// then by clicking the login button
				System.out.println("Logging in");
				driver.findElementByCssSelector("div.form-actions > button").click();

				// let's wait here to ensure the page has loaded completely
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logged-in-message\"]/h2")));

				// Let's assert that the welcome message is present on the page.
				// if not, an exception will be raised and we'll set the score to fail in the
				// catch block.
				String welcomeMessage = driver.findElementByXPath("//*[@id=\"logged-in-message\"]/h2").getText();
				Assert.assertEquals("Welcome tester@crossbrowsertesting.com", welcomeMessage);

				// driver.get("https://www.floridacarinsurance.com/car-insurance-in-fort-myers-fl-the-complete-guide/");
				driver.manage().window().maximize();
				// for (JsonSiteStructure jsonSiteObject : jsonSitesList) {
				jsonSiteObject = jsonSitesList.get("autoinsurance.xlsx");
				mdaConfig = dataFromExcel.populateMdaConfigs(jsonSiteObject.getConfig());
				urlStringList = jsonSiteObject.getUrlsList();
				for (String urlString : urlStringList) {
					boolean bit;

					bit = isHomePage(urlString);
					driver.get(urlString);
					((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
					mDAs = new ProcessMda(driver, allMdas, 1);
					ProcessMda.execute_partial_BottomMDA = true;
					mDAs.processHeaderMDA(mdaConfig, driver);

					isHomePage(urlString);
					driver.get(urlString);
					driver.manage().window().maximize();
					mDAs = new ProcessMda(driver, allMdas, 1);
					if (bit) {
						mDAs.processHomePageTopMDA(mdaConfig, driver);
					} else {
						mDAs.processTopMDA(mdaConfig, driver);
					}

					driver.get(urlString);
					driver.manage().window().maximize();
					mDAs = new ProcessMda(driver, allMdas, 1);
					mDAs.processBottomMDA(mdaConfig, driver);

					driver.get(urlString);
					driver.manage().window().maximize();
					mDAs = new ProcessMda(driver, allMdas, 1);
					mDAs.processBlueMDA(mdaConfig, driver);

					driver.get(urlString);
					driver.manage().window().maximize();
					mDAs = new ProcessMda(driver, allMdas, 1);
					mDAs.processGrayMDA(mdaConfig, driver);

				}

				demoMDA.testScore = "pass";
			} catch (AssertionError ae) {

				// if we have an assertion error, take a snapshot of where the test fails
				// and set the score to "fail"
				String snapshotHash = demoMDA.takeSnapshot(driver.getSessionId().toString());
				demoMDA.setDescription(driver.getSessionId().toString(), snapshotHash, ae.toString());
				demoMDA.testScore = "fail";
			} finally {

				System.out.println("Test complete: " + demoMDA.testScore);

				// here we make an api call to actually send the score
				demoMDA.setScore(driver.getSessionId().toString(), demoMDA.testScore);

				// and quit the driver
				driver.quit();
			}
		}

	}

	private static boolean isHomePage(String urlString) throws MalformedURLException {
		URL aURL = new URL(urlString);
		if (aURL.getPath().equalsIgnoreCase("/")) {
			System.out.println("Homepage");
			return true;
		} else
			return false;
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
