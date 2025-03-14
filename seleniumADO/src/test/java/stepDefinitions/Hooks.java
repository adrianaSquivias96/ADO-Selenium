package stepDefinitions;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;

import io.cucumber.java.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import utils.TestContextSetup;

public class Hooks {
    TestContextSetup testContextSetup;

    public Hooks(TestContextSetup testContextSetup) {

        this.testContextSetup = testContextSetup;
    }

    @BeforeStep
    public void BeforeStep(Scenario scenario) throws IOException {

        testContextSetup.initReportVariables();

    }
    @After
    public void AfterScenario(Scenario scenario) throws IOException {

        WebDriver driver = testContextSetup.testBase.WebDriverManager();

        //si falla toma ss
        /*if (scenario.isFailed()) {
            //screenshot
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");

        }*/

        testContextSetup.testBase.WebDriverManager().quit();

    }

    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        WebDriver driver = testContextSetup.testBase.WebDriverManager();

        //si falla toma ss + bandera de addss
        //if (scenario.isFailed() || testContextSetup.addScreenShot) {
            //screenshot
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
            scenario.attach(fileContent, "image/png", "image");

        //}

        if(!testContextSetup.reportText.isEmpty()){
            scenario.log(testContextSetup.reportText);
        }

    }

}
