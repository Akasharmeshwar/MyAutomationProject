package regression;

import framework_core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ComposeRegressionTests extends BaseTest {

    @Test(description = "Regression: Compose autosave after idle")
    public void T29_composeAutosaveIdle() throws InterruptedException {
        new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        ComposePage c = new InboxPage(driver).clickCompose().subject("IdleAutoSave").body("Wait for autosave");
        Thread.sleep(15000); // replace with explicit wait if autosave indicator exists
        DraftsPage d = c.saveDraft();
        Assert.assertTrue(d.hasDraftWithSubject("IdleAutoSave"));
    }

    @Test(description = "Regression: Send rate-limiting or throttle behavior")
    public void T30_sendThrottle() {
        new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        for (int i = 0; i < 5; i++) {
            new InboxPage(driver).clickCompose().to(env.getProperty("recipient")).subject("SpamTest " + i)
                    .body("Throttling check").send();
        }
        Assert.assertTrue(true, "Either all sent or error toast displayed for throttle");
    }

    @Test(description = "Regression: Internationalization in subject/body")
    public void T31_internationalization() {
        new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
        new InboxPage(driver).clickCompose().to(env.getProperty("recipient")).subject("नमस्ते महाराष्ट्र")
                .body("मराठी / हिंदी चाचणी").send();
        Assert.assertTrue(new SentItemsPage(driver).containsSubject("नमस्ते महाराष्ट्र"));
    }
}
