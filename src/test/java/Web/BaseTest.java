package Web;

import AllureUtils.ScreenshotRecorder;
import AllureUtils.VideoRecorder;
import Common.CommonActions;
import Pages.MainPage;
import Pages.SearchResultsPage;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    WebDriver driver;

    MainPage mainPage;
    SearchResultsPage searchResultsPage;

    File videoFile;
    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
        videoFile = VideoRecorder.startRecording();
        driver = CommonActions.createDriver();

        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        File newFile = new File(videoFile.getParent(), result.getName() + ".avi");
        try {
            VideoRecorder.stopRecording();

            //Rename created video record to test name
            VideoRecorder.renameVideoRecord(videoFile, newFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            // Attach screenshot to allure report
            File screenshot = null;
            try {
                screenshot = ScreenshotRecorder.takeScreenshot(result.getName(), driver);
                System.out.println("Attaching screenshot...");
                Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshot));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            // Attach video to allure report
            try {
                Allure.addAttachment("Video", FileUtils.openInputStream(newFile));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            // Delete video for passed tests
            VideoRecorder.deleteRecordedVideo(newFile);
        }

        if (driver != null) {
            driver.quit();
        }
    }
}
