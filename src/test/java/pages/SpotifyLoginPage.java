package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.DriverManager;

public class SpotifyLoginPage {
    private WebDriver webDriver;
    private DriverManager driverManager;
    private final String URL = "https://accounts.spotify.com/hu/login/";
    private final By USERNAME_INPUT_FIELD = By.id("login-username");
    private final By PASSWORD_INPUT_FIELD = By.id("login-password");
    private final By LOGIN_BUTTON = By.id("login-button");
    private final By WEB_VERSION_BUTTON = By.xpath("//*[@id=\"app\"]/body/div/div[2]/div/div/div[4]/div/a");
    private final By MAIN = By.id("main");

    public SpotifyLoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public boolean LoginToPage(String username, String password){
        webDriver.get(URL);
        WebElement usernameField = webDriver.findElement(USERNAME_INPUT_FIELD);
        usernameField.sendKeys(username);
        WebElement passwordField = webDriver.findElement(PASSWORD_INPUT_FIELD);
        passwordField.sendKeys(password);
        WebElement loginButton = webDriver.findElement(LOGIN_BUTTON);
        loginButton.click();
        WebElement webVersionButton = webDriver.findElement(WEB_VERSION_BUTTON);
        webVersionButton.click();
        try {
            webDriver.findElement(By.id("onetrust-accept-btn-handler")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement rootMenu = webDriver.findElement(MAIN);
        if(rootMenu.isDisplayed()) return true;
        else return false;
    }
}
