package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.DriverManager;

public class SpotifyLoginPage {
    private WebDriver webDriver;
    private DriverManager driverManager;

    public SpotifyLoginPage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public void GoToLoginPage(WebElement button){
        button.click();
    }
}
