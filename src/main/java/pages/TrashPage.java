package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TrashPage extends BasePage {

    @FindBy(css = "#div_list")
    private WebElement trashList;

    @FindBy(css = "button.restore")
    private WebElement restoreButton;

    public TrashPage(WebDriver driver) {
        super(driver);
    }

    public boolean containsSubject(String subject) {
        return trashList.findElements(By.cssSelector("a.mail_subject")).stream()
                .anyMatch(el -> el.getText().contains(subject));
    }

    public void selectFirstNTrash(int n) {
        List<WebElement> checks = driver.findElements(By.cssSelector("input.selectTrash"));
        for (int i = 0; i < n && i < checks.size(); i++) {
            click(checks.get(i));
        }
    }

    public void restoreSelected() {
        click(restoreButton);
    }

    public void restoreFirstTwoMails() {
        selectFirstNTrash(2);
        restoreSelected();
    }
}
