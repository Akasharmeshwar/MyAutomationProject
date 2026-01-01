package base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import utils.ChartGenerator;
import utils.DriverFactory;
import utils.ScreenshotHelper;

public class BaseTest {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected ExtentReports extent;
    protected static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public ExtentTest getTest() {
        return test.get();
    }

    @BeforeSuite
    public void setupSuite() {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Test-output/ExtentReports/index.html");
        extent.attachReporter(spark);
    }

    @Parameters({"browser", "baseUrl"})
    @BeforeMethod
    public void setup(@Optional("chrome") String browser,
                      @Optional("https://www.bseindia.com/index.html") String baseUrl) {
        driver.set(DriverFactory.initDriver(browser));
        getDriver().get(baseUrl);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        ExtentTest currentTest = getTest();

        if (currentTest != null) {
            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    String screenshotPath = ScreenshotHelper.captureScreenshot(result.getName());
                    currentTest.fail(result.getThrowable())
                               .addScreenCaptureFromPath(screenshotPath);
                    break;
                case ITestResult.SUCCESS:
                    currentTest.pass("Test Passed");
                    break;
                case ITestResult.SKIP:
                    currentTest.skip("Test Skipped");
                    break;
            }
        }

        getDriver().quit();
        driver.remove();
        test.remove();
    }

    @AfterSuite
    public void flushReport() {
        extent.flush();
        ChartGenerator.generatePieChart(16, 3, 1); // Update counts if needed
    }
}
