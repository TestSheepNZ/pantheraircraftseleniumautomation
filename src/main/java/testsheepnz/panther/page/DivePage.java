package testsheepnz.panther.page;

import org.hamcrest.BaseDescription;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class DivePage extends BasePage {
    //Define the IDs ...
    private static final String DIVE_FORM = "diveForm";
    private static final String DIVE_ALTITUDE_FIELD = "diveAltitude";
    private static final String DIVE_APPLY_BUTTON = "applyDive";

    public DivePage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(DIVE_FORM)));
    }

    public void waitForPageToVanish() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(DIVE_FORM)));
    }


    //Altitude field
    @FindBy(id = DIVE_ALTITUDE_FIELD)
    private WebElement altitudeField;

    public int getDiveAltitude() { return Integer.parseInt(altitudeField.getAttribute("value")); }

    public void setDiveAltitude(final String enterStr) {
        altitudeField.sendKeys(enterStr);
    }


    //Select apply button
    @FindBy(id = DIVE_APPLY_BUTTON)
    private WebElement applyButton;

    public void clickApplyButton() {
        applyButton.click();
    }

}
