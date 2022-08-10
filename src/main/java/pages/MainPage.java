package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static core.Driver.getDriver;
import static utils.Utils.scrollToElement;

public class MainPage extends BasePage {

    private final By cookiePolicyAcceptButton = By.xpath("//button[text()='ACCEPT ALL']");
    private final By openDemoAccountButton = By.xpath("(//div[contains(text(), 'Open a Demo Account')])[1]");
    private final By riskCloseButton = By.id("js-riskCloseButton");

    @Step("open Main Page")
    public MainPage openMainPage() {
        if (find(cookiePolicyAcceptButton).isDisplayed()) {
            find(cookiePolicyAcceptButton).click();
        }
        return this;
    }

    public MainPage checkThatCookiesPopupClosed() {
        scrollToElement(getDriver(), openDemoAccountButton);
        find(openDemoAccountButton).isEnabled();
        return this;
    }

    @Step("click On Risk Close Button")
    public MainPage clickOnRiskCloseButton() {
        getDriver().switchTo().parentFrame();
        if (find(riskCloseButton).isDisplayed()) {
            find(riskCloseButton).click();
        }
        return this;
    }
}