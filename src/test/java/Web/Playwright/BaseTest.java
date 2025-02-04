package Web.Playwright;

import com.microsoft.playwright.*;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class BaseTest {

    static Playwright playwright;
    static Browser browser;
    static BrowserContext context;
    static Page page;

    @BeforeMethod
    public void launchBrowser() {
        ArrayList<String> args = new ArrayList<>();
        args.add("--start-maximized");
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
                .setArgs(args));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null)
                .setRecordVideoDir(Paths.get("./target/videos/")));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            File screenshot = new File("./target/screenshots/" + result.getName() + result.getStartMillis() + ".png");
            page.screenshot(new Page.ScreenshotOptions().setPath(screenshot.toPath()));
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenshot));
            Allure.addAttachment("Video", FileUtils.openInputStream(page.video().path().toFile()));
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            page.video().delete();
        }

        context.tracing().stop(new Tracing.StopOptions()
                .setPath(new File("./target/trace/" + result.getName() + result.getStartMillis() + ".zip").toPath()));

        context.close();
        playwright.close();
    }
}
