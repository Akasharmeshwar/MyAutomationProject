package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage {
	@FindBy(css = "input#search")
	private WebElement searchInput; // adjust
	@FindBy(css = "button#searchBtn")
	private WebElement searchBtn; // adjust
	@FindBy(css = "#searchResults")
	private WebElement results; // adjust

	public SearchPage(WebDriver driver) {
		super(driver);
	}

	public SearchPage query(String text) {
		type(searchInput, text);
		click(searchBtn);
		return this;
	}

	public int resultCount() {
		return results.findElements(By.cssSelector("a.mail_subject")).size();
	}
}
