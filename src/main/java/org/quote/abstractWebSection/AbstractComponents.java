package org.quote.abstractWebSection;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractComponents {

	protected WebElement sectionElement;
	protected ArrayList<WebElement> sectionElements;
	protected WebDriver driver;

	public AbstractComponents(WebDriver driver, By sectionElement) {
		this.driver = driver;
		this.sectionElement = driver.findElement(sectionElement);
	}

	public AbstractComponents(WebDriver driver, By sectionElements, int i) {
		this.driver = driver;
		this.sectionElements = (ArrayList<WebElement>) driver.findElements(sectionElements);
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
