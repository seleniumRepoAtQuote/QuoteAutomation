
package com.quote.dropDown.mobileMDATests;

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
import org.quote.MdaTypes.ProcessMobileDropDownMDA;
import org.quote.MdaTypes.ProcessMda;
import org.quote.mobileMDATests.SeleniumSetUpFunctions;
import org.quote.mobileMDATests.TestUtilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

public class Loans {
	private static AppiumDriver<AndroidElement> driver;
	private static By allMdas = By.tagName("section");
	private MDAConfig mdaConfig;
	boolean bit;
	private ArrayList<String> urlStringList;
	private static JsonSiteStructure jsonSiteObject;
	private ProcessMobileDropDownMDA mDAs;
	private ArrayList<String> dropDownList;
	private String fileName_Excel = "Loans.xlsx";

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
			dropDownList = mdaConfig.getDropDownList();
			urlStringList = jsonSiteObject.getUrlsList();
			for (String urlString : urlStringList) {

				for (String dropDownOption : dropDownList) {

					 //validate_headerMDA(urlString, dropDownOption);
					//validate_TopMDA(urlString, dropDownOption);
					//validate_grayMDA(urlString,dropDownOption);
					// validate_blueMDA(urlString, dropDownOption);

					validate_bottomMDA(urlString, dropDownOption);

				}

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

	public void validate_TopMDA(String urlString, String dropDownOption)
			throws MalformedURLException, InterruptedException {
		System.out.println("validate_TopMDA" + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(8000);
		// driver.manage().window().maximize();
		mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
		if (bit) {
			mDAs.processMobileHomePageTopMDA(mdaConfig, driver, dropDownOption);
		} else {
			mDAs.processMobileTopMDA(mdaConfig, driver, dropDownOption);
		}
	}

	public void validate_headerMDA(String urlString, String dropDownOption)
			throws MalformedURLException, InterruptedException {
		System.out.println("validate_headerMDA @@@@@@@@  " + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(5000);
		// driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processMobileHeaderMDA(mdaConfig, driver, dropDownOption);

	}

	public void validate_blueMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_blueMDA" + urlString);
		driver.get(urlString);
		Thread.sleep(5000);
		// driver.manage().window().maximize();
		mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processMobileGrayLikeBlueMDA(mdaConfig, driver, dropDownOption);
		//mDAs.processMobileBlueMDA(mdaConfig, driver, dropDownOption);
	}

	public void validate_grayMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_grayMDA" + urlString);
		driver.get(urlString);
		Thread.sleep(5000);
		// driver.manage().window().maximize();
		mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
		mDAs.processMobileGrayMDA(mdaConfig, driver, dropDownOption);
	}

	public void validate_bottomMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_bottomMDA" + urlString);
		driver.get(urlString);
		Thread.sleep(5000);
		// driver.manage().window().maximize();
		mDAs = new ProcessMobileDropDownMDA(driver, allMdas, 1);
		mDAs.processMobileBottomMDA(mdaConfig, driver, dropDownOption);
	}

	@AfterClass
	public void close() throws UnirestException {
		// SeleniumSetUpFunctions.closedriver();
	}
}
