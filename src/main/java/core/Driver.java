package core;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class Driver {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (threadDriver.get() == null) {
            initDriver();
        }
        return threadDriver.get();
    }

    private static void initDriver() {
        String browser = System.getProperty("browser", "chrome");
        String resolution = System.getProperty("resolution", "max");
        switch (browser) {
            case "chrome":
                chromedriver().setup();
                threadDriver.set(new ChromeDriver());
                break;
            case "firefox":
                firefoxdriver().setup();
                threadDriver.set(new FirefoxDriver());
                break;
            case "edge":
                edgedriver().setup();
                threadDriver.set(new EdgeDriver());
                break;
            case "IE":
                iedriver().setup();
                threadDriver.set(new InternetExplorerDriver());
                break;
            case "opera":
                operadriver().setup();
                threadDriver.set(new OperaDriver());
                break;
            default:
                throw new IllegalArgumentException("Entered browser value is not recognized");
        }
        switch (resolution) {
            case "1024x768":
                threadDriver.get().manage().window().setSize(new Dimension(1024, 768));
                break;
            case "800x600":
                threadDriver.get().manage().window().setSize(new Dimension(800, 600));
                break;
            case "max":
                threadDriver.get().manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException("Entered resolution value is not recognized");
        }
        threadDriver.get().manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        threadDriver.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        threadDriver.get().manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
    }

    public static void killDriver() {
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            threadDriver.remove();
        }
    }

    public static WebElement wailVisibleLocated(By locator, int second) {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(second)).until(
                ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
