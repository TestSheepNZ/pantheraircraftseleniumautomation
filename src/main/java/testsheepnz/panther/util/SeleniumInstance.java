package testsheepnz.panther.util;

import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;


//import java.time.Duration;


public class SeleniumInstance {

    private WebDriver driver;
    private WebDriverWait wait;
    TestProperties testProperties;

    //private static final int TIMEOUT = 5;
    //private static final int POLLING = 100;
    private SeleniumInstance() {
        testProperties = new TestProperties();
        this.launchDriver();
    }

    public SeleniumInstance(TestProperties properties) {
        testProperties = properties;
        this.launchDriver();
    }

    private void launchDriver() {
        if(testProperties.getBrowserType().equals(BrowserType.FIREFOX)) {
            System.setProperty("webdriver.gecko.driver", testProperties.getDriverPath() + "geckodriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", testProperties.getDriverPath() + "chromedriver.exe");
            driver = new ChromeDriver();
        }

        wait = new WebDriverWait(driver, 50, 100);
        //wait = new WebDriverWait(driver, testProperties.getMaxWait());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public TestProperties getTestProperties() {
        return testProperties;
    }

    public void get(String pageSource) {
        driver.get(pageSource);
    }

    public void goHome() {
        driver.get(testProperties.getWebsiteToTest());
    }

    public void quit() {
        //driver.close();
        driver.quit();
    }

    public void maximize() {
        driver.manage().window().maximize();
    }

    public String getPageContent() {
        return driver.getPageSource();
    }

    public void setBrowserSize() {
        Dimension dimension = new Dimension(testProperties.getBrowserWidth(), testProperties.getBrowserHeight());
        driver.manage().window().setSize(dimension);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public WebDriverWait getWait() {
        return wait;
    }

}
