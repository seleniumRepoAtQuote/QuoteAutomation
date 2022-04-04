
package com.quote.dropDown.deskTopMDATests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessDropDownMDA;
import org.quote.MdaTypes.ProcessMda;
import org.quote.deskTopMDATests.SeleniumSetUpFunctions;
import org.quote.deskTopMDATests.TestUtilities;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mashape.unirest.http.exceptions.UnirestException;

public class Loans {
	private RemoteWebDriver driver;
	private static By allMdas = By.tagName("section");
	private MDAConfig mdaConfig;
	boolean bit;
	private ArrayList<String> urlStringList;
	private static JsonSiteStructure jsonSiteObject;
	private ProcessDropDownMDA mDAs;
	private ArrayList<String> dropDownList;
	private String fileName_Excel = "Loans.xlsx";

	@BeforeClass
	@org.testng.annotations.Parameters(value = { "platform", "browserName", "version", "screenResolution" })
	public void setup(String platform, String browserName, String version, String screenResolution,
			ITestContext context) throws IOException {
		driver = SeleniumSetUpFunctions.initializeRemoteEnv(platform, browserName, version, screenResolution, context);
		// driver = SeleniumSetUpFunctions.initializeRemoteEnvBrowserStack(platform,
		// browserName, version, screenResolution, context);
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
				// validate_TopMDA(urlString);

				// validate_headerMDAEnterButton(urlString);
				// validate_headerMDA(urlString);

				//
				for (String dropDownOption : dropDownList) {

					validate_headerMDA(urlString, dropDownOption);
					validate_TopMDA(urlString, dropDownOption);
					validate_grayMDA(urlString, dropDownOption);
					validate_TopMDAEnterButton(urlString, dropDownOption);
					validate_blueMDA(urlString, dropDownOption);
					validate_bottomMDA(urlString, dropDownOption);

				}

				// SeleniumSetUpFunctions.setTestScore("pass");

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
		} catch (NoSuchElementException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("NoSuchElementException occured : ", e);
		} catch (WebDriverException e) {
			SeleniumSetUpFunctions.setScore(driver.getSessionId().toString(), "fail");
			SeleniumSetUpFunctions.closedriver();

			Assert.fail("WebDriverException occured : ", e);
		}
	}

	private void validate_bottomMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_bottomMDA " + urlString);
		driver.get(urlString);
		Thread.sleep(8000);
		driver.manage().window().maximize();
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		mDAs.processBottomMDA(mdaConfig, driver, dropDownOption);

	}

	private void validate_blueMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_blueMDA " + urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		mDAs.processBlueMDA(mdaConfig, driver, dropDownOption);

	}

	private void validate_grayMDA(String urlString, String dropDownOption) throws InterruptedException {
		System.out.println("validate_grayMDA" + urlString);
		driver.get(urlString);
		driver.manage().window().maximize();
		Thread.sleep(5000);
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		mDAs.processGrayMDA(mdaConfig, driver, dropDownOption);
	}

	private void validate_headerMDA(String urlString, String dropDownOption)
			throws InterruptedException, MalformedURLException {
		System.out.println("validate_headerMDA " + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(8000);
		driver.manage().window().maximize();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processHeaderMDA(mdaConfig, driver, dropDownOption);

	}

	private void validate_TopMDAEnterButton(String urlString, String dropDownOption)
			throws MalformedURLException, InterruptedException {
		System.out.println("validate_TopMDAEnterButton " + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(8000);
		driver.manage().window().maximize();
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		//ProcessMda.execute_partial_BottomMDA = true;
		if (bit) {
			mDAs.processHomePageTopMDAEnterButton(mdaConfig, driver, dropDownOption);
		} else {
			mDAs.processTopMDAEnterButton(mdaConfig, driver, dropDownOption);
		}

	}

	private void validate_TopMDA(String urlString, String dropDownOption)
			throws InterruptedException, MalformedURLException {
		System.out.println("validate_TopMDA " + urlString);
		bit = TestUtilities.isHomePage(urlString);
		driver.get(urlString);
		Thread.sleep(8000);
		driver.manage().window().maximize();
		mDAs = new ProcessDropDownMDA(driver, allMdas, 1);
		if (bit) {
			mDAs.processHomePageTopMDA(mdaConfig, driver, dropDownOption);
		} else {
			mDAs.processTopMDA(mdaConfig, driver, dropDownOption);
		}
	}

}
