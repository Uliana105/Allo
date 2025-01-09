package Web;

import Common.Constants;
import io.qameta.allure.Feature;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners({AllureTestNg.class})
public class SearchProductTest extends BaseTest{
    String textToSearch = "IPhone";
    int expectedMinNumOfResults = 2;

    @Test
    @Feature("Search product by text")
    public void searchProductByText() {
        //Step 1: Open Allo Main page
        driver.get(Constants.BASE_URL);

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
}
