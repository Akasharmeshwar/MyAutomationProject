package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class Waits {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Waits(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void untilVisible(WebElement el) { wait.until(ExpectedConditions.visibilityOf(el)); }
    public void untilClickable(WebElement el) { wait.until(ExpectedConditions.elementToBeClickable(el)); }
    public void untilUrlContains(String part) { wait.until(ExpectedConditions.urlContains(part)); }
    public void untilText(WebElement el, String text) { wait.until(ExpectedConditions.textToBePresentInElement(el, text)); }
}
