package Mobile.Tests;

import Common.Constants;
import Common.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class AlloTest extends BaseTest {

    private HashMap<String, String> cityCase = new HashMap<>(){{
        put("Київ","Києві");
        put("Харків","Харкові");
        put("Одеса","Одесі");
        put("Кривий Ріг","Кривому Розі");
        put("Дніпро","Дніпрі");
        put("Львів","Львові");
        put("Запоріжжя","Запоріжжі");
        put("Полтава","Полтаві");
        put("Хмельницький","Хмельницькому");
        put("Вінниця","Вінниці");
        put("Рівне","Рівному");
        put("Тернопіль","Тернополі");
        put("Чернівці","Чернівцях");
        put("Житомир","Житомирі");
        put("Луцьк","Луцьку");
        put("Івано-Франківськ","Івано-Франківську");
        put("Кропивницький","Кропивницькому");
        put("Черкаси","Черкасах");
        put("Ужгород","Ужгороді");
    }};

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

        alloPage.clickMenuButton();
        alloPage.clickLocationButton();

        List<String> cities = alloPage.getAvailableLocations();
        String newCity = cities.get(RandomGenerator.randInteger(cities.size()));

        alloPage.selectCity(newCity);

        String newProductPageTitle = alloPage.getPageTitleText();

        Assert.assertNotEquals(productPageTitle, newProductPageTitle, "Title wasn't changed");
        Assert.assertEquals(newProductPageTitle, String.format("Телевізори у %s", cityCase.get(newCity)));
    }
}
