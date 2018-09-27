package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage extends GoogleSearchBasePage{

    @FindBy(xpath = "//input[@name='btnK']")
    private WebElement searchButton;
    @FindBy(xpath = "//input[@id='lst-ib']")
    private WebElement searchField;


    /**
     * Constructor of GoogleSearchSearchPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public GoogleSearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(searchField,20, "GoogleSearchPage is not loaded" );
    }


    public GoogleResultPage search(String searchTerm) {
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);

        return new GoogleResultPage(driver);
    }
}
