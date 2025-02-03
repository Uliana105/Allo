package Pages.Playwright;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class MainPage {
    private Page mainPage;

    private final Locator searchInput;

    public MainPage(Page page) {
        this.mainPage = page;
        searchInput = mainPage.locator("input#search-form__input");
    }

    public void typeTextIntoSearchField(String text) {
        searchInput.fill(text);
    }

    public void clickEnterOnSearchField() {
        mainPage.keyboard().press("Enter");
    }

    public String getTextFromSearchField() {
        return searchInput.inputValue();
    }

    public void searchProductByText(String text) {
        searchInput.fill(text);
        mainPage.keyboard().press("Enter");
    }
}
