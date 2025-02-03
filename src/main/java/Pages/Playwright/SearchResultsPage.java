package Pages.Playwright;

import Pages.PageTools;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage {
    private Page searchResultPage;

    private final Locator productItems;
    private final Locator specialOffersPic;
    private final Locator priceFilterTitle;
    private final Locator minPriceInput;
    private final Locator maxPriceInput;
    private final Locator showProducts;
    private final Locator productPrice;
    private final Locator activeFilters;
    private final Locator priceActiveFilter;
    private final Locator pagesLinks;
    private final Locator exactPageLinks;
    private final Locator footer;

    public SearchResultsPage(Page page){
        searchResultPage = page;
        productItems = page.locator("div.product-card");
        specialOffersPic = page.locator("div.rectangle-label__pic");
        priceFilterTitle = page.locator("h3[data-id='price']");
        minPriceInput = page.locator("form[data-range-filter='price'] input:nth-child(1)");
        maxPriceInput = page.locator("form[data-range-filter='price'] input:nth-child(2)");
        showProducts = page.locator("span.f-popup__message");
        productPrice = page.locator("div:is(.v-pb__cur, .discount) span.sum");
        activeFilters = page.locator(".toolbar-block div.f-active");
        priceActiveFilter = page.locator(".toolbar-block div.f-active a[data-id='price']");
        pagesLinks = page.locator("a[class='pagination__links']");
        exactPageLinks = page.locator("a.pagination__next__link");
        footer = page.locator("div.footer__main");
    }

    public void waitForResultsLoaded() {
        searchResultPage.waitForSelector(specialOffersPic.toString().split("@")[1]);
    }

    public int getNumberOfResults() {
        waitForResultsLoaded();
        return productItems.count();
    }

    public String getPriceFilterTitleText() {
        return priceFilterTitle.textContent();
    }

    public void typeMinPrice(String price) {
        minPriceInput.fill(price);
        searchResultPage.waitForSelector(showProducts.toString().split("@")[1]);
    }

    public void waitForShowProductsBtn() {
        searchResultPage.waitForSelector(showProducts.toString().split("@")[1]);
    }

    public String getMinPriceInputText() {
        return minPriceInput.inputValue();
    }

    public boolean isMinPriceInputVisible() {
        return minPriceInput.isVisible();
    }

    public void typeMaxPrice(String price) {
        maxPriceInput.fill(price);
        searchResultPage.waitForSelector(showProducts.toString().split("@")[1]);
    }

    public boolean isMaxPriceInputVisible() {
        return maxPriceInput.isVisible();
    }

    public String getMaxPriceInputText() {
        return maxPriceInput.inputValue();
    }

    public void clickShowProductsButton() {
        showProducts.click();
    }

    public ArrayList<Integer> getProductsPrices() {
        List<String> prices = productPrice.allInnerTexts();
        return (ArrayList<Integer>) prices.stream().map(n -> Integer.parseInt(n.replace(" ", ""))).collect(Collectors.toList());
    }

    public void waitForFiltersToBeApplied() {
        searchResultPage.waitForSelector(activeFilters.toString().split("@")[1]);
    }

    public String getPriceActiveFilterText() {
        return priceActiveFilter.textContent().trim();
    }

    public int getNumOfPages() {
        if(pagesLinks.allInnerTexts().size() > 0) {
            List<String> pages = pagesLinks.allInnerTexts();
            return Integer.parseInt(pages.get(pages.size() - 1));
        } else return 1;
    }

    public void clickNextPage() {
        exactPageLinks.click();
    }
}
