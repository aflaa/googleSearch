package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * GoogleResult Page Object
 */
public class GoogleResultPage extends GoogleSearchBasePage {

    @FindBy(xpath = "//div[@id='resultStats']")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//div[@class='g']/div[contains(@data-hveid, 'QAA')]")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//a[@aria-label='Page 2']")
    private WebElement secondPageButton;

    @FindBy(xpath = "//div[contains(text(), 'Page 2 of about')]")
    private WebElement secondPageResultsTotal;

    @FindBy(xpath = "//div[contains(text(), 'Сторінка 2 з такої приблизної кількості результатів')]")
    private WebElement secondPageResultsTotalUkr;

    /**
     * Constructor of GoogleSearchSearchPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public GoogleResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(searchResultsTotal,20, "GoogleResultPage is not loaded" );
    }

    /**
     * getSearchResultsNumber method - calculates number of found results on page.
     * @return
     */
    public int getSearchResultsNumber() {
            return searchResults.size();
        }

    /**
     * getSearchReasultList method - scroll to every found result and get it's text.
     * @return String list of text results.
     */
    public List<String> getSearchReasultList() {
        List<String> SearchReasultList = new ArrayList<String>();
        for (WebElement searchResult : searchResults) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchResult);
            SearchReasultList.add(searchResult.getText());
        }
        return SearchReasultList;
    }

    /**
     * Method navigates to 2nd search page
     */
    public void goOn2ndPage () {
        secondPageButton.click();
    }

    /**
     * isPageLoaded method. Checks 2nd Page loaded.
     * @return true, when esecondPageResultsTotal found.
     */
    public boolean is2ndPageLoaded() {
              return secondPageResultsTotalUkr.isDisplayed()|| secondPageResultsTotal.isDisplayed();
    }

}
