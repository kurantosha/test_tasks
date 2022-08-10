package TestTasks;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import pages.EconomicCalendarPage;
import pages.MainPage;

import static enums.CalendarTabs.*;
import static utils.DateUtils.*;
import static utils.Utils.wasOpenedNewTab;

public class AutomationTestingUITest extends BaseTest {

    @Test
    public void checkWasOpenedDocumentInNewTabTest() {

        MainPage mainPage = new MainPage();
        SoftAssertions softAssertions = new SoftAssertions();

        EconomicCalendarPage economicCalendarPage = mainPage.openMainPage().
                checkThatCookiesPopupClosed()
                .clickOnRiskCloseButton()
                .getMainMenuBlock()
                .clickOnResearchAndEducationMainMenuButton()
                .clickOnEconomicCalendarMainMenuButton();

        String expectedYesterdayDate = economicCalendarPage.clickOnCalendarTab(YESTERDAY)
                .checkIsActiveTab(YESTERDAY)
                .checkIsCorrectDate(YESTERDAY);

        String expectedTodayDate = economicCalendarPage.clickOnCalendarTab(TODAY)
                .checkIsActiveTab(TODAY)
                .checkIsCorrectDate(TODAY);

        String expectedTomorrowDate = economicCalendarPage.clickOnCalendarTab(TOMORROW)
                .checkIsActiveTab(TOMORROW)
                .checkIsCorrectDate(TOMORROW);

        String expectedThisWeekDate = economicCalendarPage.clickOnCalendarTab(THIS_WEEK)
                .checkIsActiveTab(THIS_WEEK)
                .checkIsCorrectDate(THIS_WEEK);

        softAssertions.assertThat(getActualYesterdayDate())
                .isEqualTo(expectedYesterdayDate);

        softAssertions.assertThat(getActualTodayDate())
                .isEqualTo(expectedTodayDate);

        softAssertions.assertThat(getActualTomorrowDate())
                .isEqualTo(expectedTomorrowDate);

        softAssertions.assertThat(getActualThisWeekDate())
                .isEqualTo(expectedThisWeekDate);

        economicCalendarPage
                .clickOnHereFromDisclaimerButton()
                .clickOnHereFromRiskWarning();

        softAssertions.assertThat(wasOpenedNewTab())
                .as("document wasn't opened in new tab")
                .isEqualTo(true);

        softAssertions.assertAll();
    }
}