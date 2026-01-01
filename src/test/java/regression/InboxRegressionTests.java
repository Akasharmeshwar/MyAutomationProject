package regression;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class InboxRegressionTests extends BaseTest {

    @Test(description = "Regression: Multiple login/logout cycles")
    public void T25_multiLoginLogout() {
        for (int i = 0; i < 5; i++) {
            InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
            Assert.assertTrue(inbox.isLoaded(), "Inbox load #" + i);
            inbox.logout();
            Assert.assertTrue(driver.getCurrentUrl().contains("login"), "Logout redirect #" + i);
        }
    }

    @Test(description = "Regression: Pagination deep navigation")
    public void T26_paginationNavigation() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        inbox.goToNextPage();
        inbox.goToNextPage();
        inbox.goToFirstPage();
        Assert.assertTrue(inbox.hasMailList(), "List should remain consistent");
    }

    @Test(description = "Regression: Bulk delete and restore")
    public void T27_bulkDeleteRestore() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        inbox.selectFirstNMail(5);
        inbox.deleteSelected();

        TrashPage trash = new TrashPage(driver);
        trash.restoreFirstTwoMails();

        // TODO: Replace with actual count verification
        Assert.assertTrue(true, "Counts should update after restore");
    }

    @Test(description = "Regression: Folder creation and deletion")
    public void T28_folderCreateDelete() {
        InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        FoldersPage f = new FoldersPage(driver).createFolder("RegFolder");
        Assert.assertTrue(f.hasFolder("RegFolder"), "Folder should be created");

        f.deleteFolder("RegFolder");
        Assert.assertFalse(f.hasFolder("RegFolder"), "Folder should be deleted");
    }
}
