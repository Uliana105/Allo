package Mobile.Tests;

import Common.Constants;
import Mobile.Objects.Actions;
import Mobile.Objects.AlloPage;
import Mobile.Objects.GoogleSearchPage;
import Mobile.Objects.MainScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {

    AndroidDriver driver;
    MainScreen mainScreen;
    Actions actions;
    GoogleSearchPage googleSearchPage;
    AlloPage alloMainPage;
    @BeforeMethod
    public void setUp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("deviceName", "Google Pixel 9 API 35");
        capabilities.setCapability("platformVersion", "15");
        capabilities.setCapability("app", "D:\\intellj\\allo1\\src\\main\\resources\\android.wdio.native.app.v1.0.8.apk");
        capabilities.setCapability("automationName", "UiAutomator2");

        UiAutomator2Options options = new UiAutomator2Options()
                .setUdid("emulator-5554")
                .setApp("D:\\intellj\\allo1\\src\\main\\resources\\android.wdio.native.app.v1.0.8.apk");
        driver = new AndroidDriver(
                // The default URL in Appium 1 is http://127.0.0.1:4723/wd/hub
                new URL("http://127.0.0.1:4723"), options
        );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.IMPLICIT_WAIT));

        mainScreen = new MainScreen(driver);
        actions = new Actions(driver);
        googleSearchPage = new GoogleSearchPage(driver);
        alloMainPage = new AlloPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
