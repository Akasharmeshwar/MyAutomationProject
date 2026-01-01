package functional;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SettingsFunctionalTests extends BaseTest {

	@Test(description = "Functional: Update signature")
	public void T23_updateSignature() {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		SettingsPage s = new SettingsPage(driver).updateSignature("Best regards,\nAkash");
		Assert.assertTrue(s.isSaveSuccess(), "Signature save should succeed");
	}

	@Test(description = "Functional: Enable vacation auto-reply")
	public void T24_enableVacationAutoReply() {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		SettingsPage s = new SettingsPage(driver).enableVacation("I'm away, will reply soon.");
		Assert.assertTrue(s.isSaveSuccess(), "Vacation toggle save should succeed");
	}
}
