package Mobile.Tests;

import Common.Constants;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MobileTest extends BaseTest{

    @Test
    public void test() {
        actions.clickHomeButton();

        mainScreen.clickOnChromeIcon();
        googleSearchPage.goToHomePage();

        googleSearchPage.openUrl(Constants.BASE_URL);
        actions.clickBlockButtonOnAlert();
        alloMainPage.searchByText("iPhone");

        System.out.println(alloMainPage.getNumberOfResults());
        Assert.assertTrue(alloMainPage.getNumberOfResults() > 2, "Fewer results than expected");
    }
}
