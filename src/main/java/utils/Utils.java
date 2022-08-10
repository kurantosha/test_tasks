package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import static core.Driver.getDriver;
import static core.Driver.wailVisibleLocated;

public class Utils {

    private static Actions actions;

    @Step("Scroll")
    public static void scrollToElement(WebDriver driver, By element) {
        actions = new Actions(driver);
        int attempts = 0;
        while (attempts < 2) {
            try {
                actions.moveToElement(wailVisibleLocated(element, 10)).build().perform();
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
    }

    public static int getCountTabs() {
        return getDriver().getWindowHandles().size();
    }

    public static boolean wasOpenedNewTab() {
        if (getCountTabs() > 1) {
            return true;
        }
        return false;
    }
}