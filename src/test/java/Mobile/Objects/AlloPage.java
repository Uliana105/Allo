package Mobile.Objects;

import Mobile.Objects.PageTools;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;


public class AlloPage {
    private AndroidDriver driver;
    private PageTools pageTools;

    public AlloPage(AndroidDriver driver){
        this.driver = driver;
        pageTools = new PageTools(driver);
    }

    private By searchField = AppiumBy.xpath("//android.widget.Button[@text=\"Надіслати\"]/../android.view.View/android.widget.EditText");
    private By searchButton = AppiumBy.xpath("//android.widget.Button[@text=\"Надіслати\"]");
    private By searchResults = AppiumBy.xpath("//android.widget.Button[@text=\"Купити\"]/../android.view.View[@content-desc]/android.widget.TextView[@text]");
    private By filtersButton = AppiumBy.xpath("//android.widget.Button[@text=\" Filters\"]");
    private By pageNumber = AppiumBy.xpath("//android.view.View[@content-desc=\"1\"]");
    private By menuButton = AppiumBy.xpath("//android.widget.Button[@text=\"Відкрити меню\"]");
    private By productCatalogButton = AppiumBy.xpath("//android.widget.Button[@text=\"Каталог товарів\"]");
    private By pageTitle = AppiumBy.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.widget.TextView[@text]");
    private By locationButton = AppiumBy.xpath("//android.widget.Button[@text=\"Каталог товарів\"]/../android.widget.Button[4]");
    private By locationTitle = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]");
    private By availableLocations = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]/..//android.view.View[@content-desc]/android.widget.TextView");

    public void searchByText(String text) {
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public int getNumberOfResults() {
        pageTools.waitForElementVisibility(filtersButton);

        List<String> productCards = new ArrayList<>(
                driver.findElements(searchResults).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList()));
        while (!pageTools.isElementPresent(pageNumber)) {
            pageTools.performScrollDown();

            productCards.addAll(driver.findElements(searchResults).stream()
                    .map(WebElement::getText)
                    .collect(Collectors.toList()));
        }

        return new HashSet<>(productCards).size();
    }

    public void clickCatalogItem(String item) {
        driver.findElement(AppiumBy.accessibilityId(item)).click();
    }

    public void clickMenuButton() {
        driver.findElement(menuButton).click();
    }

    public void clickProductCatalogButton() {
        driver.findElement(productCatalogButton).click();
    }

    public void clickLocationButton() {
        driver.findElement(locationButton).click();
    }

    public String getPageTitleText() {
        pageTools.waitForElementVisibility(filtersButton);
        return driver.findElement(pageTitle).getText();
    }

    public String getCurrentCity() {
        return driver.findElement(locationTitle).getText().replace("Ваше місто: ", "");
    }

    public List<String> getAvailableLocations() {
        List<String> cities = driver.findElements(availableLocations).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        cities.remove(getCurrentCity());
        return cities;
    }

    public void selectCity(String city) {
        driver.findElement(AppiumBy.accessibilityId(city)).click();
    }
}