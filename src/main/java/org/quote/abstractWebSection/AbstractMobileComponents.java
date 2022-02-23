package org.quote.abstractWebSection;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public abstract class AbstractMobileComponents {

	protected WebElement sectionElement;
	protected ArrayList<AndroidElement> sectionElements;
	protected AndroidDriver<AndroidElement> driver;

	public AbstractMobileComponents(AndroidDriver<AndroidElement> driver, By sectionElement) {
		this.driver = driver;
		this.sectionElement = driver.findElement(sectionElement);
	}

	public AbstractMobileComponents(AndroidDriver<AndroidElement> driver, By sectionElements, int i) {
		this.driver = driver;
		this.sectionElements = (ArrayList<AndroidElement>) driver.findElements(sectionElements);
		System.out.println("abstractComponents: size of sections for mdas found:" + this.sectionElements.size());
	}

	public WebElement findElement(By findElementBy) {
		return sectionElement.findElement(findElementBy);
	}

	public void waitForElementToDisappear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
	}

	public List<WebElement> findElements(By findElementBy) {
		return sectionElement.findElements(findElementBy);
	}

}
