package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pages.MarketWatchPage;

public class MarketWatchTests extends BaseTest {

    private MarketWatchPage mw;

    @BeforeMethod
    public void setUpPage() {
        mw = new MarketWatchPage(getDriver());
        mw.closeInitialPopupIfPresent();
    }

    @Test(groups = { "UI" })
    public void verifyMarketSummaryLoads() {
        test.set(extent.createTest("Verify Market Summary Loads"));
        mw.clickMarketWatchTab();
        Assert.assertTrue(mw.isMarketSummaryVisible(), "Market Summary table is not visible");
    }

    @Test(groups = { "UI" })
    public void verifyTopGainersTabLoads() {
        test.set(extent.createTest("Verify Top Gainers Tab Loads"));
        mw.clickTopGainers();
        Assert.assertTrue(mw.isGainersTableVisible(), "Gainers table is not visible");
    }

    @Test(groups = { "UI" })
    public void verifyTopLosersTabLoads() {
        test.set(extent.createTest("Verify Top Losers Tab Loads"));
        mw.clickTopLosers();
        Assert.assertTrue(mw.isLosersTableVisible(), "Losers table is not visible");
    }

    @Test(groups = { "Functional" })
    public void verifyIndexValueIsDisplayed() {
        test.set(extent.createTest("Verify Index Value Display"));
        String value = mw.getIndexValue();
        Assert.assertNotNull(value, "Index value is not displayed");
    }

    @Test(groups = { "UI" })
    public void verifyResponsiveLayoutChrome() {
        test.set(extent.createTest("Verify Responsive Layout on Chrome"));
        int width = getDriver().manage().window().getSize().getWidth();
        Assert.assertTrue(width >= 1024, "Layout not responsive for desktop view");
    }
}
