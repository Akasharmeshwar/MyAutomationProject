package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class ComposePage extends BasePage {

	@FindBy(css = "input#TO_ID")
	private WebElement toField;

	@FindBy(css = "input#CC_ID")
	private WebElement ccField;

	@FindBy(css = "input#BCC_ID")
	private WebElement bccField;

	@FindBy(css = "input.subject")
	private WebElement subjectField;

	@FindBy(css = "iframe.mailcontent")
	private WebElement bodyIframe;

	@FindBy(css = "input.attach")
	private WebElement attachInput;

	@FindBy(css = "button.send")
	private WebElement sendBtn;

	@FindBy(css = "button.save")
	private WebElement saveDraftBtn;

	@FindBy(css = ".toast.success")
	private WebElement successToast;

	@FindBy(css = ".toast.error")
	private WebElement errorToast;

	public ComposePage(WebDriver driver) {
		super(driver);
	}

	public ComposePage to(String email) {
		type(toField, email);
		return this;
	}

	public ComposePage cc(String email) {
		type(ccField, email);
		return this;
	}

	public ComposePage bcc(String email) {
		type(bccField, email);
		return this;
	}

	public ComposePage subject(String subject) {
		type(subjectField, subject);
		return this;
	}

	public ComposePage body(String text) {
		driver.switchTo().frame(bodyIframe);
		WebElement editable = driver.findElement(By.cssSelector("body"));
		editable.clear();
		editable.sendKeys(text);
		driver.switchTo().defaultContent();
		return this;
	}

	public ComposePage attach(String path) {
		attachInput.sendKeys(path);
		return this;
	}

	// ✅ Fix: add send() for regression tests
	public InboxPage send() {
		click(sendBtn);
		return new InboxPage(driver);
	}

	public boolean isSuccessToastVisible() {
		return isDisplayed(successToast);
	}

	public boolean isErrorToastVisible() {
		return isDisplayed(errorToast);
	}

	public DraftsPage saveDraft() {
		click(saveDraftBtn);
		return new DraftsPage(driver);
	}

	// In ComposePage.java
	public InboxPage sendSuccessful() {
		click(sendBtn);
		return new InboxPage(driver);
	}

	// Negative flow: stays on ComposePage so you can check error toast
	public ComposePage sendExpectingError() {
		click(sendBtn);
		return this;
	}

}
