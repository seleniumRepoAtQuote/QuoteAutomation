
package org.quote.deskTopMDATests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;

import org.quote.MdaTypes.ProcessMda;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class LifeInsurancePost {
	private RemoteWebDriver driver;
	private static By allMdas = By.tagName("section");
	private MDAConfig mdaConfig;
	boolean bit;
	private ArrayList<String> urlStringList;
	private static JsonSiteStructure jsonSiteObject;
	private ProcessMda mDAs;
	private String fileName_Excel = "lifeinsurancepost.xlsx";

	@BeforeClass
	@org.testng.annotations.Parameters(value = { "platform", "browserName", "version", "screenResolution" })
	public void setup(String platform, String browserName, String version, String screenResolution,
			ITestContext context) throws IOException {
		driver = SeleniumSetUpFunctions.initializeRemoteEnv(platform, browserName, version, screenResolution, context);
	}

	@Test
	public void validate_SiteURLs() throws UnirestException, IOException, InterruptedException {
		try {
			LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
			HashMap<String, JsonSiteStructure> jsonSitesList = LoadJsonData.parseJson();
			jsonSiteObject = jsonSitesList.get(fileName_Excel);
			mdaConfig = dataFromExcel.populateMdaConfigs(jsonSiteObject.getConfig());
			urlStringList = jsonSiteObject.getUrlsList();
			for (String urlString : urlStringList) {
				validate_TopMDA(urlString);
				
				
				
				validate_headerMDAEnterButton(urlString);
				validate_headerMDA(urlString);
				
				//validate_TopMDA(urlString);
				validate_grayMDA(urlString);
				validate_blueMDA(urlString);
				validate_bottomMDA(urlString);
				// SeleniumSetUpFunctions.setTestScore("pass");

			}
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "pass");
			SeleniumSetUpFunctions.closedriver();

		} catch (IndexOutOfBoundsException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("Exception occured : ", e);
		}
		// demoMDA.testScore = "pass";
	}

	private void validate_headerMDAEnterButton(String urlString) throws MalformedURLException, InterruptedException {
		System.out.println("validate_headerMDA" + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessMda(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processHeaderMDAEnterButton(mdaConfig, driver);

	}

	public void validate_TopMDA(String urlString) throws MalformedURLException, InterruptedException {
		System.out.println("validate_TopMDA" + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		mDAs = new ProcessMda(driver, allMdas, 1);
		if (bit) {
			mDAs.processHomePageTopMDA(mdaConfig, driver);
		} else {
			mDAs.processTopMDA(mdaConfig, driver);
		}
	}

	public void validate_headerMDA(String urlString) throws MalformedURLException, InterruptedException {
		System.out.println("validate_headerMDA" + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessMda(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processHeaderMDA(mdaConfig, driver);

	}

	public void validate_blueMDA(String urlString) throws InterruptedException {
		System.out.println("validate_blueMDA" + urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processBlueMDA(mdaConfig, driver);
	}

	public void validate_grayMDA(String urlString) throws InterruptedException {
		System.out.println("validate_grayMDA" + urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processGrayMDA(mdaConfig, driver);
	}

	public void validate_bottomMDA(String urlString) throws InterruptedException {
		System.out.println("validate_bottomMDA" + urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processBottomMDA(mdaConfig, driver);
	}

	@AfterClass
	public void close() throws UnirestException {
		// SeleniumSetUpFunctions.closedriver();
	}
}
