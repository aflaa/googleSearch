package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import page.GoogleSearchPage;

public class GoogleSearchBaseTest {

    GoogleSearchPage googleSearchPage;
    WebDriver driver;
    String URL ="https://www.google.com/";

    /**
     * BeforeMethod - method executed before every Test.
     *
     * Scenario:
     * - Open browser.
     * - Navigate to test site link.
     * - Create googleSearchSearchPage.
     */
    @Parameters({"browserName"})
    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod(@Optional("chrome") String browserName) throws Exception {
        setBrowser(browserName);
        driver.get(URL);
        googleSearchPage =  new GoogleSearchPage(driver);
    }

    /**
     * AfterMethod - method executed after every Test.
     *
     * Scenario:
     * -Quit from browser.
     */
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod() {
//        driver.quit();
//    }

    /**
     * setBrowser method check which browser to user for tests.
     */
    public void setBrowser(String browserName) throws Exception {
        switch (browserName.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;

            default:
                throw new Exception ("Browser " + browserName+ " is not supported.");

        }

    }
}
