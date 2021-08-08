package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.DriverManager;

import java.util.List;

public class SpotifyLoginPage {
    private WebDriver webDriver;
    private DriverManager driverManager;
    private final By USERNAME_INPUT_FIELD = By.id("login-username");
    private final By PASSWORD_INPUT_FIELD = By.id("login-password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By CLOSE_BUTTON = By.xpath("//*[contains(@class,'Button-sc-1dqy6lx-0 cLnKJb _1202545091238e5aa5b47b15ab6786fe-scss e810fe421a0b204c0de3771cf655e135-scss')]");
    private final By MAIN = By.id("main");

    public SpotifyLoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public void GoToLoginPage(WebElement button){
        button.click();
    }

    public boolean LoginToPage(String username, String password){
        WebElement usernameField = webDriver.findElement(USERNAME_INPUT_FIELD);
        usernameField.sendKeys(username);
        WebElement passwordField = webDriver.findElement(PASSWORD_INPUT_FIELD);
        passwordField.sendKeys(password);
        WebElement loginButton = webDriver.findElement(LOGIN_BUTTON);
        loginButton.click();
        return true;
    }

    public boolean PressCloseKey(){
        WebElement closeButton = webDriver.findElement(CLOSE_BUTTON);
        System.out.println(closeButton.getText());
        try {
            closeButton.click();
        } catch (Exception e) {
            new Actions(webDriver).sendKeys(Keys.PAGE_DOWN).perform();
            closeButton.click();
        }
        WebElement mainElements = webDriver.findElement(MAIN);
        if(mainElements.isDisplayed()) return true;
        else return false;
    }
}
