package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utils.Utils;

import static core.Driver.getDriver;

public class RiskWarningPage extends BasePage {

    private final By hereFromRiskWarningButton = By.xpath("//a[contains(@href,'Risk-Disclosures')]");

    @Step("click On Here From Risk Warning")
    public void clickOnHereFromRiskWarning() {
        Utils.scrollToElement(getDriver(), hereFromRiskWarningButton);
        find(hereFromRiskWarningButton).click();
    }
}
