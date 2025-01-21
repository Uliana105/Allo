package Mobile.Objects;

import Common.Constants;
import io.appium.java_client.AppiumBy;
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
        return !driver.findElements(by).isEmpty();
    }

    public void waitForElementVisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitForElementInvisibility(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForElementClickable(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(Constants.IMPLICIT_WAIT));
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void performScrollDown() {
        int windowHeight = driver.manage().window().getSize().getHeight();
        int windowWidth = driver.manage().window().getSize().getWidth();

        Point start = new Point((int) (windowWidth * 0.6), (int) (windowHeight * 0.9));
        Point end = new Point ((int) (windowWidth * 0.6), (int) (windowHeight * 0.25));

        performMoveAction(start, end, "scroll");
    }

    public void swipeElementToTheLeft(By by) {
        int windowWidth = driver.manage().window().getSize().getWidth();

        Point start = driver.findElement(by).getLocation();
        Point end = new Point ((int) (windowWidth * 0.1), start.getY());

        performMoveAction(start, end, "swipe");
    }

    public void moveElement(By pointA, By pointB) {
        Point start = driver.findElement(pointA).getLocation();
        WebElement pointBElement = driver.findElement(pointB);
        Point destination = new Point(
                pointBElement.getLocation().getX() + pointBElement.getSize().getWidth()/2,
                pointBElement.getLocation().getY() + pointBElement.getSize().getHeight()/2);

        performMoveAction(start, destination, "move");
    }

    public void moveElement(Point pointA, Point pointB) {
        performMoveAction(pointA, pointB, "move");
    }

    public void scrollDownBetweenPoints(WebElement pointA, WebElement pointB) {
        int windowWidth = driver.manage().window().getSize().getWidth();

        Point start = new Point((int) (windowWidth * 0.6), pointA.getLocation().getY());
        Point end = new Point((int) (windowWidth * 0.6), pointB.getLocation().getY());

        performMoveAction(start, end, "scroll");
    }

    private void performMoveAction(Point start, Point end, String type) {
        int duration = type.equals("scroll") ? 1500 : 3000;
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence move = new Sequence(finger, 1);

        move.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), start.getX(), start.getY()));
        move.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        move.addAction(finger.createPointerMove(Duration.ofMillis(duration),
                PointerInput.Origin.viewport(), end.getX(), end.getY()));
        move.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(move));
    }
}
