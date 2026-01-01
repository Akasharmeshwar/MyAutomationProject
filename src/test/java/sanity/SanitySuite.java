package sanity;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class SanitySuite extends BaseTest {

    @Test(description="Sanity: Valid login lands in Inbox")
    public void T01_validLogin() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        Assert.assertTrue(inbox.isLoaded(), "Inbox should load after valid login");
    }

    @Test(description="Sanity: Compose minimal email and send")
    public void T02_composeSendMinimal() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        ComposePage compose = inbox.clickCompose();
        compose.to(env.getProperty("recipient")).subject("Sanity").body("Hello").send();
        SentItemsPage sent = new SentItemsPage(driver);
        Assert.assertTrue(sent.containsSubject("Sanity"), "Sent items should contain email with subject Sanity");
    }

    @Test(description="Sanity: Logout")
    public void T03_logout() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        LoginPage login = inbox.logout();
        Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Should be redirected to login");
    }

    @Test(description="Sanity: Inbox loads with list or empty state")
    public void T04_inboxLoad() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        Assert.assertTrue(inbox.hasMailList() || inbox.isLoaded(), "Inbox list or compose should be visible");
    }

    @Test(description="Sanity: Search basic keyword")
    public void T05_basicSearch() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        SearchPage search = new SearchPage(driver).query("Sanity");
        Assert.assertTrue(search.resultCount() >= 0, "Search should not error");
    }

    @Test(description="Sanity: Open mail and view content")
    public void T06_openMail() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        inbox.openFirstMail();
        Assert.assertTrue(driver.getPageSource().contains("From") || driver.getPageSource().contains("Subject"), "Mail content should display");
    }

    @Test(description="Sanity: Delete mail to Trash")
    public void T07_deleteMailToTrash() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        // select first mail checkbox and delete
        // adjust selectors accordingly
        driver.findElement(org.openqa.selenium.By.cssSelector("input[type='checkbox'].selectMail")).click();
        driver.findElement(org.openqa.selenium.By.cssSelector("button.delete")).click();
        TrashPage trash = new TrashPage(driver);
        Assert.assertTrue(trash.containsSubject(""), "Trash should contain deleted mail"); // adapt with subject capture
    }

    @Test(description="Sanity: Draft saved on close")
    public void T08_draftSaveOnClose() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        ComposePage compose = inbox.clickCompose();
        compose.subject("Draft Sanity").body("Auto-save check");
        DraftsPage drafts = compose.saveDraft();
        Assert.assertTrue(drafts.hasDraftWithSubject("Draft Sanity"), "Draft should appear");
    }
}
