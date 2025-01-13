package Mobile.Tests;

import Common.Constants;
import Common.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AlloTest extends BaseTest{

    @BeforeMethod
    public void openAlloMainPage() {
        actions.clickHomeButton();

        mainScreen.clickOnChromeIcon();
        googleSearchPage.goToHomePage();

        googleSearchPage.openUrl(Constants.BASE_URL);
        actions.clickBlockButtonOnAlert();
    }

    @Test
    public void searchByText() {
        alloPage.searchByText("iPhone");

        int num = alloPage.getNumberOfResults();
        System.out.println(num);
        Assert.assertTrue(num > 2, "Fewer results than expected");
    }

    @Test
    public void changeLocationTest() {
        alloPage.clickMenuButton();

        alloPage.clickProductCatalogButton();

        //Select specific category
        alloPage.clickCatalogItem("Телевізори та мультимедіа");
        alloPage.clickCatalogItem("Телевізори");
        alloPage.clickCatalogItem("Всі товари →");

        String productPageTitle = alloPage.getPageTitleText();
        System.out.println(productPageTitle);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        alloPage.clickMenuButton();
        alloPage.clickLocationButton();

        List<String> cities = alloPage.getAvailableLocations();
        System.out.println(cities);
        String newCity = cities.get(RandomGenerator.generateRandomInteger(cities.size()));
        alloPage.selectCity(newCity);

        String newProductPageTitle = alloPage.getPageTitleText();
        System.out.println(newProductPageTitle);

        Assert.assertNotEquals(productPageTitle, newProductPageTitle, "Title wasn't changed");
        Assert.assertEquals(newProductPageTitle, String.format("Телевізори у %s", newCity));
    }
}
