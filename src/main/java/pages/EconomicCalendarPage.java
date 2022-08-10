package pages;

import enums.CalendarTabs;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

import static core.Driver.getDriver;
import static utils.DateUtils.*;

public class EconomicCalendarPage extends BasePage {

    private final WebElement iframeElement = getDriver().findElement(By.xpath("//iframe[@title='economicCalendar']"));
    private final By yesterdayButton = By.xpath("//a[@id='timeFrame_yesterday']");
    private final By todayButton = By.id("timeFrame_today");
    private final By tomorrowButton = By.id("timeFrame_tomorrow");
    private final By thisWeekButton = By.id("timeFrame_thisWeek");
    private final By dateRange = By.xpath("//div[@id='widgetFieldDateRange']");
    private final By hereFromDisclaimerButton = By.xpath("//a[contains(@href,'risk_warning')]");
    private final By hiddenCalendarTabsButton = By.xpath("//li[@class='saveSpace']");

    @Step("Click on tab [{tab}]")
    public EconomicCalendarPage clickOnCalendarTab(final CalendarTabs tab) {
        switch (CalendarTabs.valueOf(String.valueOf(tab))) {
            case YESTERDAY:
                if (getDriver().manage().window().getSize().getWidth() < 1200 && getDriver().manage().window().getSize().getWidth() >= 992) {
                    getDriver().switchTo().frame(iframeElement).findElement(hiddenCalendarTabsButton).click();
//                    find(hiddenCalendarTabsButton).click();
                    find(yesterdayButton).click();
                } else {
                    getDriver().switchTo().frame(iframeElement).findElement(yesterdayButton).click();
                }
                break;
            case TODAY:
                if (getDriver().manage().window().getSize().getWidth() < 1200 && getDriver().manage().window().getSize().getWidth() >= 992) {
                    find(hiddenCalendarTabsButton).click();
                    find(todayButton).click();
                } else {
                    find(todayButton).click();
                }
                break;
            case TOMORROW:
                if (getDriver().manage().window().getSize().getWidth() < 1200 && getDriver().manage().window().getSize().getWidth() >= 992) {
                    find(hiddenCalendarTabsButton).click();
                    find(tomorrowButton).click();
                } else {
                    find(tomorrowButton).click();
                }
                break;
            case THIS_WEEK:
                if (getDriver().manage().window().getSize().getWidth() < 1200 && getDriver().manage().window().getSize().getWidth() >= 992) {
                    find(hiddenCalendarTabsButton).click();
                    find(thisWeekButton).click();
                } else {
                    find(thisWeekButton).click();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + CalendarTabs.valueOf(String.valueOf(tab)));
        }
        return this;
    }

    @Step("Check is active Tab  - [{tab}]")
    public EconomicCalendarPage checkIsActiveTab(final CalendarTabs tab) {
        switch (CalendarTabs.valueOf(String.valueOf(tab))) {
            case YESTERDAY:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.attributeContains(find(yesterdayButton), "class", "toggled"));
                break;
            case TODAY:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.attributeContains(todayButton, "class", "toggled"));
                break;
            case TOMORROW:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.attributeContains(find(tomorrowButton), "class", "toggled"));
                break;
            case THIS_WEEK:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.attributeContains(find(thisWeekButton), "class", "toggled"));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + CalendarTabs.valueOf(String.valueOf(tab)));
        }
        return this;
    }

    @Step("Check is correct date - [{tab}]")
    public String checkIsCorrectDate(final CalendarTabs tab) {
        switch (CalendarTabs.valueOf(String.valueOf(tab))) {
            case YESTERDAY:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.textToBe(dateRange, getActualYesterdayDate()));
                break;
            case TODAY:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.textToBe(dateRange, getActualTodayDate()));
                break;
            case TOMORROW:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.textToBe(dateRange, getActualTomorrowDate()));
                break;
            case THIS_WEEK:
                new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                        ExpectedConditions.textToBe(dateRange, getActualThisWeekDate()));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + CalendarTabs.valueOf(String.valueOf(tab)));
        }
        return find(dateRange).getText();
    }

    @Step("click On Here From Disclaimer Button")
    public RiskWarningPage clickOnHereFromDisclaimerButton() {
        getDriver().switchTo().parentFrame();
        Utils.scrollToElement(getDriver(), hereFromDisclaimerButton);
        new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                ExpectedConditions.presenceOfElementLocated(hereFromDisclaimerButton)).click();
        return new RiskWarningPage();
    }
}