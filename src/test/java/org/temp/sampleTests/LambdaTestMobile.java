package org.temp.sampleTests;

//<your package name>

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessMda;
import org.quote.MdaTypes.ProcessMobileDropDownMDA;
import org.quote.mobileMDATests.SeleniumSetUpFunctions;
import org.quote.mobileMDATests.TestUtilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class LambdaTestMobile {
	private static AppiumDriver<AndroidElement> driver;
	private static By allMdas = By.tagName("section");
	private MDAConfig mdaConfig;
	boolean bit;
	private ArrayList<String> urlStringList;
	private static JsonSiteStructure jsonSiteObject;
	private ProcessMobileDropDownMDA mDAs;
	private ArrayList<String> dropDownList;
	private String fileName_Excel = "insuranceproviders.xlsx";
	//public AppiumDriver<AndroidElement> driver = null;
	String username = "deeps.b2010";
	String accessKey = "87KEJhWUYsUF7AaMWPQ8JxCniLS2M8nAT8jCCGx7fP4OmBVgyF";
	//private ProcessMobileDropDownMDA mDAs;
	//private static By allMdas = By.tagName("section");

	@BeforeTest
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "iOS");
		capabilities.setCapability("deviceName", "iPhone 12 Pro Max");
		capabilities.setCapability("platformVersion","14.2");
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
		//capabilities.setCapability("nativeWebTap", true);
		//capabilities.setCapability("appiumVersion", "1.22.1");

		try {
			driver = new AppiumDriver<AndroidElement>(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		}
	}

	@Test(enabled = true)
	public void testScript() throws Exception {
		try {
			LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
			HashMap<String, JsonSiteStructure> jsonSitesList = LoadJsonData.parseJson();
			jsonSiteObject = jsonSitesList.get(fileName_Excel);
			mdaConfig = dataFromExcel.populateMdaConfigs(jsonSiteObject.getConfig());
			dropDownList = mdaConfig.getDropDownList();
			urlStringList = jsonSiteObject.getUrlsList();
			for (String urlString : urlStringList) {

				for (String dropDownOption : dropDownList) {

					validate_headerMDA(urlString, dropDownOption);
					// validate_TopMDA(urlString, dropDownOption);
					//validate_grayMDA(urlString,dropDownOption);
					// validate_blueMDA(urlString, dropDownOption);

					//validate_bottomMDA(urlString, dropDownOption);

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
}