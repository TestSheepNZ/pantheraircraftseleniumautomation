package testsheepnz.panther.util;

import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLog {
    private GenerateStringFromTime generateString;
    protected TestProperties testProperties;
    //private FileWriter fileWriter;
    private String fileName;

    private static final String IMG_FOLDER = "img";


    public TestLog(TestProperties properties) {
        generateString = new GenerateStringFromTime();
        testProperties = properties;

        String uniqueName = generateString.getUniqueName();
        String testLogStr = "Test log " + generateString.getCurrentDate();
        fileName = testProperties.getScreenshotsPath() + "/" + uniqueName + "-" + testLogStr + ".html";

        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write("<!DOCTYPE html>");
            fileWriter.write("<html>");
            fileWriter.write("\t<head>");
            fileWriter.write("\t\t<base target=\"_top\">");
            fileWriter.write("\t\t<title>" + testLogStr + "</title>");
            fileWriter.write("\t\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            fileWriter.write("\t\t<link rel=\"stylesheet\" href=\"css/bootstrap.min.css\">");
            fileWriter.write("\t\t<link rel=\"stylesheet\" href=\"css/bootstrap-responsive.css\">");
            fileWriter.write("\t</head>");
            fileWriter.write("    <body>");
            fileWriter.write("");
            fileWriter.write("\t\t<h1>" + testLogStr + "</h1>");
            fileWriter.write("\t\t<p>This page gives an overview of the recent test run initiated at " + generateString.getJustTime() + " on " + generateString.getJustDate() + "</p>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeLog() {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("\t\t</table>");
            fileWriter.write("    </body>");
            fileWriter.write("</html>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openTable(String testName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("\t\t<h2>Test: " + testName + "</h2>");
            fileWriter.write("\t\t<table class=\"table table-hover table-dark\" style=\"width:100%\">");
            fileWriter.write("\t\t<tr>");
            fileWriter.write("\t\t\t<th><b>Time</b></th>");
            fileWriter.write("\t\t\t<th><b>Action</b></th> ");
            fileWriter.write("\t\t\t<th><b>Screenshot</b></th>");
            fileWriter.write("\t\t</tr>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeTable() {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("\t\t</table>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addScreenshot(WebDriver driver, String testName, String description) {
        String imgFile = IMG_FOLDER + "/" + generateString.getUniqueName() + "-" + testName + ".gif";

        //take screenshot
        TakesScreenshot scrShot =((TakesScreenshot)driver);
        File sourceFile=scrShot.getScreenshotAs(OutputType.FILE);
        File destinationFile=new File(testProperties.getScreenshotsPath() + "/" + imgFile);
        try {
            FileHandler.copy(sourceFile, destinationFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //add screenshot and details to log

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            fileWriter.write("\t\t<tr>");
            fileWriter.write("\t\t\t<td>"+generateString.getJustTime()+"</td>");
            fileWriter.write("\t\t\t<td>"+description+"</td>");
            fileWriter.write("\t\t\t<td><img src=\""+imgFile+"\" width=\"" + testProperties.getBrowserWidth()
                    + "\" height=\"" + testProperties.getBrowserHeight() + "\"></td>");
            fileWriter.write("\t\t</tr>");
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
