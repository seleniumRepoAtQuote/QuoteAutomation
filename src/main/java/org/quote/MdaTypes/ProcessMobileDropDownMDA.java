
package org.quote.MdaTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quote.DataTypes.MDAConfig;
import org.quote.abstractWebSection.AbstractComponents;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ProcessMobileDropDownMDA extends AbstractComponents {
	AppiumDriver<AndroidElement> driver;
	By title = By.xpath("//section[1]/div[1]/h2[1]");
	By mdaSectionToCheckId = By.tagName("section");

	String title_gray = "Enter your ZIP code below to view companies that have cheap auto insurance rates.";
	String title_blues = "Free Auto Insurance Comparison";
	String text_blue = "Enter your ZIP code below to view companies that have cheap auto insurance rates.";
	public static boolean execute_partial_TopMDA = false;
	public static boolean execute_partial_bottomMobileMDA = false;

	public ProcessMobileDropDownMDA(AppiumDriver<AndroidElement> driver, By sectionElement) {
		super(driver, sectionElement);
	}

	public ProcessMobileDropDownMDA(AndroidDriver<AndroidElement> driver2, By sectionElements, int i) {
		super(driver2, sectionElements, 1);
	}

	public ProcessMobileDropDownMDA(AppiumDriver<AndroidElement> driver2, By sectionElements, int i) {
		super(driver2, sectionElements, 1);
	}

	public ProcessMobileDropDownMDA(IOSDriver<AndroidElement> driver2, By sectionElements, int i) {
		super(driver2, sectionElements, 1);
	}
	

	public void printTitleOfMda() {
		String str = findElement(title).getText();
		System.out.println(str);
		str = findElement(mdaSectionToCheckId).getAttribute("id");
		System.out.println(str);
	}

	public void printMDA() {
		WebElement webElement = sectionElements.get(4);
		String str = webElement.getAttribute("class");
		System.out.println(str);
		str = webElement.getAttribute("id");
		System.out.println(str);
		WebElement h2 = webElement.findElement(By.tagName("h2"));
		WebElement p = webElement.findElement(By.tagName("p"));
		System.out.println(h2.getText() + "   --- " + p.getText());
		return;
	}

	public void printAllMdas() {
		for (WebElement webElement : sectionElements) {
			String str = webElement.getAttribute("class");
			System.out.println(str);
			str = webElement.getAttribute("id");
			System.out.println(str);
			WebElement h2 = webElement.findElement(By.tagName("h2"));
			WebElement p = webElement.findElement(By.tagName("p"));
			System.out.println(h2.getText() + "   --- " + p.getText());
		}
		return;
		/*
		 * WebElement mda = sectionElements.get(4); String str =
		 * mda.getAttribute("class"); System.out.println(str); str =
		 * mda.getAttribute("id"); System.out.println(str); String h2 =
		 * mda.getAttribute("h2"); String p = mda.getAttribute("p");
		 * System.out.println(h2 + "   --- " + p);
		 */

	}

	/*
	 * public void processHeaderMDA(MDAConfig mdaConfig, WebDriver driver) throws
	 * InterruptedException {
	 * 
	 * this.processTopMDA(mdaConfig, driver); WebElement headerMDA =
	 * sectionElements.get(0); WebElement label =
	 * headerMDA.findElement(By.tagName("label")); //
	 * xpath("//label[contains(text(),'Free Auto Insurance Comparison')]")); //
	 * ("//label[contains(text(),'Free Auto Insurance Comparison')]"));
	 * System.out.println(label.getText()); String originalHandle =
	 * driver.getWindowHandle(); System.out.println(label.getText() +
	 * " LabelText=======================================>"); if
	 * (label.getText().equalsIgnoreCase(mdaConfig.getMdaTextHeader())) {
	 * System.out.println("Header MDA text matched "); // WebElement zipWebElement =
	 * // headerMDA.findElement(By.xpath("//div[1]/form[1]/div[1]/input[1]"));
	 * WebElement zipWebElement = headerMDA.findElement(By.name("zipcode"));
	 * zipWebElement.sendKeys(mdaConfig.getZipCode().get(0)); //
	 * zipWebElement.sendKeys(mdaConfig.getZipCode().get(0)); WebElement
	 * getQuoteButton =
	 * headerMDA.findElement(By.xpath("//div[1]/form[1]/div[2]/input[1]"));
	 * getQuoteButton.click(); ArrayList<String> tabs = new
	 * ArrayList<String>(driver.getWindowHandles()); // switch to new tab
	 * Thread.sleep(500); driver.switchTo().window(tabs.get(0)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match"); // switch to parent window
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match"); } else {
	 * System.out.println("Header MDA text did not match"); } for (String handle :
	 * driver.getWindowHandles()) { if (!handle.equals(originalHandle)) {
	 * driver.switchTo().window(handle); driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.navigate().back(); //
	 * driver.close();
	 * System.out.println("processHeaderMDA Done ==>#############################");
	 * return; }
	 */
	/*
	 * public void processTopMDA(MDAConfig mdaConfig, WebDriver driver) throws
	 * InterruptedException { System.out.println(sectionElements.size() +
	 * "sections size in topMDA function"); String originalHandle =
	 * driver.getWindowHandle(); WebElement topMDA = sectionElements.get(1);
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
	 * Thread.sleep(800); WebElement h2 = topMDA.findElement(By.tagName("h2"));
	 * System.out.println(h2.getText() + " h2 "); if
	 * (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
	 * System.out.println("Titles matched"); WebElement zipWebElement =
	 * topMDA.findElement(By.name("zipcode"));
	 * zipWebElement.sendKeys(mdaConfig.getZipCode().get(0)); if
	 * (ProcessMda.execute_partial_BottomMDA == true) { return; } WebElement
	 * getQuoteButton = topMDA.findElement(By.className("mda-submit"));
	 * Thread.sleep(5000); getQuoteButton.click(); Thread.sleep(5000);
	 * ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles()); //
	 * switch to new tab
	 * 
	 * driver.switchTo().window(tabs.get(0)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match"); // switch to parent window
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match"); } else
	 * System.out.println("Titles not matched."); for (String handle :
	 * driver.getWindowHandles()) { if (!handle.equals(originalHandle)) {
	 * driver.switchTo().window(handle); driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.close();
	 * System.out.println("processTopMDA Done ==>#############################"); }
	 */
	/*
	 * public void processBottomMDA(MDAConfig mdaConfig, WebDriver driver) throws
	 * InterruptedException { String originalHandle = driver.getWindowHandle();
	 * 
	 * System.out.println("==================================" +
	 * sectionElements.size());
	 * 
	 * WebElement bottomMDA = sectionElements.get(sectionElements.size() - 1);
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", bottomMDA);
	 * Thread.sleep(800); WebElement h2 = bottomMDA.findElement(By.tagName("h2"));
	 * System.out.println(h2.getText() + " h2 "); WebElement p =
	 * bottomMDA.findElement(By.tagName("p")); System.out.println(p.getText() +
	 * " <p> "); if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
	 * System.out.println("Titles matched"); WebElement zipWebElementB = bottomMDA
	 * .findElement(By.xpath(
	 * "//*[@id=\"mdabottom\"]/div/section/div/form/div[1]/input")); //
	 * By.name("zipcode")); // ("//div[1]/form[1]/div[1]/input[1]")); // WebElement
	 * zipWebElementB = bottomMDA.findElement(By.tagName("input"));
	 * zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0)); Thread.sleep(5000);
	 * WebElement getQuoteButton = bottomMDA .findElement(By.xpath(
	 * "//*[@id=\"mdabottom\"]/div/section/div/form/div[2]/input")); //
	 * className("mda-submit")); // ("//div[1]/form[1]/div[2]/input[1]"));
	 * getQuoteButton.click(); Thread.sleep(500); ArrayList<String> tabs = new
	 * ArrayList<String>(driver.getWindowHandles()); // switch to new tab
	 * 
	 * driver.switchTo().window(tabs.get(0)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match"); // switch to parent window
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match"); } else
	 * System.out.println("Titles not matched."); for (String handle :
	 * driver.getWindowHandles()) { if (!handle.equals(originalHandle)) {
	 * driver.switchTo().window(handle); driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.close();
	 * System.out.println("processBottomMDA Done ==>#############################");
	 * }
	 */

//responsive-mda mda

	/*
	 * public void processBlueMDA(MDAConfig mdaConfig, WebDriver driver) throws
	 * InterruptedException { String originalHandle = driver.getWindowHandle();
	 * WebElement blueMDA = null; for (int i = sectionElements.size() - 2; i > 2;
	 * i--) {
	 * 
	 * blueMDA = sectionElements.get(i); if
	 * (blueMDA.getAttribute("class").equalsIgnoreCase("responsive-mda mda")) {
	 * System.out.println(blueMDA.getAttribute("class") + " Class"); break; } } if
	 * (blueMDA == null) { System.out.println("No blue MDA found!"); return; }
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
	 * Thread.sleep(800); WebElement h2 = blueMDA.findElement(By.tagName("h2"));
	 * System.out.println(h2.getText() + " h2 "); WebElement p =
	 * blueMDA.findElement(By.tagName("p")); System.out.println(p.getText() +
	 * " <p> "); if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
	 * System.out.println("Titles matched"); WebElement zipWebElementB =
	 * blueMDA.findElement(By.name("zipcode")); //
	 * ("//div[1]/form[1]/div[1]/input[1]")); // WebElement zipWebElementB //
	 * =bottomMDA.findElement(By.tagName("input"));
	 * zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0)); Thread.sleep(5000);
	 * WebElement getQuoteButton = blueMDA.findElement(By.className("mda-submit"));
	 * // ("//div[1]/form[1]/div[2]/input[1]")); getQuoteButton.click();
	 * ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
	 * Thread.sleep(500); driver.switchTo().window(tabs.get(0)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match");
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match"); } else
	 * System.out.println("Titles not matched."); for (String handle :
	 * driver.getWindowHandles()) { if (!handle.equals(originalHandle)) {
	 * driver.switchTo().window(handle); driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.close();
	 * System.out.println("processBlueMDA Done ==>#############################"); }
	 */

//alt-responsive-mda mda alt-shortcode
	/*
	 * public void processGrayMDA(MDAConfig mdaConfig, WebDriver driver) throws
	 * InterruptedException { String originalHandle = driver.getWindowHandle();
	 * WebElement grayMDA = null; for (int i = sectionElements.size() - 4; i > 4;
	 * i--) {
	 * 
	 * grayMDA = sectionElements.get(i); if (grayMDA.getAttribute("class").
	 * equalsIgnoreCase("alt-responsive-mda mda alt-shortcode")) {
	 * System.out.println(grayMDA.getAttribute("class") + " Class"); break; } } if
	 * (grayMDA == null) { System.out.println("No Gray MDA found"); return; }
	 * ((JavascriptExecutor)
	 * driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
	 * Thread.sleep(800);
	 * 
	 * WebElement h2 = grayMDA.findElement(By.tagName("h2"));
	 * System.out.println(h2.getText() + " h2 ");
	 * 
	 * WebElement p = grayMDA.findElement(By.tagName("p"));
	 * System.out.println(p.getText() + " <p> "); if
	 * (p.getText().equalsIgnoreCase(mdaConfig.getMdaText())) {
	 * System.out.println("<p> text matched"); WebElement zipWebElementB =
	 * grayMDA.findElement(By.name("zipcode")); //
	 * ("//div[1]/form[1]/div[1]/input[1]")); // WebElement zipWebElementB =
	 * bottomMDA.findElement(By.tagName("input"));
	 * zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0)); Thread.sleep(5000);
	 * WebElement getQuoteButton = grayMDA.findElement(By.className("mda-submit"));
	 * // ("//div[1]/form[1]/div[2]/input[1]")); getQuoteButton.click();
	 * ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles()); //
	 * switch to new tab Thread.sleep(500); driver.switchTo().window(tabs.get(0));
	 * if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match"); // switch to parent window
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match"); } else
	 * System.out.println("Titles not matched."); for (String handle :
	 * driver.getWindowHandles()) { if (!handle.equals(originalHandle)) {
	 * driver.switchTo().window(handle); driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.close();
	 * System.out.println("processGrayMDA Done ==>#############################"); }
	 */

	/*
	 * public void processMobileHeaderMDA(MDAConfig mdaConfig, WebDriver driver)
	 * throws InterruptedException {
	 * 
	 * Thread.sleep(9000); System.out.println(sectionElements.size() +
	 * " Size of total MDAs"); WebElement headerMDA = sectionElements.get(0);
	 * 
	 * String originalHandle = driver.getWindowHandle(); // WebElement zipWebElement
	 * = // headerMDA.findElement(By.xpath("//div[1]/form[1]/div[1]/input[1]"));
	 * WebElement zipWebElement =
	 * headerMDA.findElement(By.xpath("//div[1]/form[1]/div[1]/input[1]"));
	 * zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
	 * zipWebElement.sendKeys(mdaConfig.getZipCode().get(0)); WebElement
	 * getQuoteButton =
	 * headerMDA.findElement(By.xpath("//div[1]/form[1]/div[2]/input[1]"));
	 * getQuoteButton.click(); ArrayList<String> tabs = new
	 * ArrayList<String>(driver.getWindowHandles()); // switch to new tab
	 * Thread.sleep(500); driver.switchTo().window(tabs.get(0)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
	 * System.out.println("Page title of current tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of current tab: " +
	 * driver.getTitle() + " ----> Did not match"); // switch to parent window
	 * driver.switchTo().window(tabs.get(1)); if
	 * (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
	 * System.out.println("Page title of new tab: " + driver.getTitle() +
	 * " ----> Matched"); else System.out.println("Page title of new tab: " +
	 * driver.getTitle() + " ----> Did not match");
	 * 
	 * for (String handle : driver.getWindowHandles()) { if
	 * (!handle.equals(originalHandle)) { driver.switchTo().window(handle);
	 * driver.close(); } }
	 * 
	 * driver.switchTo().window(originalHandle); // driver.navigate().back(); //
	 * driver.close(); return; }
	 */
	public void processMobileHomePageTopMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {

		System.out.println(sectionElements.size() + "sections size in topMDA function");
		String originalHandle = driver.getWindowHandle();

		WebElement h1 = driver.findElement(
				By.xpath("/html[1]/body[1]/allow-navigation[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/h1[1]"));

		// "//body/allow-navigation[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/h1[1]"));

		Thread.sleep(5000);
		System.out.println(h1.getText());
		WebElement h2 = driver.findElement(
				By.xpath("/html[1]/body[1]/allow-navigation[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/h2[1]"));
		System.out.println(h2.getText());

		WebElement topMDA = sectionElements.get(1);
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		// WebElement h22 = topMDA.findElement(By.tagName("h2"));
		// System.out.println(h2.getText() + " h2 ");
		// if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
		// System.out.println("Titles matched");

		WebElement dropDownElement = driver.findElement(By.xpath("//div[@id='mdatop']//select[@name='type']"));
		Select dropdown = new Select(dropDownElement);
		System.out.println("DDOption ==> " + dropDownOption);
		dropdown.selectByValue(dropDownOption);

		WebElement zipWebElement = topMDA.findElement(By.xpath("//div[@id='mdatop']//input[@placeholder='ZIP Code']"));// (By.name("zipcode"));
		zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));

		WebElement getQuoteButton = topMDA.findElement(By.xpath("//div[@id='mdatop']//input[@class='mda-submit']"));// className("mda-submit"));
		Thread.sleep(5000);
		getQuoteButton.click();
		Thread.sleep(5000);
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		// switch to new tab

		driver.switchTo().window(tabs.get(0));
		if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
			System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
		else
			System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
		// switch to parent window
		driver.switchTo().window(tabs.get(1));
		if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
			System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
		else
			System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");

		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(originalHandle);
		// driver.close();
		System.out.println("processHomePageTopMDA Done ==>#############################");
	}

	public void processMobileTopMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		System.out.println(sectionElements.size() + "sections size in topMDA function");
		String originalHandle = driver.getWindowHandle();
		WebElement topMDA = sectionElements.get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		WebElement h2 = topMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);

			WebElement dropDownElement = driver.findElement(By.xpath("//div[@id='mdatop']//select[@name='type']"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElement = topMDA
					.findElement(By.xpath("//div[@id='mdatop']//input[@placeholder='ZIP Code']"));// (By.name("zipcode"));
			zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
			WebElement getQuoteButton = topMDA.findElement(By.xpath("//div[@id='mdatop']//input[@class='mda-submit']"));// className("mda-submit"));
			Thread.sleep(5000);
			getQuoteButton.click();
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab

			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
		} else
			System.out.println("Titles not matched.");
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
		// driver.close();

	}

	public void processMobileBlueMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		execute_partial_bottomMobileMDA = true;
		this.processMobileBottomMDA(mdaConfig, driver, dropDownOption);
		String originalHandle = driver.getWindowHandle();
		WebElement blueMDA = null;
		int i;
		for (i = sectionElements.size() - 2; i > 1; i--) {

			blueMDA = sectionElements.get(i);
			if (blueMDA.getAttribute("class").equalsIgnoreCase("responsive-mda mda")) {
				System.out.println(blueMDA.getAttribute("class") + " Class");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
				Thread.sleep(800);
				break;
			}
		}
		if (blueMDA == null) {
			System.out.println("No blue MDA found!");
			return;
		}
		/*
		 * ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
		 * Thread.sleep(800); ((JavascriptExecutor)
		 * driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
		 */
		WebElement h2 = blueMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
		Thread.sleep(5000);
		WebElement p = blueMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> ");
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");
			Thread.sleep(5000);
			i++;
			WebElement dropDownElement = blueMDA.findElement(By.xpath("(//select[@name='type'])[" + i + "]"));

			// section[@class='responsive-mda mda']//select[@name='type']"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			// Thread.sleep(3000);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = blueMDA.findElement(By.xpath("(//input[@name='zipcode'])[" + i + "]"));

			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			((HidesKeyboard) driver).hideKeyboard();

			WebElement getQuoteButton = blueMDA.findElement(By.xpath("(//input[@class='mda-submit'])[" + i + "]"));

			// xpath("//section[@class='responsive-mda mda']//input[@class='mda-submit']"));
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
			getQuoteButton.click();
			/*
			 * Actions actions = new Actions(driver);
			 * 
			 * actions.moveToElement(getQuoteButton).click().perform();
			 */

			/*
			 * JavascriptExecutor jse = (JavascriptExecutor)driver;
			 * jse.executeScript("arguments[0].click()", getQuoteButton);
			 */

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			Thread.sleep(500);
			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
		} else
			System.out.println("Titles not matched.");
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle); // driver.close();
		System.out.println("processBlueMDA Done ==>#############################");

	}

	public void processMobileGrayMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();
		WebElement grayMDA = null;
		System.out.println(sectionElements.size());
		int i;
		for (i = sectionElements.size() - 2; i > 1; i--) {

			grayMDA = sectionElements.get(i);
			if (grayMDA.getAttribute("class").equalsIgnoreCase("alt-responsive-mda mda alt-shortcode")) {
				System.out.println(grayMDA.getAttribute("class") + " Class");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
				Thread.sleep(800);
				break;
			}
		}
		if (grayMDA == null) {
			System.out.println("GrayMDA not present!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return;
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

		/*
		 * WebElement h2 = grayMDA.findElement(By.tagName("h2"));
		 * System.out.println(h2.getText() + " h2 ");
		 */

		String zipTextMdaIDString = grayMDA.getAttribute("id");
		System.out.println("zipTextMdaIDString ==== " + zipTextMdaIDString);

		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
		Thread.sleep(5000);
		WebElement p = grayMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> "); // *[@id=\"mdabottom\"]
		if (p.getText().equalsIgnoreCase(mdaConfig.getMdaText())) {
			System.out.println("<p> text matched");
			Thread.sleep(5000);

			i++;
			WebElement dropDownElement = grayMDA.findElement(
					By.xpath("//section[@class='alt-responsive-mda mda alt-shortcode']//select[@name='type']"));
			// xpath("(//select[@name='type'])["+i+"]"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = grayMDA.findElement(By
					.xpath("//section[@class='alt-responsive-mda mda alt-shortcode']//input[@placeholder='ZIP Code']"));
			// xpath("(//input[@placeholder='ZIP Code'])["+i+"]"));
			// name("zipcode"));//xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[1]/input"));//
			// xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[1]/input"));//name("zipcode"));
			Thread.sleep(5000);
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));

			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Get
			// Quotes']"))).click();
			// *[@id="mda_bc2cf8"]/div/p[1]/text()
			WebElement getQuoteButton = grayMDA.findElement(
					By.xpath("//section[@class='alt-responsive-mda mda alt-shortcode']//input[@class='mda-submit']"));
			// className("mda-submit"));
			// xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[2]/input"));
			Thread.sleep(5000);
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.click();
			Thread.sleep(5000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab

			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
		} else
			System.out.println("Titles not matched.");
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
		// driver.close();
		System.out.println("processGrayMDA Done ==>#############################");
	}

	public void processMobileGrayLikeBlueMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {

		ArrayList<WebElement> sections_res = (ArrayList<WebElement>) driver
				.findElements(By.xpath("(//section[@class='responsive-mda mda'])"));
		System.out.println(sections_res.size());
		int index = sections_res.size() - 1;
		WebElement Section_LastButOne = driver
				.findElement(By.xpath("(//section[@class='responsive-mda mda'])[" + index + "]"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);

		/*
		 * execute_partial_bottomMobileMDA = true;
		 * this.processMobileBottomMDA(mdaConfig, driver, dropDownOption);
		 */

		String originalHandle = driver.getWindowHandle();
		WebElement grayLikeBlueMDA = null;
		System.out.println(sectionElements.size());
		int i;
		for (i = sectionElements.size() - 2; i >= 2; i--) {

			grayLikeBlueMDA = sectionElements.get(i);
			if (grayLikeBlueMDA.getAttribute("class").equalsIgnoreCase("responsive-mda mda")) {
				System.out.println(grayLikeBlueMDA.getAttribute("class") + " Class");
				// ((JavascriptExecutor)
				// driver).executeScript("arguments[0].scrollIntoView(true);", grayLikeBlueMDA);
				// Thread.sleep(800);
				break;
			}
		}
		// i++;
		if (grayLikeBlueMDA == null) {
			System.out.println("grayLikeBlueMDA not present!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return;
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

		/*
		 * WebElement h2 = grayMDA.findElement(By.tagName("h2"));
		 * System.out.println(h2.getText() + " h2 ");
		 */

		String sectionMdaID = grayLikeBlueMDA.getAttribute("id");
		System.out.println("sectionMdaID ==== " + sectionMdaID);

		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", grayLikeBlueMDA);
		// Thread.sleep(5000);
		WebElement p = grayLikeBlueMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> "); // *[@id=\"mdabottom\"]
		if (p.getText().equalsIgnoreCase(mdaConfig.getMdaText())) {
			System.out.println("<p> text matched");
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);
			i++;
			String locator = "(//select[@name='type'])[" + i + "]";
			System.out.println(locator);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);

			// driver.switchTo().activeElement();
			WebElement dropDownElement = driver
					.findElement(By.xpath("(//select[@name='type'])["+i+"]"));

			// ("(//select[@name='type'])[" + i + "]"));

			// By.xpath("//section[@id='" + sectionMdaID + "']//select[@name='type']"));
			//

			// By.xpath("//div[1]/form[1]/div[1]/select[1]"));
			// section[@class='responsive-mda mda']//select[@name='type']"));
			// dropDownElement.click();
			// xpath("(//select[@name='type'])["+i+"]"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);

			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);",
			// sectionElements.get(18));
			WebElement zipWebElementB = driver.findElement(By.xpath("(//input[@name='zipcode'])[" + i + "]"));
			// By.xpath("//section[@id='" + sectionMdaID + "']//input[@name='zipcode']"));
			//

			// zipWebElementB.sendKeys("abcde");
			Thread.sleep(5000);
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			((HidesKeyboard) driver).hideKeyboard();

			// ((JavascriptExecutor)
			// driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Get
			// Quotes']"))).click();
			// *[@id="mda_bc2cf8"]/div/p[1]/text()
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);
			// driver.switchTo().activeElement();

			WebElement getQuoteButton = driver
					.findElement(By.xpath("//section[@id='" + sectionMdaID + "']//input[@value='Get Quotes →']"));
			// xpath("//div[@class='main-content']//div[@class='quote-new-360-mda']//input[@value='Get
			// Quotes →']"));

			// WebElement getQuoteButton =
			// driver.findElement(By.xpath("(//input[@class='mda-submit'])[" + i + "]"));

			// xpath("//section[@class='responsive-mda mda']//input[@class='mda-submit']"));
			// Thread.sleep(5000);

			/*
			 * String str = getQuoteButton.getText();
			 * //System.out.println(str+"    "+getQuoteButton.getLocation());
			 * System.out.println("isDisplayed "+getQuoteButton.isDisplayed());
			 * System.out.println("isEnabled "+getQuoteButton.isEnabled());
			 * System.out.println("isSelected "+getQuoteButton.isSelected());
			 */
			// this.processMobileGrayLikeBlueMDA(mdaConfig, driver, dropDownOption);
			// JavascriptExecutor executor = (JavascriptExecutor)driver;
			// ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
			// getQuoteButton);
			// executor.executeScript(“arguments[0].click();”, element);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Section_LastButOne);
			getQuoteButton.click();
			Thread.sleep(15000);
			
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab
			System.out.println("number of Tabs open = " + tabs.size());
			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
		} else
			System.out.println("Titles not matched.");
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
		// driver.close();
		System.out.println("processMobileGrayLikeBlueMDA Done ==>#############################");
	}

	public void processMobileBottomMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {

		String originalHandle = driver.getWindowHandle();

		System.out.println("=================================" + sectionElements.size());

		WebElement bottomMDA = sectionElements.get(sectionElements.size() - 1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomMDA);
		Thread.sleep(5000);
		WebElement h2 = bottomMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 and excel ==> " + mdaConfig.getMdaTitle());
		WebElement p = bottomMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> and excel ==>  " + mdaConfig.getMdaText());
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomMDA);
			WebElement dropDownElement = bottomMDA
					.findElement(By.xpath("//div[@id='mdabottom']//select[@name='type']"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			// *[@id="mdabottom"]/div/section/div/form/div[2]/input
			WebElement zipWebElementB = bottomMDA
					.findElement(By.xpath("//div[@id='mdabottom']//input[@name='zipcode']"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			WebElement getQuoteButton = bottomMDA
					.findElement(By.xpath("//div[@id='mdabottom']//input[@class='mda-submit']"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			try {
				if (execute_partial_bottomMobileMDA == true)
					return;
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomMDA);

				getQuoteButton.click();

			} catch (Exception e) {
				return;
			}
			Thread.sleep(500);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab

			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
		} else
			System.out.println("Titles not matched.");
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);
		// driver.close();
		System.out.println("processBottomMDA Done ==>#############################");
	}

	public void processMobileHeaderMDA(MDAConfig mdaConfig, AppiumDriver<AndroidElement> driver, String dropDownOption)
			throws InterruptedException {
		execute_partial_bottomMobileMDA = true;
		this.processMobileBottomMDA(mdaConfig, driver, dropDownOption);

		WebElement mobile_GetQuote = driver
				.findElement(By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div[2]/div/input"));
		mobile_GetQuote.click();
		WebElement headerMDA = sectionElements.get(1);
		WebElement label = driver
				.findElement(By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div[1]/section/div/label"));// tagName("label"));
		System.out.println(label.getText());
		String originalHandle = driver.getWindowHandle();
		if (label.getText().equalsIgnoreCase(mdaConfig.getMdaTextHeader()) || label.getText().isEmpty()) {
			// System.out.println("Header MDA text does not exist");

			// *[@id="header-mda-container"]/div[2]/div[1]/section/div/form/div[1]/select
			// driver.switchTo().activeElement();
			WebElement dropDownElement = driver
					.findElement(By.xpath("//div[@id='header-mda-container']//div//div//section//div//select[@name='type']"));
							//("//div[@class='header-mda ']//select[@name='type']"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElement = driver.findElement(
					By.xpath("//div[@class='header-mda ']//input[@placeholder='ZIP Code']"));
			// By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div/section/div/form/div[1]/input"));
			zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(3000);

			WebElement getQuoteButton = driver.findElement(
					By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div[1]/section/div/form/div[3]/input"));
			// By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div/section/div/form/div[2]/input"));

			// getQuoteButton.sendKeys(Keys.ENTER);
			getQuoteButton.click();

			Thread.sleep(8000);

			if (driver instanceof IOSDriver<?>) { // driver.context("NATIVE_APP");
				// getQuoteButton.click();

				// driver.findElement(By.name("OK")).click();
				/*
				 * WebDriverWait wait = new WebDriverWait(driver, 30);
				 * wait.until(ExpectedConditions.alertIsPresent()); Alert alert =
				 * driver.switchTo().alert(); alert.accept();
				 */
				// driver.switchTo().alert().accept();

				/*
				 * driver.context("NATIVE_APP"); driver.findElement(By.id("Allow")).click();
				 * //Change context back to the main page. driver.context("WEBVIEW");
				 */
			}

			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			System.out.println("Windows open ==>" + tabs.size());

			// switch to new tab
			Thread.sleep(5000);
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
			// driver.switchTo().
			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab()))

				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window

		} else {
			System.out.println("Header MDA text did not match");
		}
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(originalHandle)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}

		driver.switchTo().window(originalHandle);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs.size() + " #################### Window handles");
		System.out.println("processHeaderMDA Done ==>#############################");
		return;
	}
}
