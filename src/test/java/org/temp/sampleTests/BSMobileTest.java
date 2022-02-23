package org.temp.sampleTests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.MdaTypes.ProcessMda;
import org.quote.MdaTypes.processMobileMDA;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;

import java.net.URL;

public class BSMobileTest {

	public static final String USERNAME = "deeps_Y4a10G";
	public static final String AUTOMATE_KEY = "oTmoPxYJBscsisz9KRqj";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	private static By allMdas = By.tagName("section");

	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

		/*
		 * caps.setCapability("device", "Samsung Galaxy S10");
		 * caps.setCapability("os_version", "9.0"); caps.setCapability("project",
		 * "My First Project"); caps.setCapability("name", "Android MDA Checker");
		 * caps.setCapability("project", "My First Project"); caps.setCapability("name",
		 * "Android MDA Checker");
		 */

		caps.setCapability("os_version", "11");
		caps.setCapability("device", "iPhone X ");
		caps.setCapability("browserName", "Safari"); //
		caps.setCapability("real_mobile", "true");
		caps.setCapability("browserstack.local", "false");
		caps.setCapability("project", "My First Project");
		caps.setCapability("name", "iOS MDA Checker");

		// caps.setCapability("safariAllowPopups ", true);

		/*
		 * caps.setCapability("device", "iPhone X"); caps.setCapability("os", "iOS");
		 * caps.setCapability("os_version", "11"); caps.setCapability("browser",
		 * "Safari");
		 */

		URL url = new URL("https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub");
		// AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(url,
		// caps);
		AppiumDriver<WebElement> driver = new AppiumDriver<WebElement>(url, caps);

		// WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		// driver.get("http://www.google.com");

		// Your test script like you usually write
		LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
		MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs("autoinsurance.xlsx");
		processMobileMDA mDAs;
		// driver.get("https://www.autoinsurance.org/best-companies/");
		// MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs();
		/*
		 * driver.get("https://www.autoinsurance.org/best-companies/"); processMobileMDA
		 * mDAs = new processMobileMDA(driver, allMdas, 1); ((JavascriptExecutor)
		 * driver).executeScript("window.scrollBy(0,7000)");
		 * mDAs.processTopMDA(mdaConfig, driver);
		 */

		/*
		 * driver.get("https://www.autoinsurance.org/best-companies/"); mDAs = new
		 * processMobileMDA(driver, allMdas, 1); mDAs.processMobileBottomMDA(mdaConfig,
		 * driver); if (processMobileMDA.execute_partial_bottomMobileMDA == true)
		 * mDAs.processMobileBottomMDA(mdaConfig, driver);
		 * 
		 * driver.get("https://www.autoinsurance.org/best-companies/"); mDAs = new
		 * processMobileMDA(driver, allMdas, 1); mDAs.processBlueMDA(mdaConfig, driver);
		 * 
		 * driver.get("https://www.autoinsurance.org/best-companies/"); mDAs = new
		 * processMobileMDA(driver, allMdas, 1); mDAs.processGrayMDA(mdaConfig, driver);
		 */

		driver.get("https://www.autoinsurance.org/best-companies/");
		mDAs = new processMobileMDA(driver, allMdas, 1);
		processMobileMDA.execute_partial_bottomMobileMDA = true;
		mDAs.processMobileHeaderMDA(mdaConfig, driver);

		// driver.quit();
	}
}
