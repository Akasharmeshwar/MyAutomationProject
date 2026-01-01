package regression;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SystemRegressionTests extends BaseTest {

	@Test(description = "Regression: Session timeout and re-auth")
	public void T32_sessionTimeout() throws InterruptedException {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		Thread.sleep(600000); // 10 min; replace with configurable timeout and explicit detection
		driver.navigate().refresh();
		Assert.assertTrue(driver.getCurrentUrl().contains("login") || driver.getPageSource().contains("session"),
				"Should require re-login");
	}

	@Test(description = "Regression: Attachment virus/malware block (negative)")
	public void T33_malwareBlock() {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage c = new InboxPage(driver).clickCompose().to(env.getProperty("recipient")).subject("EICAR Test")
				.body("Malware check").attach("src/test/resources/data/eicar.txt");
		Assert.assertTrue(c.isErrorToastVisible(), "Malware-like content should be blocked");
	}

	@Test(description = "Regression: Cross-browser smoke (Chrome only by default)")
	public void T34_crossBrowserSmoke() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		Assert.assertTrue(inbox.isLoaded(), "Baseline smoke on default browser");
		// For full cross-browser, run suite with -Denv=qa and env.browser override or
		// matrix in CI
	}
}
