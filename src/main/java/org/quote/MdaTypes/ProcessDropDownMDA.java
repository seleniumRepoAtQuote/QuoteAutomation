package org.quote.MdaTypes;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.quote.DataTypes.MDAConfig;
import org.quote.abstractWebSection.AbstractComponents;

public class ProcessDropDownMDA extends AbstractComponents {
	public ProcessDropDownMDA(WebDriver driver, By sectionElement) {
		super(driver, sectionElement);
	}

	public ProcessDropDownMDA(WebDriver driver, By sectionElements, int i) {
		super(driver, sectionElements, 1);
	}

	public void processHeaderMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		this.processBottomMDA(mdaConfig, driver, dropDownOption);
		WebElement headerMDA = sectionElements.get(0);
		Thread.sleep(800);
		WebElement label = headerMDA.findElement(By.tagName("label"));
		// xpath("//label[contains(text(),'Free Auto Insurance Comparison')]"));
		// ("//label[contains(text(),'Free Auto Insurance Comparison')]"));
		// System.out.println(label.getText());
		String originalHandle = driver.getWindowHandle();
		if (label.getText().isEmpty()) {
			System.out.println("LABEL IS EMPTY");
		}
		System.out.println(label.getText() + " LabelText=======================================>");
		if (label.getText().equalsIgnoreCase(mdaConfig.getMdaTextHeader()) || label.getText().isEmpty()) {
			System.out.println("Header MDA text matched ");
			// WebElement zipWebElement =
			// headerMDA.findElement(By.xpath("//div[1]/form[1]/div[1]/input[1]"));
			//*[@id="header-mda-container"]/div[3]/div[1]/section/div/form/div[1]/select
			
			
			
			WebElement dropDownElement = driver.findElement(
					By.xpath("//div[@id='header-mda-container']//div//div//section//div//select[@name='type']"));
					//("/html/body/allow-navigation/div[1]/header/div[1]/div/div[3]/div[3]/div[1]/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			System.out.println("DDOption ==> " + dropDownOption);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElement = headerMDA.findElement(By.name("zipcode"));
			zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			// zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));
			WebElement getQuoteButton = headerMDA.findElement(By.xpath("//div/form/div[2]/input"));
			// "/html[1]/body[1]/allow-navigation[1]/div[1]/header[1]/div[1]/div[1]/div[3]/div[2]/div[1]/section[1]/div[1]/form[1]/div[2]/input[1]"));
			// By.xpath("//div/form/div[2]/input"));

			// "//*[@id=\"header-mda-container\"]/div[2]/div/section/div/form/div[2]/input"));
			getQuoteButton.submit();
			Thread.sleep(1000);
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab
			Thread.sleep(500);
			driver.switchTo().window(tabs.get(0));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitleCurrentTab())) {

				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Matched");
			} else
				System.out.println("Page title of current tab: " + driver.getTitle() + " ----> Did not match");
			// switch to parent window
			driver.switchTo().window(tabs.get(1));
			if (driver.getTitle().equalsIgnoreCase(mdaConfig.getPageTitlenewTab()))
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Matched");
			else
				System.out.println("Page title of new tab: " + driver.getTitle() + " ----> Did not match");
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
		// driver.navigate().back();
		// driver.close();
		System.out.println("processHeaderMDA Done ==>#############################");
		return;
	}

	public void processBottomMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();

		System.out.println("==================================" + sectionElements.size());

		WebElement bottomMDA = sectionElements.get(sectionElements.size() - 1);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", bottomMDA);
		Thread.sleep(800);
		WebElement h2 = bottomMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");
		WebElement p = bottomMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> ");
		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");

			WebElement dropDownElement = driver
					.findElement(By.xpath("//*[@id=\"mdabottom\"]/div/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = bottomMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			if (ProcessMda.execute_partial_BottomMDA == true) {
				zipWebElementB.clear();
				zipWebElementB.sendKeys("abcde");
				Thread.sleep(800);
				return;
			}
			Thread.sleep(5000);
			WebElement getQuoteButton = bottomMDA.findElement(By.className("mda-submit"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.submit();// click();
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

	public void processTopMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();

		System.out.println("==================================" + sectionElements.size());

		WebElement topMDA = sectionElements.get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		WebElement h2 = topMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");

		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");

			WebElement dropDownElement = driver
					.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = topMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			
			Thread.sleep(5000);
			WebElement getQuoteButton = topMDA.findElement(By.className("mda-submit"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.submit();// click();
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
		System.out.println("processTopMDA Done ==>#############################");
	}public void processTopMDAEnterButton(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();

		System.out.println("==================================" + sectionElements.size());

		WebElement topMDA = sectionElements.get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		WebElement h2 = topMDA.findElement(By.tagName("h2"));
		System.out.println(h2.getText() + " h2 ");

		if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
			System.out.println("Titles matched");

			WebElement dropDownElement = driver
					.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = topMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			
			Thread.sleep(5000);
			WebElement getQuoteButton = topMDA.findElement(By.className("mda-submit"));
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.sendKeys(Keys.ENTER);
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
		System.out.println("processTopMDAEnterButton Done ==>#############################");
	}

	public void processHomePageTopMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
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

		WebElement topMDA = sectionElements.get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		// WebElement h22 = topMDA.findElement(By.tagName("h2"));
		// System.out.println(h2.getText() + " h2 ");
		// if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
		// System.out.println("Titles matched");

		WebElement dropDownElement = driver
				.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
		Select dropdown = new Select(dropDownElement);
		dropdown.selectByValue(dropDownOption);

		WebElement zipWebElement = topMDA.findElement(By.name("zipcode"));
		zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));

		WebElement getQuoteButton = topMDA.findElement(By.className("mda-submit"));
		Thread.sleep(5000);
		getQuoteButton.submit();// click();
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

	public void processHomePageTopMDAEnterButton(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		System.out.println(sectionElements.size() + "sections size in topMDA function");
		String originalHandle = driver.getWindowHandle();

		WebElement h1 = driver
				.findElement(By.xpath("//body/allow-navigation[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/h1[1]"));

		Thread.sleep(5000);
		System.out.println(h1.getText());
		WebElement h2 = driver.findElement(
				By.xpath("/html[1]/body[1]/allow-navigation[1]/div[1]/main[1]/div[1]/div[1]/div[1]/div[1]/h2[1]"));
		System.out.println(h2.getText());

		WebElement topMDA = sectionElements.get(2);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		Thread.sleep(800);
		// WebElement h22 = topMDA.findElement(By.tagName("h2"));
		// System.out.println(h2.getText() + " h2 ");
		// if (h2.getText().equalsIgnoreCase(mdaConfig.getMdaTitle())) {
		// System.out.println("Titles matched");

		WebElement dropDownElement = driver
				.findElement(By.xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
		Select dropdown = new Select(dropDownElement);
		dropdown.selectByValue(dropDownOption);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", topMDA);
		WebElement zipWebElement = topMDA.findElement(By.name("zipcode"));
		zipWebElement.sendKeys(mdaConfig.getZipCode().get(0));

		WebElement getQuoteButton = topMDA.findElement(By.className("mda-submit"));
		Thread.sleep(5000);
		getQuoteButton.sendKeys(Keys.ENTER);
		;
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
		System.out.println("processHomePageEnterButton TopMDA Done ==>#############################");
	}

	public void processBlueMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();
		WebElement blueMDA = null;
		System.out.println(sectionElements.size());
		for (int i = sectionElements.size() - 2; i > 1; i--) {

			blueMDA = sectionElements.get(i);
			if (blueMDA.getAttribute("class").equalsIgnoreCase("alt-responsive-mda mda alt-shortcode")) {
				System.out.println(blueMDA.getAttribute("class") + " Class");
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
				Thread.sleep(800);
				break;
			}
		}
		if (blueMDA == null) {
			System.out.println("GrayMDA not present!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			return;
		}
		// ((JavascriptExecutor)
		// driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

		/*
		 * WebElement h2 = grayMDA.findElement(By.tagName("h2"));
		 * System.out.println(h2.getText() + " h2 ");
		 */
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);
		WebElement p = blueMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> ");
		if (p.getText().equalsIgnoreCase(mdaConfig.getMdaText())) {
			System.out.println("<p> text matched");

			// *[@id="mda_a14d55"]/div/form/div[1]/select
			WebElement dropDownElement = blueMDA.findElement(By.name("type"));// xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = blueMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", blueMDA);

			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Get
			// Quotes']"))).click();

			WebElement getQuoteButton = blueMDA.findElement(By.className("mda-submit"));
			Thread.sleep(5000);
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.submit();// click();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab
			Thread.sleep(500);
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
		System.out.println("processBlueMDA Done ==>#############################");
	}

	public void processGrayMDA(MDAConfig mdaConfig, WebDriver driver, String dropDownOption)
			throws InterruptedException {
		String originalHandle = driver.getWindowHandle();
		WebElement grayMDA = null;
		System.out.println(sectionElements.size());
		for (int i = sectionElements.size() - 2; i > 1; i--) {

			grayMDA = sectionElements.get(i);
			if (grayMDA.getAttribute("class").equalsIgnoreCase("responsive-mda mda")) {
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
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);
		WebElement p = grayMDA.findElement(By.tagName("p"));
		System.out.println(p.getText() + " <p> ");
		if (p.getText().equalsIgnoreCase(mdaConfig.getMdaText())) {
			System.out.println("<p> text matched");

			// *[@id="mda_a14d55"]/div/form/div[1]/select
			WebElement dropDownElement = grayMDA.findElement(By.name("type"));// xpath("//*[@id=\"mdatop\"]/div/section/div/form/div[1]/select"));
			Select dropdown = new Select(dropDownElement);
			dropdown.selectByValue(dropDownOption);

			WebElement zipWebElementB = grayMDA.findElement(By.name("zipcode"));
			// ("//div[1]/form[1]/div[1]/input[1]"));
			// WebElement zipWebElementB = bottomMDA.findElement(By.tagName("input"));
			zipWebElementB.sendKeys(mdaConfig.getZipCode().get(0));
			Thread.sleep(5000);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", grayMDA);

			// new WebDriverWait(driver,
			// 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Get
			// Quotes']"))).click();

			WebElement getQuoteButton = grayMDA.findElement(By.className("mda-submit"));
			Thread.sleep(5000);
			// ("//div[1]/form[1]/div[2]/input[1]"));
			getQuoteButton.submit();// click();
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			// switch to new tab
			Thread.sleep(500);
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

}
