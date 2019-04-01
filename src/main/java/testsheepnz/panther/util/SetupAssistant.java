package testsheepnz.panther.util;

import testsheepnz.panther.page.EquipmentPage;

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

/*
 * This class helps with tests by doing a lot of setup for certain pages
 * This class assumes you to be on the page you need to be
 * it will also not perform any assertions - this it to set up an instance for testing
 * NOT for testing it
 */
public class SetupAssistant {

    private SeleniumInstance seleniumInstance;
    private TestLog testLog;

    public SetupAssistant(SeleniumInstance testInstance, TestLog log) {
        this.seleniumInstance=testInstance;
        this.testLog=log;
    }

    public void setupStandardEquipment(String fuel) {
        setUpAircraftEquipmentThenSubmit(  fuel,
                                6,
                             4,
                                            Boolean.FALSE,
                                            Boolean.FALSE,
                                            Boolean.FALSE);
    }

    public void setUpAircraftEquipmentThenSubmit(  String   fuel,
                                                   int      numMissiles,
                                                   int      numDumbBombs,
                                                   Boolean  reconPod,
                                                   Boolean  intelliBomb,
                                                   Boolean  externalFuelTanks) {
        setUpAircraftEquipment( fuel,
                                numMissiles,
                                numDumbBombs,
                                reconPod,
                                intelliBomb,
                                externalFuelTanks);
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.clickLoadButton();
    }

    private void setUpAircraftEquipment(String   fuel,
                                       int      numMissiles,
                                       int      numDumbBombs,
                                       Boolean  reconPod,
                                       Boolean  intelliBomb,
                                       Boolean  externalFuelTanks) {
        //Assumes you are already where you need to be on equipment page
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();

        String testDescription="Setting up an aircraft with,";

        if(!fuel.equals("")) {
            equipPage.setInitialFuelField(fuel);
            testDescription += "<br>Add fuel of " + fuel + "kg";
        }

        if(numMissiles!=0) {
            equipPage.setNumMissiles(numMissiles);
            testDescription += "<br>Number of missiles: " + numMissiles;
        }

        if(numDumbBombs!=0) {
            equipPage.setNumDumbBomb(numDumbBombs);
            testDescription += "<br>Number of dumb bombs: " + numDumbBombs;
        }

        if(reconPod){
            equipPage.selectReconPod();
            testDescription += "<br>Recon pod";
        }

        if(intelliBomb){
            equipPage.selectIntelliBomb();
            testDescription += "<br>Intelli bomb";
        }

        if(externalFuelTanks){
            equipPage.selectFuelTank();
            testDescription += "<br>Fuel tanks";
        }

        testLog.addScreenshot(seleniumInstance.getDriver(), "setUpAircraftEquipment", testDescription);

    }

    // Select apply / dive / cruise - all assume on the Status form
    public void selectCruise() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setCruise();
    }

    public void selectClimb() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setClimb();
    }

    public void selectDive() {
        StatusPage statusPage = new StatusPage(seleniumInstance);
        statusPage.waitForPage();
        statusPage.setDive();
    }



}
