package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends BasePage {
	@FindBy(id = "signature")
	private WebElement signatureTextArea; // adjust
	@FindBy(id = "saveSignature")
	private WebElement saveSignatureBtn; // adjust
	@FindBy(id = "vacationToggle")
	private WebElement vacationToggle; // adjust
	@FindBy(id = "vacationMessage")
	private WebElement vacationMessage; // adjust
	@FindBy(css = ".save.success")
	private WebElement saveSuccessToast; // adjust

	public SettingsPage(WebDriver driver) {
		super(driver);
	}

	public SettingsPage updateSignature(String text) {
		type(signatureTextArea, text);
		click(saveSignatureBtn);
		return this;
	}

	public SettingsPage enableVacation(String msg) {
		if (!vacationToggle.isSelected())
			click(vacationToggle);
		type(vacationMessage, msg);
		click(saveSignatureBtn);
		return this;
	}

	public boolean isSaveSuccess() {
		return isDisplayed(saveSuccessToast);
	}
}
