package Mobile.Objects;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class WebDriverIOApp {
    private AndroidDriver driver;
    private PageTools pageTools;

    public WebDriverIOApp(AndroidDriver driver){
        this.driver = driver;
        pageTools = new PageTools(driver);
    }

    private By homeScreen = AppiumBy.xpath("//android.widget.ScrollView[@content-desc=\"Home-screen\"]");

    // Bottom menu
    private By swipeBtn = AppiumBy.xpath("//android.view.View[@content-desc=\"Swipe\"]");
    private By formsBtn = AppiumBy.xpath("//android.widget.TextView[@text=\"Forms\"]");
    private By dragBtn = AppiumBy.xpath("//android.widget.TextView[@text=\"Drag\"]");

    // Swipe page
    private String carouselItems = "//android.view.ViewGroup[@resource-id=\"__CAROUSEL_ITEM_%s_READY__\"]";
    private By secondCarouselItem = AppiumBy.xpath("(//android.view.ViewGroup[@content-desc=\"card\"])[2]");

    //Forms page
    private By dropdownSelect = AppiumBy.xpath("//android.widget.EditText[@resource-id=\"text_input\"]");
    private String dropdownOption = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"%s\"]";
    private By switcher = AppiumBy.xpath("//android.widget.Switch[@content-desc=\"switch\"]");
    private By switcherDescriptionText = AppiumBy.xpath("//android.widget.TextView[@content-desc=\"switch-text\"]");

    // Drag page
    private String dropZone = "//android.view.ViewGroup[@content-desc=\"drop-%s\"]";
    private String squereToDrag = "//android.view.ViewGroup[@content-desc=\"drag-%s\"]/android.widget.ImageView";
    private By successfulMessage = AppiumBy.xpath("//android.widget.TextView[@text=\"Congratulations\"]");


    // Bottom menu
    public void clickMenuSwipeBtn() {
        driver.findElement(swipeBtn).click();
    }

    public void clickMenuFormsBtn() {
        driver.findElement(formsBtn).click();
    }

    public void clickMenuDragBtn() {
        driver.findElement(dragBtn).click();
    }

    // Swipe page
    public void swipeElementsInCarousel() {
        while (!pageTools.isElementPresent(AppiumBy.xpath(String.format(carouselItems, "5")))) {
            pageTools.swipeElementToTheLeft(secondCarouselItem);
        }
    }

    public void swipeToElementsInCarouselPresent(String num) {
        do {
            pageTools.swipeElementToTheLeft(secondCarouselItem);
        } while (!pageTools.isElementPresent(AppiumBy.xpath(String.format(carouselItems, num))));
    }

    public boolean isElementInCarouselVisible(String num) {
        return pageTools.isElementPresent(AppiumBy.xpath(String.format(carouselItems, num)));
    }

    //Forms page
    public void clickDropdownSelect() {
        driver.findElement(dropdownSelect).click();
    }

    public void selectDropdownOption(String option) {
        driver.findElement(AppiumBy.xpath(String.format(dropdownOption, option))).click();
    }

    public String getDropdownSelectedText() {
        return driver.findElement(dropdownSelect).getText();
    }

    public void clickSwitcher() {
        driver.findElement(switcher).click();
    }

    public String getSwitcherDescriptionText() {
        return driver.findElement(switcherDescriptionText).getText();
    }

    //Drag
    public void moveSquares(String elementsID) {
        pageTools.moveElement(
                AppiumBy.xpath(String.format(squereToDrag, elementsID)),
                AppiumBy.xpath(String.format(dropZone, elementsID))
        );
    }

    public boolean isSuccessfulMessageVisible() {
        return pageTools.isElementPresent(successfulMessage);
    }
}
