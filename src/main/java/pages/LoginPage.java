package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import framework_core.BasePage;

public class LoginPage extends BasePage {

	@FindBy(id = "login1")
	private WebElement username;
	@FindBy(id = "password")
	private WebElement password;
	@FindBy(css = "input.signin")
	private WebElement signInButton;
	@FindBy(css = ".error")
	private WebElement errorBanner;

	public LoginPage(WebDriver driver) {
		super(driver);

	}

	public InboxPage login(String user, String pass) {

		type(username, user);
		type(password, pass);
		click(signInButton);

		return new InboxPage(driver);
	}

	public boolean isErrorVisible() {

		return isDisplayed(errorBanner);
	}

}
