package framework_core;

import utils.Screenshot;

import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import java.util.Properties;

public abstract class BaseTest {
	protected WebDriver driver;
	protected Properties env;

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() {
		// Hook for global init if needed
	}

	@Parameters({ "env" })
	@BeforeClass(alwaysRun = true)
	public void setup(@Optional("qa") String envName) {
		env = EnvLoader.load(envName);
		driver = DriverFactory.create(env.getProperty("browser"), env);
		driver.manage().deleteAllCookies();
		driver.get(env.getProperty("baseUrl"));
	}

	@AfterMethod(alwaysRun = true)
	public void afterEach(org.testng.ITestResult result) {
		if (!result.isSuccess()) {
			Screenshot.capture(driver, result.getName());
		}
	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		if (driver != null)
			driver.quit();
	}
}
