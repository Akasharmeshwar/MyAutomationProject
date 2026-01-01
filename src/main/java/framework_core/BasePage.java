package framework_core;

import utils.Waits;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
	protected WebDriver driver;
	protected Waits waits;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.waits = new Waits(driver);
		PageFactory.initElements(driver, this);
	}

	protected void click(WebElement el) {
		waits.untilVisible(el);
		try {
			el.click();
		} catch (ElementClickInterceptedException ex) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
		}
	}

	protected void type(WebElement el, String text) {
		waits.untilVisible(el);
		el.clear();
		el.sendKeys(text);
	}

	protected void waitForUrlContains(String part) {
		waits.untilUrlContains(part);
	}

	protected void waitForText(WebElement el, String text) {
		waits.untilText(el, text);
	}

	protected boolean isDisplayed(WebElement el) {
		try {
			return el.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
}
