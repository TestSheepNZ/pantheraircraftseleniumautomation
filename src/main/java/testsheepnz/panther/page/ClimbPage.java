package testsheepnz.panther.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class ClimbPage extends BasePage {
    //Define the IDs ...
    private static final String CLIMB_FORM = "climbForm";
    private static final String CLIMB_ALTITUDE_FIELD = "climbAltitude";
    private static final String CLIMB_APPLY_BUTTON = "applyClimb";

    public ClimbPage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(CLIMB_FORM)));
    }

    public void waitForPageToVanish() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(CLIMB_FORM)));
    }

    //Altitude field
    @FindBy(id = CLIMB_ALTITUDE_FIELD)
    private WebElement altitudeField;

    public int getClimbAltitude() { return Integer.parseInt(altitudeField.getAttribute("value")); }

    public void setClimbAltitude(final String enterStr) {
        altitudeField.sendKeys(enterStr);
    }


    //Select apply button
    @FindBy(id = CLIMB_APPLY_BUTTON)
    private WebElement applyButton;

    public void clickApplyButton() {
        applyButton.click();
    }

}
