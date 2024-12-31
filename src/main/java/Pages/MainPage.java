package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPage {
    protected WebDriver driver;
    private PageTools pageTools;

    public MainPage(WebDriver driver){
        this.driver = driver;
        pageTools = new PageTools(driver);
    }
    private final By searchInput = By.cssSelector("input#search-form__input");

    @Step("Type {0} into search field")
    public void typeTextIntoSearchField(String text) {
        driver.findElement(searchInput).sendKeys(text);
    }

    @Step("Click enter button to search")
    public void clickEnterOnSearchField() {
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public String getTextFromSearchField() {
        return driver.findElement(searchInput).getDomProperty("value");
    }

    @Step("Search product by text {0}")
    public void searchProductByText(String text) {
        driver.findElement(searchInput).sendKeys(text);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }
}
