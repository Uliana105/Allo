package Mobile.Objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class GoogleSearchPage {
    private AndroidDriver driver;

    public GoogleSearchPage(AndroidDriver driver){
        this.driver = driver;
    }

    public By searchField = AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/search_box_text\"]");
    public By searchUrlBar = AppiumBy.xpath("//android.widget.EditText[@resource-id=\"com.android.chrome:id/url_bar\"]");
    public By firstSearchResult = AppiumBy.xpath("//android.widget.TextView[@resource-id=\"com.android.chrome:id/line_2\"]");
    public By homePage = AppiumBy.id("com.android.chrome:id/home_button");

    public void openUrl(String url) {
        driver.findElement(searchField).click();
        driver.findElement(searchUrlBar).sendKeys(url);
        driver.findElement(firstSearchResult).click();
    }

    public void goToHomePage() {
        if (driver.findElements(searchField).size() == 0)
            driver.findElement(homePage).click();
    }
}
