package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

public class GoogleSearchBasePage {

    protected WebDriver driver;


    /**
     * getCurrent Url method.
     * @return current URL of page.
     */
    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * getCurrent Title method.
     * @return current title of page.
     */
    protected String getCurrentTitle() {
        return driver.getTitle();
    }
    /**
     * waitUntilElementVisible method.
     *
     * Method waits until element become visible on page,
     * @param webElement - webElement is waiting for.
     * @param timeOutInSec -max of seconds will wait.
     * @return webElement that was waited for.
     */
    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    /**
     * assertElementIsVisible method returns assert if web element is not found on page.
     *
     * @param webElement
     * @param timeOutInSec
     */
    protected void assertElementIsVisible (WebElement webElement, int timeOutInSec, String message) {
        try {
            waitUntilElementVisible(webElement, timeOutInSec);
        }
        catch (TimeoutException e) {
            throw new AssertionError (message);
        }
    }

}
