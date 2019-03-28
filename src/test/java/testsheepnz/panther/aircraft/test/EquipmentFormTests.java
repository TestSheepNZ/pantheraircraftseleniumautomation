package testsheepnz.panther.aircraft.test;

import org.junit.Test;
import testsheepnz.panther.page.EquipmentPage;
import org.junit.Assert;
import testsheepnz.panther.page.HomePage;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.*;

public class EquipmentFormTests extends BaseTest {



    @Test
    public void clickThroughFromHomepageWorks() {

        String testDescription = "Homepage title is TestSheepNZ's page";
        seleniumInstance.get(testProperties.getWebsiteHomepage());
        HomePage homePage = new HomePage(seleniumInstance);
        homePage.waitForPage();
        takeScreenshot(testDescription);
        //assertTrue(homePage.getTitle());
        assertThat(testDescription, seleniumInstance.getTitle(), containsString("TestSheepNZ's page"));

        testDescription = "Clicking ODM button takes to Panther Fuel Calculator titled page";
        homePage.clickODMButton();
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertThat(testDescription, seleniumInstance.getTitle(), containsString("Panther Fuel Calculator"));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void pageOpensSuccessfully() {
        seleniumInstance.goHome();

        String testDescription = "Open page, it should contain Panther ODM";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertThat(testDescription, seleniumInstance.getPageContent(), containsString("Panther ODM"));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void complexTest() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should reject adding 10 missiles";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        equipPage.setInitialFuelField("2222");
        equipPage.setNumMissiles(3);
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumMissile(10));

        //If it gets to the end, test passes
        testPasses=Boolean.TRUE;
    }

    //Missile tests
    @Test
    public void canAdd0Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 0 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(0));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd1Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 1 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(1));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void canAdd8Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 8 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertTrue(testDescription, equipPage.attemptNumMissile(8));
        testPasses=Boolean.TRUE;
    }

    @Test
    public void cannotAdd9Missiles() {
        seleniumInstance.goHome();
        String testDescription;

        testDescription = "Should accept 9 missiles selection";
        EquipmentPage equipPage = new EquipmentPage(seleniumInstance);
        equipPage.waitForPage();
        takeScreenshot(testDescription);
        assertFalse(testDescription, equipPage.attemptNumMissile(9));
        testPasses=Boolean.TRUE;
    }
}
