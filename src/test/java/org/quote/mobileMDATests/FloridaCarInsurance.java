package org.quote.mobileMDATests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessCBTMDA;
import org.quote.MdaTypes.ProcessMda;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class FloridaCarInsurance {
	private static AppiumDriver<AndroidElement> driver;
	private static By allMdas = By.tagName("section");
	private MDAConfig mdaConfig;
	boolean bit;
	private ArrayList<String> urlStringList;
	private static JsonSiteStructure jsonSiteObject;
	private ProcessCBTMDA mDAs;
	private String fileName_Excel = "floridacarinsurance.xlsx";

	@BeforeClass
	@org.testng.annotations.Parameters(value = { "browserName", "deviceName", "platformVersion", "platformName",
			"deviceOrientation" })
	public void setup(String browserName, String deviceName, String platformVersion, String platformName,
			String deviceOrientation, ITestContext context) throws IOException {

		/*
		 * if (platformName.equalsIgnoreCase("iOs")) { driverIPhone =
		 * (IOSDriver<AndroidElement>)
		 * SeleniumSetUpFunctions.initializeRemoteEnv(browserName, deviceName,
		 * platformVersion, platformName, deviceOrientation, context); driver =
		 * driverIPhone; } else { driverAndroid = (AndroidDriver<AndroidElement>)
		 * SeleniumSetUpFunctions.initializeRemoteEnv(browserName, deviceName,
		 * platformVersion, platformName, deviceOrientation, context); driver =
		 * driverAndroid; }
		 */

		/*
		 * driver = SeleniumSetUpFunctions.initializeRemoteEnv(browserName, deviceName,
		 * platformVersion, platformName, deviceOrientation, context);
		 */
		driver = SeleniumSetUpFunctions.initializeRemoteEnv(browserName, deviceName, platformVersion, platformName,
				deviceOrientation, context);
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
				// validate_headerMDAEnterButton(urlString);
				validate_headerMDA(urlString);
				/*
				 * validate_TopMDA(urlString); validate_grayMDA(urlString);
				 * validate_blueMDA(urlString); validate_bottomMDA(urlString);
				 * SeleniumSetUpFunctions.setTestScore("pass");
				 */
			}
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "pass");
			SeleniumSetUpFunctions.closedriver();

		} catch (NoAlertPresentException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("NoAlertPresentException occured : ", e);
		} catch (IndexOutOfBoundsException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("IndexOutOfBoundsException occured : ", e);
		} catch (UnreachableBrowserException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("UnreachableBrowserException occured : ", e);
		} catch (WebDriverException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("WebDriverException occured : ", e);
		}
		// demoMDA.testScore = "pass";
	}

	public void validate_TopMDA(String urlString) throws MalformedURLException, InterruptedException {
		System.out.println("validate_TopMDA" + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		// driver.manage().window().maximize();
		mDAs = new ProcessCBTMDA(driver, allMdas, 1);
		if (bit) {
			// mDAs.processHomePageTopMDA(mdaConfig, driver);
		} else {
			mDAs.processMobileTopMDA(mdaConfig, driver);
		}
	}

	public void validate_headerMDA(String urlString) throws MalformedURLException, InterruptedException {
		System.out.println("validate_headerMDA @@@@@@@@  " + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(5000);
		// driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessCBTMDA(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processMobileHeaderMDA(mdaConfig, driver);

	}

	public void validate_blueMDA(String urlString) throws InterruptedException {
		System.out.println("validate_blueMDA" + urlString);
		driver.get(urlString);
		// driver.manage().window().maximize();
		mDAs = new ProcessCBTMDA(driver, allMdas, 1);
		mDAs.processMobileBlueMDA(mdaConfig, driver);
	}

	public void validate_grayMDA(String urlString) throws InterruptedException {
		System.out.println("validate_grayMDA" + urlString);
		driver.get(urlString);
		// driver.manage().window().maximize();
		mDAs = new ProcessCBTMDA(driver, allMdas, 1);
		mDAs.processMobileGrayMDA(mdaConfig, driver);
	}

	public void validate_bottomMDA(String urlString) throws InterruptedException {
		System.out.println("validate_bottomMDA" + urlString);
		driver.get(urlString);
		// driver.manage().window().maximize();
		mDAs = new ProcessCBTMDA(driver, allMdas, 1);
		mDAs.processMobileBottomMDA(mdaConfig, driver);
	}

	@AfterClass
	public void close() throws UnirestException {
		// SeleniumSetUpFunctions.closedriver();
	}
}
