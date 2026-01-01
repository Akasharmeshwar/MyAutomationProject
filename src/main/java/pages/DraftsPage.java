package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class DraftsPage extends BasePage {
	@FindBy(css = "#div_list")
	private WebElement draftsList;

	public DraftsPage(WebDriver driver) {
		super(driver);
	}

	public boolean hasDraftWithSubject(String subject) {
		return draftsList.findElements(By.cssSelector("a.mail_subject")).stream()
				.anyMatch(el -> el.getText().contains(subject));
	}
}
