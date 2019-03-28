package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import org.junit.Assert;
import testsheepnz.panther.page.HomePage;
import testsheepnz.panther.util.TestLog;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class FuelLoadTest extends BaseTest {

    private static final String ERROR_MESSAGE_NO_FUEL_TANKS = "Max fuel level of 3000kg";
    private static final String ERROR_MESSAGE_WITH_FUEL_TANKS = "Max fuel level of 6000kg";

    @BeforeClass
    public static void setAppropriateLogName() {
        String calledFrom = "FuelLoadTest";
        testLog.appendLogFileNameAccordingToTestsRun(calledFrom);
    }

    // Fuel limit
    @Test
    public void fuelLoadOfEmptyRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with no fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOf0kgRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 0kg fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("0");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOfTextRejected() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with text string characters in fuel";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("hello");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOf3001kgRejectedWithNoFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 3001kg with no fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("3001");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(ERROR_MESSAGE_NO_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void fuelLoadOf6001kgRejectedWithFuelTank() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Reject loading aircraft with 6001kg with fuel tank";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.selectFuelTank();
        equipPage.setInitialFuelField("6001");
        equipPage.clickLoadButton();
        takeScreenshot(testDescription);
        assertThat(testDescription, equipPage.getErrorMessage(), containsString(ERROR_MESSAGE_WITH_FUEL_TANKS));
        testPasses=Boolean.TRUE;
    }

}
