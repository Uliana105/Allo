package Mobile.Tests;

import Common.Constants;
import Common.RandomGenerator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public void filterResultsByPrice() {
        alloPage.searchByText("iPhone");

        alloPage.clickFilterBtn();

        alloPage.setMinPriceToPercent(0.25);

        alloPage.setMaxPriceToPercent(0.75);

        int minPrice = alloPage.getMinPrice();
        int maxPrice = alloPage.getMaxPrice();

        alloPage.clickShowResultsBtn();

        Set<Integer> prices = alloPage.getResultsPrices();

        Assert.assertTrue(prices.stream().allMatch(num -> num >= minPrice && num <= maxPrice),
                "Prices aren't in range: " + prices.stream()
                        .filter(number -> number < minPrice || number > maxPrice)
                        .collect(Collectors.toList()));
    }

    @Test
    public void changeLocationTest() {
        //Select specific category
        alloPage.clickMenuButton();
        alloPage.clickProductCatalogButton();
        alloPage.clickCatalogItem("Телевізори та мультимедіа");
        alloPage.clickCatalogItem("Телевізори");
        alloPage.clickCatalogItem("Всі товари →");

        String productPageTitle = alloPage.getPageTitleText(); // get current title

        // go to location selection
        alloPage.clickMenuButton();
        alloPage.clickLocationButton();

        // randomize new city
        List<String> cities = alloPage.getAvailableLocations();
        String newCity = cities.get(RandomGenerator.randInteger(cities.size()));

        alloPage.selectCity(newCity); // select new city

        String newProductPageTitle = alloPage.getPageTitleText(); // get new title

        Assert.assertNotEquals(productPageTitle, newProductPageTitle, "Title wasn't changed");
        Assert.assertEquals(newProductPageTitle, String.format("Телевізори у %s", cityCase.get(newCity)));
    }
}
