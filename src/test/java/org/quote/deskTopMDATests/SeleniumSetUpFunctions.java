package org.quote.deskTopMDATests;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
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

public class SeleniumSetUpFunctions {
	private static RemoteWebDriver driver;
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

	public static RemoteWebDriver initializeRemoteEnv(String platform, String browserName, String version,
			String screenResolution, ITestContext ctx) throws IOException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("name", ctx.getCurrentXmlTest().getName());
		caps.setCapability("record_video", "true"); //
		caps.setCapability("record_network", "false");
		caps.setCapability("browserName", browserName);
		caps.setCapability("version", version);
		caps.setCapability("platform", platform);
		caps.setCapability("screenResolution", screenResolution);
		driver = new RemoteWebDriver(
				new URL("http://" + username + ":" + authkey + "@hub.crossbrowsertesting.com:80/wd/hub"), caps);
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
