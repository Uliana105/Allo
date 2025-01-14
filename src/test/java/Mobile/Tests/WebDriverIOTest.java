package Mobile.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WebDriverIOTest extends BaseTest{

    @Test
    public void horizontalScroll() {
        webDriverIOApp.clickMenuSwipeBtn();
        webDriverIOApp.swipeToElementsInCarouselPresent("5");

        Assert.assertTrue(webDriverIOApp.isElementInCarouselVisible("5"), "Element isn't visible");
    }

    @Test
    public void fillInForm() {
        webDriverIOApp.clickMenuFormsBtn();

        String dropdownOption = "Appium is awesome";
        webDriverIOApp.clickDropdownSelect();
        webDriverIOApp.selectDropdownOption(dropdownOption);

        String switcherDescription = webDriverIOApp.getSwitcherDescriptionText();
        webDriverIOApp.clickSwitcher();

        Assert.assertNotEquals(webDriverIOApp.getSwitcherDescriptionText(), switcherDescription);
        Assert.assertEquals(webDriverIOApp.getDropdownSelectedText(), dropdownOption, "Incorrect option selected");
    }

    @Test
    public void completePuzzle() {
        webDriverIOApp.clickMenuDragBtn();

        for (int i = 1; i <= 3; i++) {
            webDriverIOApp.moveSquares("l" + i);
            webDriverIOApp.moveSquares("c" + i);
            webDriverIOApp.moveSquares("r" + i);
        }

        Assert.assertTrue(webDriverIOApp.isSuccessfulMessageVisible(), "Puzzle isn't completed");
    }
}
