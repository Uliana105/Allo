import Common.CommonActions;
import Common.Constants;
import Pages.MainPage;
import Pages.SearchResultsPage;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({AllureTestNg.class})
public class SearchProductTest {
    String textToSearch = "IPhone";
    int expectedMinNumOfResults = 2;
    public WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = CommonActions.createDriver();
    }

    @Test
    @Feature("Search product by text")
    public void searchProductByText() {
        //Step 1: Open Allo Main page
        driver.get(Constants.BASE_URL);

        MainPage mainPage = new MainPage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

        //Step 2: Type "IPhone" into the search field
        mainPage.typeTextIntoSearchField(textToSearch);

        //Verify typed text is visible in the search field
        String actualTextInSearchField = mainPage.getTextFromSearchField();
        Assert.assertEquals(actualTextInSearchField, textToSearch, "The text isn't correct");

        //Step 3: Click Enter button on the keyboard
        mainPage.clickEnterOnSearchField();

        //Verify at least 2 items are present on the Search Results page
        int actualNumOfResults = searchResultsPage.getNumberOfResults();
        Assert.assertTrue(actualNumOfResults >= expectedMinNumOfResults,
                "Fewer results than expected. \nExpected minimum: " + expectedMinNumOfResults + "\nActual: " + actualNumOfResults);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
