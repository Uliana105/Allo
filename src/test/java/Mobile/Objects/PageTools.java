package Mobile.Objects;

import Common.Constants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageTools {
    protected AndroidDriver driver;

    public PageTools(AndroidDriver driver){
        this.driver = driver;
    }
    public boolean isElementPresent(By by) {
        return driver.findElements(by).size() != 0;
    }

    public void typeWithWipe(String text, By by) {
        WebElement webElement = driver.findElement(by);
        webElement.sendKeys(Keys.CONTROL + "a");
        webElement.sendKeys(Keys.DELETE);
        webElement.sendKeys(text);
    }

    public void waitForElementVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
    }

    public void scrollToTheBottom() {
        // Cast WebDriver to JavascriptExecutor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Scroll to the bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        try {
            Thread.sleep(Constants.MICRO_TIMEOUT * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
