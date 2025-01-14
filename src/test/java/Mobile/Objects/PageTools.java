package Mobile.Objects;

import Common.Constants;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

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

    public void performScrollDown() {
        int windowHeight = driver.manage().window().getSize().getHeight();
        int windowWidth = driver.manage().window().getSize().getWidth();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = new Point((int) (windowWidth * 0.6), (int) (windowHeight * 0.9));
        Point end = new Point ((int) (windowWidth * 0.6), (int) (windowHeight * 0.25));
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void swipeElementToTheLeft(By by) {
        int windowWidth = driver.manage().window().getSize().getWidth();

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = driver.findElement(by).getLocation();
        Point end = new Point ((int) (windowWidth * 0.1), start.getY());
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    public void moveElement(By pointA, By pointB) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = driver.findElement(pointA).getLocation();
        WebElement pointBElement = driver.findElement(pointB);
        Point destination = new Point(
                pointBElement.getLocation().getX() + pointBElement.getSize().getWidth()/2,
                pointBElement.getLocation().getY() + pointBElement.getSize().getHeight()/2);
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                PointerInput.Origin.viewport(), destination.getX(), destination.getY()));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }
}
