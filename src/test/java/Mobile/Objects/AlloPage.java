package Mobile.Objects;

import Pages.PageTools;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AlloPage {
    private AndroidDriver driver;
    private PageTools pageTools;

    public AlloPage(AndroidDriver driver){
        this.driver = driver;
        pageTools = new PageTools(driver);
    }

    By searchField = AppiumBy.xpath("//android.webkit.WebView[@text=\"АЛЛО - національний маркетплейс із найширшим асортиментом\"]/android.view.View/android.view.View/android.view.View[2]/android.view.View[1]/android.view.View[2]/android.view.View/android.view.View/android.widget.EditText");
    By searchButton = AppiumBy.xpath("//android.widget.Button[@text=\"Надіслати\"]");
    By searchResults = AppiumBy.xpath("//android.view.View/android.view.View/android.view.View[@content-desc]");
    By filtersButton = AppiumBy.xpath("//android.widget.Button[@text=\" Filters\"]");
    By footerPlayMarketButton = AppiumBy.xpath("//android.view.View[@content-desc=\"playmarket\"]");

    public void searchByText(String text) {
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public int getNumberOfResults() {
//        driver.findElement(AppiumBy.androidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+visibleText+"\").instance(0))")).click();
        pageTools.waitForElementVisibility(filtersButton);
        return driver.findElements(searchResults).size();
    }
}
