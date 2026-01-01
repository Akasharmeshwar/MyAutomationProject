package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InboxPage extends BasePage {

    @FindBy(css = "a.rd_compose")
    private WebElement composeBtn;

    @FindBy(css = "#div_list")
    private WebElement mailList;

    @FindBy(css = "a.logout")
    private WebElement logoutLink;

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return isDisplayed(mailList) || isDisplayed(composeBtn);
    }

    public ComposePage clickCompose() {
        click(composeBtn);
        return new ComposePage(driver);
    }

    public void openFirstMail() {
        WebElement first = mailList.findElement(By.cssSelector("a.mail_subject"));
        click(first);
    }

    public boolean hasMailList() {
        return isDisplayed(mailList);
    }

    public void goToNextPage() {
        WebElement nextPage = driver.findElement(By.cssSelector("a.nextPage"));
        click(nextPage);
    }

    public void goToFirstPage() {
        WebElement firstPage = driver.findElement(By.cssSelector("a.firstPage"));
        click(firstPage);
    }

    public void selectFirstNMail(int n) {
        List<WebElement> checks = driver.findElements(By.cssSelector("input[type='checkbox'].selectMail"));
        for (int i = 0; i < n && i < checks.size(); i++) {
            click(checks.get(i));
        }
    }

    public void deleteSelected() {
        WebElement deleteBtn = driver.findElement(By.cssSelector("button.delete"));
        click(deleteBtn);
    }

    public LoginPage logout() {
        click(logoutLink);
        return new LoginPage(driver);
    }
}
