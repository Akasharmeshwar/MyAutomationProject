package pages;

import framework_core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FoldersPage extends BasePage {

    @FindBy(css = "input#newFolderName")
    private WebElement newFolderName;

    @FindBy(css = "button#createFolder")
    private WebElement createFolderBtn;

    @FindBy(css = "ul.folderList")
    private WebElement folderList;

    public FoldersPage(WebDriver driver) {
        super(driver);
    }

    public FoldersPage createFolder(String name) {
        type(newFolderName, name);
        click(createFolderBtn);
        return this;
    }

    public boolean hasFolder(String name) {
        List<WebElement> folders = folderList.findElements(By.cssSelector("li"));
        return folders.stream().anyMatch(li -> li.getText().trim().equalsIgnoreCase(name));
    }

    public void deleteFolder(String name) {
        WebElement deleteBtn = driver.findElement(By.cssSelector("button.deleteFolder[data-name='" + name + "']"));
        click(deleteBtn);
    }
}
