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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.quote.DataTypes.MDAConfig;
import org.quote.abstractWebSection.AbstractComponents;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class ProcessCBTMDA extends AbstractComponents {
	AppiumDriver<AndroidElement> driver;
	By title = By.xpath("//section[1]/div[1]/h2[1]");
	By mdaSectionToCheckId = By.tagName("section");

	String title_gray = "Enter your ZIP code below to view companies that have cheap auto insurance rates.";
	String title_blues = "Free Auto Insurance Comparison";
	String text_blue = "Enter your ZIP code below to view companies that have cheap auto insurance rates.";
	public static boolean execute_partial_TopMDA = false;
	public static boolean execute_partial_bottomMobileMDA = false;

	public ProcessCBTMDA(AppiumDriver<AndroidElement> driver, By sectionElement) {
		super(driver, sectionElement);
	}

	public ProcessCBTMDA(AndroidDriver<AndroidElement> driver2, By sectionElements, int i) {
		super(driver2, sectionElements, 1);
	}

	public ProcessCBTMDA(AppiumDriver<AndroidElement> driver2, By sectionElements, int i) {
		super(driver2, sectionElements, 1);
	}

	public ProcessCBTMDA(IOSDriver<AndroidElement> driver2, By sectionElements, int i) {
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
	public void processMobileHomePageTopMDA(MDAConfig mdaConfig, WebDriver driver) throws InterruptedException {

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
		WebElement zipWebElement = topMDA
				.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/input"));// (By.name("zipcode"));
		zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));

		WebElement getQuoteButton = topMDA
				.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[2]/input"));// className("mda-submit"));
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

	public void processMobileTopMDA(MDAConfig mdaConfig, WebDriver driver) throws InterruptedException {
		System.out.println(sectionElements.size() + "sections size in topMDA function");
		String originalHandle = driver.getWindowHandle();
		WebElement topMDA = sectionElements.get(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		WebElement h2 = topMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
			WebElement zipWebElement = topMDA
					.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/input"));// (By.name("zipcode"));
			zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
			WebElement getQuoteButton = topMDA
					.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[2]/input"));// className("mda-submit"));
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

	public void processMobileBlueMDA(MDAConfig mdaConfig, WebDriver driver) throws InterruptedException {

		String originalHandle = driver.getWindowHandle();
		WebElement blueMDA = null;
		for (int i = sectionElements.size() - 2; i > 2; i--) {

			blueMDA = sectionElements.get(i);
			if (blueMDA.getAttribute("class").equalsIgnoreCase("responsive-mda mda")) {
				System.out.println(blueMDA.getAttribute("class") + " Class");
				break;
			}
		}
		if (blueMDA == null) {
			System.out.println("No blue MDA found!");
			return;
		}
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
		Thread.sleep(800);

		WebElement h2 = blueMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");
		WebElement p = blueMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> ");
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");
			WebElement zipWebElementB = blueMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]")); // WebElement zipWebElementB
			// =bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			WebElement getQuoteButton = blueMDA.findElement(By.className("mda-submit"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.click();
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

	public void processMobileGrayMDA(MDAConfig mdaConfig, WebDriver driver) throws InterruptedException {
		//ProcessMda.execute_partial_BottomMDA = true;
		execute_partial_bottomMobileMDA = true;
		this.processMobileBottomMDA(mdaConfig, driver);
		String originalHandle = driver.getWindowHandle();
		WebElement grayMDA = null;
		System.out.println(sectionElements.size());
		for (int i = sectionElements.size() - 2; i > 1; i--) {

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
			System.out.println("\"//*[@id=\"" + zipTextMdaIDString + "\"]/div/section/div/form/div[1]/input\"");
			WebElement zipWebElementB = grayMDA.findElement(By
					.xpath("//section[@class='alt-responsive-mda mda alt-shortcode']//input[@placeholder='ZIP Code']"));
			// By.name("zipcode"));//xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[1]/input"));//
			// xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[1]/input"));//name("zipcode"));
			Thread.sleep(5000);
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Get
			// Quotes']"))).click();
			// *[@id="mda_bc2cf8"]/div/p[1]/text()
			WebElement getQuoteButton = grayMDA.findElement(
					By.xpath("//section[@class='alt-responsive-mda mda alt-shortcode']//input[@class='mda-submit']"));
			// className("mda-submit"));
			// xpath("//*[@id=\""+zipTextMdaIDString+"\"]/div/section/div/form/div[2]/input"));

			// ("//div[1]/form[1]/div[2]/input[1]"));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
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

	public void processMobileBottomMDA(MDAConfig mdaConfig, WebDriver driver) throws InterruptedException {

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
			WebElement zipWebElementB = bottomMDA
					.findElement(By.xpath("//*[@id=\"mdabottom\"]/div/section/div/form/div[1]/input"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			WebElement getQuoteButton = bottomMDA
					.findElement(By.xpath("//*[@id=\"mdabottom\"]/div/section/div/form/div[2]/input"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			try {
				if (execute_partial_bottomMobileMDA == true)
					return;
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

	public void processMobileHeaderMDA(MDAConfig mdaConfig, AppiumDriver<AndroidElement> driver)
			throws InterruptedException {
		execute_partial_bottomMobileMDA = true;
		this.processMobileBottomMDA(mdaConfig, driver);
		WebElement headerMDA = sectionElements.get(0);
		WebElement label = headerMDA.findElement(By.tagName("label"));

		String originalHandle = driver.getWindowHandle();
		if (label.getText().equalsIgnoreCase(mdaConfig.getMdaTextHeader()) || label.getText().isEmpty()) {
			System.out.println("Header MDA text does not exist");

			WebElement zipWebElement = headerMDA.findElement(By.xpath("//div[1]/form[1]/div[1]/input[1]"));
			// By.xpath("//*[@id=\"header-mda-container\"]/div[2]/div/section/div/form/div[1]/input"));
			zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(3000);

			WebElement getQuoteButton = headerMDA.findElement(By.xpath("//div[1]/form[1]/div[2]/input[1]"));
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
