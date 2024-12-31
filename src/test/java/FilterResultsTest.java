import Common.CommonActions;
import Common.Constants;
import Pages.MainPage;
import Pages.SearchResultsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;

@Listeners({AllureTestNg.class})
public class FilterResultsTest {
    String textToSearch = "IPhone";
    String minPrice = "10000";
    String maxPrice = "20000";
    String expectedPriceActiveFilterText = "10 000 ₴ - 20 000 ₴";
    public WebDriver driver;

    SearchResultsPage searchResultsPage;

    @BeforeMethod
    public void setUp() {
        driver = CommonActions.createDriver();

        driver.get(Constants.BASE_URL);
        MainPage mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);

        mainPage.searchProductByText(textToSearch);
        searchResultsPage.waitForResultsLoaded();
    }

    @Test
    @Feature("Filter results by price")
    public void filterResultsByPrice() {
        //Step 1: Verify Price section visibility
        String actualPriceFilterTitle = searchResultsPage.getPriceFilterTitleText();
        Assert.assertEquals(actualPriceFilterTitle, "Ціна, ₴", "Price section title isn't valid");
        Assert.assertTrue(searchResultsPage.isMinPriceInputVisible(), "Min price input isn't visible");
        Assert.assertTrue(searchResultsPage.isMaxPriceInputVisible(), "Max price input isn't visible");

        //Step 2: type value into min price field
        searchResultsPage.typeMinPrice(minPrice);
        searchResultsPage.waitForShowProductsBtn();

        //Step 3: type value into max price field
        searchResultsPage.typeMaxPrice(maxPrice);
        searchResultsPage.waitForShowProductsBtn();

        String actualMinValue = searchResultsPage.getMinPriceInputText();
        String actualMaxValue = searchResultsPage.getMaxPriceInputText();
        Assert.assertEquals(actualMinValue, minPrice, "The min value isn't correct");
        Assert.assertEquals(actualMaxValue, maxPrice, "The max value isn't correct");

        //Step 4: Wait for filter to be applied
        searchResultsPage.waitForFiltersToBeApplied();
        String actualPriceActiveFilterText = searchResultsPage.getPriceActiveFilterText();
        Assert.assertEquals(actualPriceActiveFilterText, expectedPriceActiveFilterText, "Price active filter text isn't correct");

        //Verify the prices are in range from 10000 to 20000
        ArrayList<Integer> prices = searchResultsPage.getProductsPrices();
        int numOfPages = searchResultsPage.getNumOfPages();

        Assert.assertTrue(prices.stream().allMatch(num -> num >= Integer.parseInt(minPrice) && num <= Integer.parseInt(maxPrice)),
                "Prices aren't in range: " + prices);

        if (numOfPages > 1) {
            for (int i = 2; i < numOfPages; i++) {
                searchResultsPage.clickNextPage();

                prices = searchResultsPage.getProductsPrices();
                Assert.assertTrue(prices.stream().allMatch(num -> num >= Integer.parseInt(minPrice) && num <= Integer.parseInt(maxPrice)),
                        "Prices aren't in range: " + prices);
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
