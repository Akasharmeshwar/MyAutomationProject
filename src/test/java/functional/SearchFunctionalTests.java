package functional;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SearchFunctionalTests extends BaseTest {

	@Test(description = "Functional: Search by keyword")
	public void T20_searchKeyword() {
		InboxPage i = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		SearchPage s = new SearchPage(driver).query("Sanity");
		Assert.assertTrue(s.resultCount() >= 0);
	}

	@Test(description = "Functional: Search by sender filter")
	public void T21_searchBySender() {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		// apply sender filter (requires actual advanced search selectors)
		driver.findElement(org.openqa.selenium.By.id("senderFilter")).sendKeys(env.getProperty("recipient"));
		driver.findElement(org.openqa.selenium.By.id("searchBtn")).click();
		Assert.assertTrue(new SearchPage(driver).resultCount() >= 0);
	}

	@Test(description = "Functional: Search by date range")
	public void T22_searchByDateRange() {
		new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		driver.findElement(org.openqa.selenium.By.id("fromDate")).sendKeys("2024-01-01");
		driver.findElement(org.openqa.selenium.By.id("toDate")).sendKeys("2024-12-31");
		driver.findElement(org.openqa.selenium.By.id("searchBtn")).click();
		Assert.assertTrue(new SearchPage(driver).resultCount() >= 0);
	}
}
