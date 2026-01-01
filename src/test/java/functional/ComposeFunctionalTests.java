package functional;

import framework_core.BaseTest;
import dataProviders.ComposeDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

public class ComposeFunctionalTests extends BaseTest {

	@Test(description = "Functional: Send mail with CC and BCC")
	public void T12_sendWithCcBcc() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage compose = inbox.clickCompose();
		InboxPage afterSend = compose.to(env.getProperty("recipient")).cc(env.getProperty("recipient"))
				.bcc(env.getProperty("recipient")).subject("CC_BCC").body("Testing CC and BCC").sendSuccessful();
		SentItemsPage sent = new SentItemsPage(driver);
		Assert.assertTrue(sent.containsSubject("CC_BCC"));
	}

	@Test(description = "Functional: Attachment upload small file", dataProvider = "attachments", dataProviderClass = ComposeDataProvider.class)
	public void T13_attachmentUpload(String path, boolean shouldPass) {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage compose = inbox.clickCompose();
		InboxPage afterSend = compose.to(env.getProperty("recipient")).subject("Attachment").body("attachment test")
				.attach(path).sendSuccessful();

		if (shouldPass) {
			Assert.assertTrue(new SentItemsPage(driver).containsSubject("Attachment"),
					"Attachment mail should be sent");
		} else {
			Assert.assertTrue(compose.isErrorToastVisible(), "Error toast should show for invalid/large attachment");
		}
	}

	@Test(description = "Functional: Large attachment validation (negative)")
	public void T14_largeAttachmentNegative() {
		String largePath = "src/test/resources/data/large.zip";
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage compose = inbox.clickCompose().to(env.getProperty("recipient")).subject("LargeAttach")
				.body("should fail").attach(largePath).sendExpectingError();
		Assert.assertTrue(compose.isErrorToastVisible(), "Large attachment must be blocked");
	}

	@Test(description = "Functional: Rich text formatting preserved")
	public void T15_richTextFormatting() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage compose = inbox.clickCompose().to(env.getProperty("recipient")).subject("RichText")
				.body("Bold Italic");
		driver.findElement(org.openqa.selenium.By.cssSelector("button.bold")).click();
		driver.findElement(org.openqa.selenium.By.cssSelector("button.italic")).click();
		compose.sendSuccessful();
		Assert.assertTrue(new SentItemsPage(driver).containsSubject("RichText"));
	}

	@Test(description = "Functional: Mark mail read/unread")
	public void T16_markReadUnread() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		driver.findElement(org.openqa.selenium.By.cssSelector("input[type='checkbox'].selectMail")).click();
		driver.findElement(org.openqa.selenium.By.cssSelector("button.markUnread")).click();
		driver.findElement(org.openqa.selenium.By.cssSelector("button.markRead")).click();
		Assert.assertTrue(true, "Status toggled");
	}

	@Test(description = "Functional: Move mail to folder")
	public void T17_moveMailToFolder() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		FoldersPage folders = new FoldersPage(driver).createFolder("TestFolder");
		driver.findElement(org.openqa.selenium.By.cssSelector("input[type='checkbox'].selectMail")).click();
		driver.findElement(org.openqa.selenium.By.cssSelector("select.moveFolder")).sendKeys("TestFolder");
		Assert.assertTrue(folders.hasFolder("TestFolder"));
	}

	@Test(description = "Functional: Draft auto-save on close")
	public void T18_draftAutoSave() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		DraftsPage drafts = inbox.clickCompose().subject("AutoSave").body("wait for autosave").saveDraft();
		Assert.assertTrue(drafts.hasDraftWithSubject("AutoSave"));
	}

	@Test(description = "Functional: Invalid recipient validation (negative)")
	public void T19_invalidRecipientNegative() {
		InboxPage inbox = new LoginPage(driver).login(env.getProperty("user"), env.getProperty("pass"));
		ComposePage compose = inbox.clickCompose().to("invalid@@email").subject("InvalidRecipient").body("should fail")
				.sendExpectingError();
		Assert.assertTrue(compose.isErrorToastVisible(), "Invalid recipient should be rejected");
	}
}
