package pages;

import blocks.MainMenuBlock;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static core.Driver.getDriver;

@Getter
public abstract class BasePage {

    private final MainMenuBlock mainMenuBlock = new MainMenuBlock(getDriver());

    public WebElement find(By locator) {
        return getDriver().findElement(locator);
    }
}