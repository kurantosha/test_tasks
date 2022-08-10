package blocks;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EconomicCalendarPage;

import java.time.Duration;

import static utils.Utils.scrollToElement;

@Getter
public class MainMenuBlock {

    @Getter
    private static WebDriver driver;
    private final By hiddenMainMenuButton = By.xpath("//span[text()='Menu']");
    private final By researchAndEducationMainMenuButton = By.xpath("//li[contains(@class,'main_nav_research')]");
    private final By researchAndEducationHiddenMainMenuButton = By.xpath("//a[@aria-controls='researchMenu']");
    private final By economicCalendarMainMenuButton = By.xpath("//a[contains(text(),'Economic Calendar')]");
    private final By economicCalendarHiddenMainMenuButton = By.xpath("//div[@id='researchMenu']//a[contains(@href,'economicCalendar')]");

    public MainMenuBlock(WebDriver webDriver) {
        driver = webDriver;
    }

    public MainMenuBlock clickOnResearchAndEducationMainMenuButton() {
        if (getDriver().manage().window().getSize().getWidth() <= 990) {
            getDriver().findElement(hiddenMainMenuButton).click();
            getDriver().findElement(researchAndEducationHiddenMainMenuButton).click();
        } else {
            getDriver().findElement(researchAndEducationMainMenuButton).click();
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                    ExpectedConditions.attributeContains(researchAndEducationMainMenuButton, "class", "selected"));
        }
        return this;
    }

    public EconomicCalendarPage clickOnEconomicCalendarMainMenuButton() {
        if (getDriver().manage().window().getSize().getWidth() <= 990) {
            scrollToElement(getDriver(), economicCalendarHiddenMainMenuButton);
            getDriver().findElement(economicCalendarHiddenMainMenuButton).click();
        } else {
            scrollToElement(getDriver(), economicCalendarMainMenuButton);
            new WebDriverWait(getDriver(), Duration.ofSeconds(10)).until(
                    ExpectedConditions.elementToBeClickable(economicCalendarMainMenuButton)).click();
        }
        return new EconomicCalendarPage();
    }
}