package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.DriverManager;

public class SpotifyRegisterPage {
    private final String URL = "https://www.spotify.com/hu/signup/";
    private WebDriver webDriver;
    private DriverManager driverManager;
    private final By PRIVACY_POLICY_URL = By.xpath("//*[@id=\"__next\"]/main/div[2]/div/form/div[10]/p[1]/span/a");
    private final By PRIVACY_POLICY_CSS = By.cssSelector("#content-main > div > h1");
    private final By ACCEPT_COOKIES = By.id("onetrust-accept-btn-handler");

    public SpotifyRegisterPage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public void GoToRegisterPage() {
        webDriver.get(URL);
        driverManager.GetWait(webDriver, 5);
        try {
            webDriver.findElement(ACCEPT_COOKIES).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        driverManager.GetWait(webDriver,5);
    }

    public boolean ReadPrivacyPolicy(){
        WebElement privacyPolicy = webDriver.findElement(PRIVACY_POLICY_URL);
        try {
            privacyPolicy.click();
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
