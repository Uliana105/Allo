package Mobile.Objects;

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
    private By searchButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Надіслати\")");
    private By searchResults = AppiumBy.xpath("//android.widget.Button[@text=\"Купити\"]/../android.view.View[@content-desc]/android.widget.TextView[@text]");
    private By filtersButton = AppiumBy.androidUIAutomator("new UiSelector().text(\" Filters\")");
    private By pageNumber = AppiumBy.androidUIAutomator("new UiSelector().text(\"1\").instance(0)");
    private By menuButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Відкрити меню\")");
    private By productCatalogButton = AppiumBy.xpath("//android.widget.Button[@text=\"Каталог товарів\"]");
    private By pageTitle = AppiumBy.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.widget.TextView[@text]");
    private By locationButton = AppiumBy.xpath("//android.widget.Button[@text=\"Каталог товарів\"]/../android.widget.Button[4]");
    private By locationTitle = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]");
    private By availableLocations = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]/..//android.view.View[@content-desc]/android.widget.TextView");
    private By specialOffers = AppiumBy.xpath("//android.widget.Button[@text=\"Купити\"]/../android.widget.ListView//android.widget.Image");
    private By sideMenutitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Интернет-Магазин Allo\")");

    public void searchByText(String text) {
        driver.findElement(searchField).click();
        driver.findElement(searchField).sendKeys(text);
        driver.findElement(searchButton).click();
    }

    public int getNumberOfResultsOLD() {
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

    // Scroll and count unique search results
    public int getNumberOfResults() {
        List<WebElement> results = driver.findElements(searchResults);
        List<String> productCards = new ArrayList<>(
                results.stream()
                        .map(WebElement::getText)
                        .collect(Collectors.toList()));

        while (results.size() >= 2) {
            pageTools.scrollDownBetweenPoints(
                    results.get(results.size() - 1),
                    results.get(0)
            );

            results = driver.findElements(searchResults);
            productCards.addAll(results.stream()
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
        pageTools.waitForElementInvisibility(sideMenutitle);
        pageTools.waitForElementVisibility(specialOffers);
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