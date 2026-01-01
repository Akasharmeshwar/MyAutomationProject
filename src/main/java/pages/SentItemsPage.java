package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SentItemsPage extends BasePage {
	@FindBy(css = "#div_list")
	private WebElement sentList;

	public SentItemsPage(WebDriver driver) {
		super(driver);
	}

	public boolean containsSubject(String subject) {
		return sentList.findElements(By.cssSelector("a.mail_subject")).stream()
				.anyMatch(el -> el.getText().contains(subject));
	}
}
