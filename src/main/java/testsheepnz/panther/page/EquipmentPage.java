package testsheepnz.panther.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import testsheepnz.panther.util.SeleniumInstance;
import org.openqa.selenium.By;

public class EquipmentPage extends BasePage {

    //Define the IDs ...
    private static final String EQUIPMENT_FORM = "equipmentForm";
    private static final String INITIAL_FUEL_FIELD = "initialFuel";
    private static final String NUM_MISSILES_DROPDOWN = "numMissiles";

    private static final String LOAD_BUTTON = "setEquipmentButton";

    public EquipmentPage(SeleniumInstance test) {
        super(test);
    }

    // Wait for page
    public void waitForPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(EQUIPMENT_FORM)));
    }

    //Fuel field
    @FindBy(id = INITIAL_FUEL_FIELD)
    private WebElement fuelField;

    public void setInitialFuelField(final String enterStr) {
        fuelField.sendKeys(enterStr);
    }

    public void clearInitialFuelField() {
        fuelField.clear();
    }

    public String getInitialFuelField() {
        return fuelField.getAttribute("value");
    }

    //Num missiles
    @FindBy(id = NUM_MISSILES_DROPDOWN)
    private WebElement numMissilesDropdown;

    public void setNumMissiles(final int numMissiles) {
        Select dropdown = new Select(numMissilesDropdown);
        dropdown.selectByIndex(numMissiles);
    }

    public Boolean attemptNumMissile(final int numMissiles) {
        Boolean retValue = Boolean.TRUE;

        try {
            this.setNumMissiles(numMissiles);
            return Boolean.TRUE;
        } catch (Exception e) {
            //log.error("Num missiles element " + numMissiles + " not found");
            return Boolean.FALSE;
        }
    }

    public String getNumMissile() {
        return numMissilesDropdown.getText();
    }

    /*
    @FindBy(id = LOGOUT_FORM_LOGOUT_BTN)
    private WebElement logoutButton;

    public void clickIvsRemoveYourIdentityButton() {
        WebElement element = getElementById(REMOVE_YOUR_IDENTITY);
        logButtonClick(AttributeEnum.NAME, REMOVE_YOUR_IDENTITY);
        element.click();
    }
    */

    //Set equipment button
    @FindBy(id = LOAD_BUTTON)
    private WebElement loadButton;

    public void clickLoadButton() {
        loadButton.click();
    }

    // ACTIONS

    // RETURN VALUES


}
