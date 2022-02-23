package org.temp.sampleTests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quote.DataTypes.MDAConfig;
import org.quote.LoadData.LoadDataFromExcel;
import org.quote.MdaTypes.ProcessMda;

public class SampleTest {
	private static WebDriver driver;
	private static By mdaBlueSection = By.xpath("//*[contains(@id,'mda_')]");
	private static By allMdas = By.tagName("section");

	public static void main(String[] args) throws IOException, InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Tayu\\JarsAndEXEs\\chromedriver_93\\chromedriver.exe");
		driver = new ChromeDriver();

		// driver.get("https://www.autoinsurance.org/cheap-auto-insurance/");
		LoadDataFromExcel dataFromExcel = new LoadDataFromExcel();

		driver.get("https://www.autoinsurance.org/best-companies/");
		driver.manage().window().maximize();

		// driver.navigate().refresh();
		// ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,6000)");
		// driver.navigate().refresh();
		// driver.navigate().refresh();

		// driver.navigate().refresh();
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,6000)");
		// driver.navigate().refresh();
		// driver.navigate().refresh();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"/html[1]/body[1]/allow-navigation[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[2]/div[1]/section[1]/div[1]/label[1]")));

		// driver.navigate().refresh();
		// driver.navigate().refresh();
		// MdaBlue blue = new MdaBlue(driver, mdaBlueSection);
		// blue.printTitleOfMda();
		MDAConfig mdaConfig = dataFromExcel.populateMdaConfigs("autoinsurance");
		ProcessMda mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processHeaderMDA(mdaConfig, driver);

		driver = new ChromeDriver();
		driver.get("https://www.autoinsurance.org/best-companies/");
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processTopMDA(mdaConfig, driver);

		driver = new ChromeDriver();
		driver.get("https://www.autoinsurance.org/best-companies/");
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processBottomMDA(mdaConfig, driver);

		driver = new ChromeDriver();
		driver.get("https://www.autoinsurance.org/best-companies/");
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processBlueMDA(mdaConfig, driver);

		driver = new ChromeDriver();
		driver.get("https://www.autoinsurance.org/best-companies/");
		mDAs = new ProcessMda(driver, allMdas, 1);
		mDAs.processGrayMDA(mdaConfig, driver);
	}
}
