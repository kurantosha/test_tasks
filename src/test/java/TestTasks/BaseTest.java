package TestTasks;

import core.Driver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static core.Driver.getDriver;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        getDriver().get("https://www.xm.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver(ITestResult result) {
        if (!result.isSuccess()) {
            screenCapture();
        }
        Driver.killDriver();
    }

    @Attachment(type = "image/png")
    private byte[] screenCapture() {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}