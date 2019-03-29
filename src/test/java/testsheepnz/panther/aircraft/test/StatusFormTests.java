package testsheepnz.panther.aircraft.test;

import org.junit.BeforeClass;
import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import org.junit.Assert;
import testsheepnz.panther.page.HomePage;
import testsheepnz.panther.page.StatusPage;
import testsheepnz.panther.util.SetupAssistant;
import testsheepnz.panther.util.TestLog;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;


public class StatusFormTests extends BaseTest {

    @Test
    public void aircraftWith6000kgFuel8Missiles6DumbBombsExternalFuelTanksWeight() {
        seleniumInstance.goHome();

        //Use assistant to setup aircraft
        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setUpAircraftEquipmentThenSubmit(   "6000",
                                                8,
                                            6,
                                                           Boolean.FALSE,
                                                           Boolean.FALSE,
                                                           Boolean.TRUE );

        String testDescription = "All up weight should be 17000kg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, statusPage.getWeight(), 17000);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void setupAircraftStartsWithLeg0() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardEquipment("2000");

        String testDescription = "Setup aircraft starts with leg 0";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, statusPage.getLegNumber(), 0);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void setupAircraftStartsWithAltitude0() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardEquipment("2000");

        String testDescription = "Setup aircraft starts with altitude of 0";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, statusPage.getHeight(), 0);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void setupAircraftStartsWithEnteredFuel2000() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardEquipment("2000");

        String testDescription = "Setup aircraft starts with entered fuel of 2000";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, statusPage.getFuelRemaining(), 2000);
        testPasses=Boolean.TRUE;
    }

    @Test
    public void setupAircraftHasFuelLastUsedOf0() {
        seleniumInstance.goHome();

        SetupAssistant assistant = new SetupAssistant(seleniumInstance, testLog);
        assistant.setupStandardEquipment("2000");

        String testDescription = "Setup aircraft has used 0kg fuel last leg";
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        takeScreenshot(testDescription);
        assertEquals(testDescription, statusPage.getFuelUsedLastLeg(), 0);
        testPasses=Boolean.TRUE;
    }

}
