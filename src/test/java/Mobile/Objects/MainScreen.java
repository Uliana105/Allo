package Mobile.Objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class MainScreen {
    private AndroidDriver driver;

    public MainScreen(AndroidDriver driver){
        this.driver = driver;
    }

    public By chromeIcon = AppiumBy.accessibilityId("Chrome");

    public void clickOnChromeIcon() {
        driver.findElement(chromeIcon).click();
    }
}
