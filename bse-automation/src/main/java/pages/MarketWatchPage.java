package pages;

import constants.Locators;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelper;

public class MarketWatchPage {
    private WebDriver driver;

    @FindBy(xpath = Locators.MARKET_WATCH_TAB)
    private WebElement marketWatchTab;

    @FindBy(xpath = Locators.TOP_GAINERS_TAB)
    private WebElement topGainersTab;

    @FindBy(xpath = Locators.TOP_LOSERS_TAB)
    private WebElement topLosersTab;

    public MarketWatchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void closeInitialPopupIfPresent() {
        try {
            WebElement popupClose = driver.findElement(By.xpath(Locators.POPUP_CLOSE_BUTTON));
            popupClose.click();
        } catch (NoSuchElementException ignored) {}
    }

    public void clickMarketWatchTab() {
        WaitHelper.waitForElement(marketWatchTab, 10);
        marketWatchTab.click();
    }

    public void clickTopGainers() {
        WaitHelper.waitForElement(topGainersTab, 10);
        topGainersTab.click();
    }

    public void clickTopLosers() {
        WaitHelper.waitForElement(topLosersTab, 10);
        topLosersTab.click();
    }

    public boolean isMarketSummaryVisible() {
        WebElement summaryTable = driver.findElement(By.xpath(Locators.MARKET_SUMMARY_TABLE));
        WaitHelper.waitForElement(summaryTable, 10);
        return summaryTable.isDisplayed();
    }

    public boolean isGainersTableVisible() {
        WebElement gainersTable = driver.findElement(By.xpath(Locators.GAINERS_TABLE));
        WaitHelper.waitForElement(gainersTable, 10);
        return gainersTable.isDisplayed();
    }

    public boolean isLosersTableVisible() {
        WebElement losersTable = driver.findElement(By.xpath(Locators.LOSERS_TABLE));
        WaitHelper.waitForElement(losersTable, 10);
        return losersTable.isDisplayed();
    }

    public String getIndexValue() {
        WebElement indexCell = driver.findElement(By.xpath(Locators.INDEX_VALUE));
        WaitHelper.waitForElement(indexCell, 10);
        return indexCell.getText().trim();
    }
}
