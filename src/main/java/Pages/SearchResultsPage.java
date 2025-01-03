package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage {
    protected WebDriver driver;
    private PageTools pageTools;

    public SearchResultsPage(WebDriver driver){
        this.driver = driver;
        pageTools = new PageTools(driver);
    }
    private final By productItems = By.cssSelector("div.product-card");
    private final By specialOffersPic = By.cssSelector("div.rectangle-label__pic");
    private final By priceFilterTitle = By.cssSelector("h3[data-id='price']");
    private final By minPriceInput = By.cssSelector("form[data-range-filter='price'] input:nth-child(1)");
    private final By maxPriceInput = By.cssSelector("form[data-range-filter='price'] input:nth-child(2)");
    private final By showProducts = By.cssSelector("span.f-popup__message");
    private final By productPrice = By.cssSelector("div:is(.v-pb__cur, .discount) span.sum");
    private final By activeFilters = By.cssSelector(".toolbar-block div.f-active");
    private final By priceActiveFilter = By.cssSelector(".toolbar-block div.f-active a[data-id='price']");
    private final By pagesLinks = By.cssSelector("a[class='pagination__links']");
    private final By exactPageLinks = By.cssSelector("a.pagination__next__link");
    private final By footer = By.cssSelector("div.footer__main ");

    public void waitForResultsLoaded() {
        pageTools.waitForElementVisibility(specialOffersPic);
    }

    public int getNumberOfResults() {
        waitForResultsLoaded();
        return driver.findElements(productItems).size();
    }

    public String getPriceFilterTitleText() {
        return driver.findElement(priceFilterTitle).getText();
    }

    public void typeMinPrice(String price) {
        pageTools.typeWithWipe(price, minPriceInput);
        pageTools.waitForElementVisibility(showProducts);
    }

    public void waitForShowProductsBtn() {
        pageTools.waitForElementVisibility(showProducts);
    }

    public String getMinPriceInputText() {
        return driver.findElement(minPriceInput).getDomProperty("value");
    }

    public boolean isMinPriceInputVisible() {
        return pageTools.isElementPresent(minPriceInput);
    }

    public void typeMaxPrice(String price) {
        pageTools.typeWithWipe(price, maxPriceInput);
        pageTools.waitForElementVisibility(showProducts);
    }

    public boolean isMaxPriceInputVisible() {
        return pageTools.isElementPresent(maxPriceInput);
    }

    public String getMaxPriceInputText() {
        return driver.findElement(maxPriceInput).getDomProperty("value");
    }

    public void clickShowProductsButton() {
        driver.findElement(showProducts).click();
    }

    public ArrayList<Integer> getProductsPrices() {
        List<WebElement> webElements = driver.findElements(productPrice);
        ArrayList<Integer> prices = new ArrayList<>();
        webElements.forEach((n) -> { prices.add(Integer.parseInt(n.getText().replace(" ", ""))); });
        return prices;
    }

    public void waitForFiltersToBeApplied() {
        pageTools.waitForElementVisibility(activeFilters);
    }

    public String getPriceActiveFilterText() {
        return driver.findElement(priceActiveFilter).getText();
    }

    public int getNumOfPages() {
        if(pageTools.isElementPresent(pagesLinks)) {
            List<WebElement> webElements = driver.findElements(pagesLinks);
            return Integer.parseInt(webElements.get(webElements.size() - 1).getText());
        } else return 1;
    }

    public void clickNextPage() {
        Actions action = new Actions(driver);

        pageTools.scrollToTheBottom();
        action.moveToElement(driver.findElement(footer)).perform();
        driver.findElement(exactPageLinks).click();
    }
}
