
package org.temp.sampleTests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.python.modules.thread.thread;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.MdaTypes.ProcessMda;
import org.testng.Assert;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class Invalid_case {
	static String username = "info%40360quotellc.com"; // Your username
	static String authkey = "u03ccde45a3cdabe"; // Your authkey
	String testScore = "unset";
	private static By allMdas = By.tagName("section");

	public static void main(String[] args) throws UnirestException, IOException, InterruptedException {
		SampleTestRemote demoMDA = new SampleTestRemote();
		DesiredCapabilities caps = new DesiredCapabilities();

		// caps.setCapability("name", "Check MDA on Safari");
		// caps.setCapability("build", "1.0");
		// caps.setCapability("browserName", "safari");
		// caps.setCapability("version", "13");
		// caps.setCapability("platform", "Mac OSX 10.12");
		// caps.setCapability("screenResolution", "1366x768");

		/*
		 * caps.setCapability("name", "Check MDA on Safari");
		 * caps.setCapability("record_video", "true");
		 * caps.setCapability("record_network", "false");
		 * caps.setCapability("browserName", "Safari"); caps.setCapability("version",
		 * "14"); caps.setCapability("platform", "MacOS 11.0");
		 * caps.setCapability("screenResolution", "1366x768");
		 */

		/*
		 * caps.setCapability("name", "Check MDA on Chrome");
		 * caps.setCapability("record_video", "true");
		 * caps.setCapability("record_network", "false");
		 * caps.setCapability("browserName", "chrome"); caps.setCapability("version",
		 * "latest"); caps.setCapability("platform", "Windows 10");
		 * caps.setCapability("screenResolution", "1366x768");
		 */

		caps.setCapability("name", "Check MDA on firefox");
		caps.setCapability("record_video", "true");
		caps.setCapability("record_network", "false");
		caps.setCapability("browserName", "firefox");
		caps.setCapability("version", "latest");
		caps.setCapability("platform", "Windows 10");
		caps.setCapability("screenResolution", "1366x768");

		// ===>Mobile

		/*
		 * caps.setCapability("name", "Check MDA on Android");
		 * caps.setCapability("browserName", "Chrome"); caps.setCapability("deviceName",
		 * "Galaxy S10"); caps.setCapability("platformVersion", "10.0");
		 * caps.setCapability("platformName", "Android");
		 * caps.setCapability("deviceOrientation", "portrait");
		 * caps.setCapability("record_video", "true");
		 * caps.setCapability("record_network", "false");
		 */

		// caps.setCapability("browserName", "Chrome");
		// caps.setCapability("version", "72"); Mac OSX 10.12 Windows 10
		RemoteWebDriver driver = new RemoteWebDriver(
				new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
		System.out.println(driver.getSessionId());
		try {

			// load the page url
			System.out.println("Loading Url");
			driver.get("http://crossbrowsertesting.github.io/login-form.html");

			// maximize the window - DESKTOPS ONLY
			// System.out.println("Maximizing window");
			// driver.manage().window().maximize();

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
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logged-in-message\"]/h2")));

			// Let's assert that the welcome message is present on the page.
			// if not, an exception will be raised and we'll set the score to fail in the
			// catch block.
			String welcomeMessage = driver.findElementByXPath("//*[@id=\"logged-in-message\"]/h2").getText();
			Assert.assertEquals("Welcome tester@crossbrowsertesting.com", welcomeMessage);

			// Thread.sleep(9000);

			// driver.get("https://www.floridacarinsurance.com/car-insurance-in-fort-myers-fl-the-complete-guide/");
			driver.manage().window().maximize();

			/*
			 * driver.navigate().refresh(); Thread.sleep(9000); ((JavascriptExecutor)
			 * driver).executeScript("window.scrollBy(0,7000)"); Thread.sleep(9000); //
			 * driver.navigate().refresh(); // ((JavascriptExecutor)
			 * driver).executeScript("window.scrollBy(0,6000)"); //
			 * driver.navigate().refresh();
			 */
			/*
			 * wait = new WebDriverWait(driver, 30);
			 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
			 * "/html[1]/body[1]/allow-navigation[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[2]/div[1]/section[1]/div[1]/label[1]"
			 * )));
			 */
			// driver.navigate().refresh();
			// driver.navigate().refresh();
			// MdaBlue blue = new MdaBlue(driver, mdaBlueSection);
			// blue.printTitleOfMda();

			/*
			 * WebElement label = driver .findElement(By.
			 * xpath("//label[contains(text(),'Free Auto Insurance Comparison')]")); String
			 * string = label.getText(); while (string.isBlank()) {
			 * driver.navigate().refresh(); Thread.sleep(9000); }
			 */

			// mDAs.processHeaderMDA(mdaConfig, driver);

			LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();

			MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs("autoinsurance");
			ProcessMda mDAs; // = new ProcessMda(driver, allMdas, 1);

			driver.get("https://www.autoinsurance.org/best-companies/");
			driver.manage().window().maximize();
			mDAs = new ProcessMda(driver, allMdas, 1);
			mDAs.invalidCaseMDA(mdaConfig, driver);

			

			
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
