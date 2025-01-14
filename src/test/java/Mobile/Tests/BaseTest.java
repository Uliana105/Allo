package Mobile.Tests;

import AllureUtils.ScreenshotRecorder;
import Common.Constants;
import Mobile.Objects.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    AndroidDriver driver;
    MainScreen mainScreen;
    Actions actions;
    GoogleSearchPage googleSearchPage;
    AlloPage alloPage;
    WebDriverIOApp webDriverIOApp;
    @BeforeMethod
    public void setUp() throws MalformedURLException {

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
//                .setAppPackage("com.android.chrome")
//                .setAppActivity("com.google.android.apps.chrome.Main");
                .setApp("D:\\intellj\\allo1\\src\\main\\resources\\android.wdio.native.app.v1.0.8.apk");
        driver = new AndroidDriver(
                // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                new URL("http://127.0.0.1:4723"), options
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));

        mainScreen = new MainScreen(driver);
        actions = new Actions(driver);
        googleSearchPage = new GoogleSearchPage(driver);
        alloPage = new AlloPage(driver);
        webDriverIOApp = new WebDriverIOApp(driver);

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            try {
                ScreenshotRecorder.takeScreenshot(result.getName(), driver);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        driver.quit();
    }
}
