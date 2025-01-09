package Mobile.Objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

public class Actions {

    private AndroidDriver driver;

    public Actions(AndroidDriver driver){
        this.driver = driver;
    }

    By alertBlockButton = AppiumBy.xpath("//android.widget.Button[@resource-id=\"com.android.chrome:id/negative_button\"]");
    public void clickHomeButton() {
        driver.pressKey(new KeyEvent(AndroidKey.HOME));
    }

    public void clickBlockButtonOnAlert() {
        if (driver.findElements(alertBlockButton).size() > 0)
            driver.findElement(alertBlockButton).click();
    }
}
