package Web.Playwright;

import Common.Constants;
import Pages.Playwright.MainPage;
import Pages.Playwright.SearchResultsPage;
import com.microsoft.playwright.options.LoadState;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.*;

import static org.junit.Assert.*;

@Listeners({AllureTestNg.class})
public class SearchProductPlaywrightTest extends BaseTest {
    String textToSearch = "IPhone";
    int expectedMinNumOfResults = 2;

    @Test
    public void searchProductByText() {
        page = context.newPage();

        //Step 1: Open Allo Main page
        page.navigate(Constants.BASE_URL);
        page.waitForLoadState(LoadState.NETWORKIDLE);

        MainPage mainPage = new MainPage(page);

        //Step 2: Type "IPhone" into the search field
        mainPage.typeTextIntoSearchField(textToSearch);

        String actualTextInSearchField = mainPage.getTextFromSearchField();
        Assert.assertEquals(actualTextInSearchField, textToSearch, "The text isn't correct");
        assertEquals("The text isn't correct", actualTextInSearchField, textToSearch);

        //Step 3: Click Enter button on the keyboard
        mainPage.clickEnterOnSearchField();

        SearchResultsPage searchResultsPage = new SearchResultsPage(page);
        searchResultsPage.waitForResultsLoaded();

        //Verify at least 2 items are present on the Search Results page
        int actualNumOfResults = searchResultsPage.getNumberOfResults();
        assertTrue("Fewer results than expected. \nExpected minimum: " + expectedMinNumOfResults + "\nActual: " + actualNumOfResults,
                actualNumOfResults >= expectedMinNumOfResults);
    }
}
