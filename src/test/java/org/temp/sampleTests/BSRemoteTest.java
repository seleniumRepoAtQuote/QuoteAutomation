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
import org.quote.DataTypes.JsonSiteStructure;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.LoadData.LoadJsonData;
import org.quote.MdaTypes.ProcessMda;

import java.net.URL;
import java.util.HashMap;

public class BSRemoteTest {

	public static final String USERNAME = "deeps_Y4a10G";
	public static final String AUTOMATE_KEY = "oTmoPxYJBscsisz9KRqj";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	private static By allMdas = By.tagName("section");
	private static JsonSiteStructure jsonSiteObject;
	private static MDAConfig mdaConfig;

	public static void main(String[] args) throws Exception {
		DesiredCapabilities caps = new DesiredCapabilities();

		/*
		 * caps.setCapability("os", "Windows"); caps.setCapability("os_version", "10");
		 * caps.setCapability("browser", "Chrome");
		 * caps.setCapability("browser_version", "80"); caps.setCapability("name",
		 * "My First Test"); caps.setCapability("build", "Build #1");
		 * caps.setCapability("project", "Sample sandbox project");
		 */

		caps.setCapability("os", "OS X");
		caps.setCapability("os_version", "Big Sur");
		caps.setCapability("browser", "Safari");
		caps.setCapability("browser_version", "14.0");
		caps.setCapability("name", "SAFARI mda checker");
		caps.setCapability("browserstack.local", "false");
		caps.setCapability("browserstack.selenium_version", "3.14.0");
		caps.setCapability("project", "Sample OS_X sandbox project");

		WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		// driver.get("http://www.google.com");

		// Your test script like you usually write
		LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();
		driver.manage().window().maximize();
		// driver.get("https://www.autoinsurance.org/best-companies/");
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 10);
		 * wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "/html[1]/body[1]/allow-navigation[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[2]/div[1]/section[1]/div[1]/label[1]"
		 * )));
		 */

		HashMap<String, JsonSiteStructure> jsonSitesList = LoadJsonData.parseJson();
		jsonSiteObject = jsonSitesList.get("autoinsurance.xlsx");
		mdaConfig = dataFromExcel.populateMdaConfigs(jsonSiteObject.getConfig());

		// MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs("autoinsurance");
		ProcessMda mDAs = new ProcessMda(driver, allMdas, 1);

		// mDAs.processHeaderMDA(mdaConfig, driver);

		/*
		 * driver.get("https://www.autoinsurance.org/best-companies/");
		 * driver.manage().window().maximize(); mDAs = new ProcessMda(driver, allMdas,
		 * 1); mDAs.processTopMDA(mdaConfig, driver);
		 * 
		 * driver.get("https://www.autoinsurance.org/best-companies/");
		 * driver.manage().window().maximize(); mDAs = new ProcessMda(driver, allMdas,
		 * 1); mDAs.processBottomMDA(mdaConfig, driver);
		 * 
		 * driver.get("https://www.autoinsurance.org/best-companies/");
		 * driver.manage().window().maximize(); mDAs = new ProcessMda(driver, allMdas,
		 * 1); mDAs.processBlueMDA(mdaConfig, driver);
		 * 
		 * driver.get("https://www.autoinsurance.org/best-companies/");
		 * driver.manage().window().maximize(); mDAs = new ProcessMda(driver, allMdas,
		 * 1); mDAs.processGrayMDA(mdaConfig, driver);
		 */

		driver.get("https://www.autoinsurance.org/best-companies/");
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,7000)");
		mDAs = new ProcessMda(driver, allMdas, 1);
		ProcessMda.execute_partial_BottomMDA = true;
		mDAs.processHeaderMDA(mdaConfig, driver);
		driver.quit();
	}
}
