package keywords;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import framework_core.BasePage;

public class Keywords {

	public static void click(BasePage p, WebElement el) {

		el.click();
	}

	public static void type(BasePage p, WebElement el, String val) {

		el.clear();
		el.sendKeys(val);
	}

	public static void verifyContains(String actual, String expected) {

		Assert.assertTrue(actual.contains(expected));
	}
}
