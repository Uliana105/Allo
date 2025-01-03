package AllureUtils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotRecorder {
    public static File takeScreenshot(String testName, WebDriver driver) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        //Call getScreenshotAs method to create image file
        File source = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        File destination = new File("./target/screenshots/" + testName + ".png");
        //Copy file at destination
        FileUtils.copyFile(source, destination);
        return destination;
    }
}
