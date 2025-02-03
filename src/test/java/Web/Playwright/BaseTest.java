package Web.Playwright;

import AllureUtils.ScreenshotRecorder;
import AllureUtils.VideoRecorder;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;

public class BaseTest {

    static Playwright playwright;
    static Browser browser;

    File videoFile;
    @BeforeAll
    public static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

//    @AfterMethod(alwaysRun = true)
//    public void tearDown(ITestResult result) {
//        File newFile = new File(videoFile.getParent(), result.getName() + ".avi");
//        try {
//            VideoRecorder.stopRecording();
//
//            //Rename created video record to test name
//            VideoRecorder.renameVideoRecord(videoFile, newFile);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        if (result.getStatus() == ITestResult.FAILURE) {
//            // Attach screenshot to allure report
//            File screenshot = null;
//            try {
//                screenshot = ScreenshotRecorder.takeScreenshot(result.getName(), driver);
//                System.out.println("Attaching screenshot...");
//                Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshot));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//            // Attach video to allure report
//            try {
//                Allure.addAttachment("Video", FileUtils.openInputStream(newFile));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        } else if (result.getStatus() == ITestResult.SUCCESS) {
//            // Delete video for passed tests
//            VideoRecorder.deleteRecordedVideo(newFile);
//        }
//    }
}
