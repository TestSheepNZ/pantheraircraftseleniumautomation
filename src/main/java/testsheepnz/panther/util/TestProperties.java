package testsheepnz.panther.util;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


enum BrowserType {
    CHROME, FIREFOX;
}

enum MachineType {
    WINDOWS, MAC, LINUX;
}

public class TestProperties {
    private BrowserType browserInUse;
    private MachineType machineInUse;
    private String driverPath;
    private String screenshotsPath;
    private String websiteToTest;
    private String websiteHomepage;
    private long maxWait;
    private int browserWidth;
    private int browserHeight;

    public TestProperties() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("test.properties");

            // load a properties file
            prop.load(input);

            // Set browser type
            this.setBrowserType(prop.getProperty("browser"));

            //Set machine type
            this.setBrowserType(prop.getProperty("machine_type"));
            switch (prop.getProperty("machine_type")) {
                case "MAC":
                    machineInUse = MachineType.MAC;
                    driverPath = prop.getProperty("mac_driver_path");
                    break;
                case "LINUX":
                    machineInUse = MachineType.LINUX;
                    driverPath = prop.getProperty("linux_driver_path");
                    break;
                default:
                    machineInUse = MachineType.WINDOWS;
                    driverPath = prop.getProperty("windows_driver_path");
                    break;
            }

            screenshotsPath = prop.getProperty("screenshots");
            maxWait = Long.parseLong(prop.getProperty("max_wait"));
            setWebsiteToTest(prop.getProperty("website_under_test"));
            setWebsiteHomepage(prop.getProperty("website_homepage"));
            browserWidth= Integer.parseInt(prop.getProperty("browser_width"));
            browserHeight=Integer.parseInt(prop.getProperty("browser_height"));
        } catch (
        IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setBrowserType(final String newBrowser) {
        switch (newBrowser) {
            case "FIREFOX_BROWSER":
                browserInUse = BrowserType.FIREFOX;
                break;
            default:
                browserInUse = BrowserType.CHROME;
        }
    }

    public BrowserType getBrowserType() {
        return browserInUse;
    }

    public String getDriverPath() {
        return driverPath;
    }

    public void setWebsiteToTest(final String newURL) {
        websiteToTest=newURL;
    }

    public String getWebsiteToTest() {
        return websiteToTest;
    }

    public void setWebsiteHomepage(final String homeURL) {
        websiteHomepage=homeURL;
    }

    public String getWebsiteHomepage() { return websiteHomepage; }

    public String getScreenshotsPath() {
        return screenshotsPath;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public int getBrowserHeight() {
        return browserHeight;
    }

    public int getBrowserWidth() {
        return browserWidth;
    }

}


