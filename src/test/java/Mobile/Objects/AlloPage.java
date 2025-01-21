package Mobile.Objects;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private By searchResultsPrices = AppiumBy.xpath("//android.widget.Button[@text=\"Купити\"]/../android.view.View[4]/android.widget.TextView[2]");
    private By filtersButton = AppiumBy.androidUIAutomator("new UiSelector().text(\" Filters\")");
    private By pageNumber = AppiumBy.androidUIAutomator("new UiSelector().text(\"1\").instance(0)");
    private By menuButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Відкрити меню\")");
    private By productCatalogButton = AppiumBy.androidUIAutomator("new UiSelector().text(\"Каталог товарів\")");
    private By pageTitle = AppiumBy.xpath("//android.webkit.WebView/android.view.View/android.view.View/android.widget.TextView[@text]");
    private By locationButton = AppiumBy.xpath("//android.widget.Button[@text=\"Каталог товарів\"]/../android.widget.Button[4]");
    private By locationTitle = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]");
    private By availableLocations = AppiumBy.xpath("//android.view.View[contains(@text,\"Ваше місто:\")]/..//android.view.View[@content-desc]/android.widget.TextView");
    private By specialOffers = AppiumBy.xpath("//android.widget.Button[@text=\"Купити\"]/../android.widget.ListView//android.widget.Image");
    private By sideMenutitle = AppiumBy.androidUIAutomator("new UiSelector().text(\"Интернет-Магазин Allo\")");

    private String priceSeekBarMin = "new UiSelector().className(\"android.widget.SeekBar\").instance(0)";
    private String priceSeekBarMax = "new UiSelector().className(\"android.widget.SeekBar\").instance(1)";
    private By priceSeekBar = AppiumBy.xpath("//android.widget.SeekBar[1]/../..//android.widget.TextView");
    private By minPriceValue = AppiumBy.xpath("//android.widget.TextView[@text=\"Ціна, ₴\"]/../../android.view.View[2]/android.view.View/android.widget.TextView[1]");
    private By maxPriceValue = AppiumBy.xpath("//android.widget.TextView[@text=\"Ціна, ₴\"]/../../android.view.View[2]/android.view.View/android.widget.TextView[2]");
    private By showResultsBtn = AppiumBy.xpath("//android.widget.Button[contains(@text,\"ПОКАЗАТИ\")]");

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

    // Scroll and get unique search results prices
    public Set<Integer> getResultsPrices() {
        pageTools.waitForElementVisibility(specialOffers);

        List<WebElement> results = driver.findElements(searchResultsPrices);
        List<Integer> prices = new ArrayList<>(
                driver.findElements(searchResultsPrices).stream()
                        .map(n -> Integer.parseInt(n.getText().replaceAll("[\\s₴]", "")))
                        .collect(Collectors.toList()));

        while (results.size() >= 2) {
            pageTools.scrollDownBetweenPoints(
                    results.get(results.size() - 1),
                    results.get(0)
            );

            results = driver.findElements(searchResultsPrices);
            prices.addAll(results.stream()
                    .map(n -> Integer.parseInt(n.getText().replaceAll("[\\s₴]", "")))
                    .collect(Collectors.toList()));
        }

        return new HashSet<>(prices);
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

    public void clickFilterBtn() {
        pageTools.waitForElementVisibility(specialOffers);
        driver.findElement(filtersButton).click();
    }

    public void setMinPriceToPercent(double percent) {
        // scroll to Price filter
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.view.View\").instance(19)).scrollIntoView("
                + priceSeekBarMin + ");"));

        WebElement seekBar = driver.findElement(priceSeekBar);
        int offset = 10; // to include min toggle to the swipe area

        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", seekBar.getLocation().getX() - offset,
                "top", seekBar.getLocation().getY(),
                "width", seekBar.getSize().getWidth(),
                "height", seekBar.getSize().getHeight(),
                "direction", "right",
                "percent", percent
        ));
    }

    public void setMaxPriceToPercent(double percent) {
        // scroll to Price filter
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().className(\"android.view.View\").instance(19)).scrollIntoView("
                + priceSeekBarMax + ");"));

        WebElement seekBar = driver.findElement(priceSeekBar);
        int offset = 10; // to include max toggle to the swipe area

        driver.executeScript("mobile: swipeGesture", ImmutableMap.of(
                "left", seekBar.getLocation().getX() + offset,
                "top", seekBar.getLocation().getY(),
                "width", seekBar.getSize().getWidth(),
                "height", seekBar.getSize().getHeight(),
                "direction", "left",
                "percent", 1 - percent
        ));
    }

    public int getMinPrice() {
        return Integer.parseInt(driver.findElement(minPriceValue).getText().replace(" ₴", ""));
    }

    public int getMaxPrice() {
        return Integer.parseInt(driver.findElement(maxPriceValue).getText().replace(" ₴", ""));
    }

    public void clickShowResultsBtn() {
        driver.findElement(showResultsBtn).click();
        pageTools.waitForElementInvisibility(showResultsBtn);
    }
}