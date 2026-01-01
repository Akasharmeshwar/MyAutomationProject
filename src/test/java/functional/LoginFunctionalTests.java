package functional;

import framework_core.BaseTest;
import dataProviders.LoginDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InboxPage;
import pages.LoginPage;

public class LoginFunctionalTests extends BaseTest {

    @Test(description="Functional: Invalid login shows error", dataProvider="invalidLogins", dataProviderClass=LoginDataProvider.class)
    public void T09_invalidLogin(String user, String pass) {
        LoginPage login = new LoginPage(driver);
        login.login(user, pass);
        Assert.assertTrue(login.isErrorVisible(), "Error banner should be visible for invalid login");
    }

    @Test(description="Functional: Password field masked")
    public void T10_passwordMasked() {
        LoginPage login = new LoginPage(driver);
        org.openqa.selenium.WebElement pwd = driver.findElement(org.openqa.selenium.By.id("password"));
        Assert.assertEquals(pwd.getAttribute("type"), "password", "Password field must be masked");
    }

    @Test(description="Functional: Successful login", groups="functional")
    public void T11_successLogin() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        Assert.assertTrue(inbox.isLoaded(), "Inbox must load");
    }
}
