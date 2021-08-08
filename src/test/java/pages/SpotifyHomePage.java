package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import util.DriverManager;
import util.ListManager;

import java.util.List;

public class SpotifyHomePage {
    private WebDriver webDriver;
    private DriverManager driverManager;
    private String URL = "https://www.spotify.com/";
    private final By MAIN_PAGE_ID = By.id("__next");
    private final By MAIN_BUTTON_LIST = By.xpath("/html/body/div[1]/div[1]/header/div/nav/ul");
    private final By MAIN_COOKIES_BUTTON = By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]");

    public SpotifyHomePage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public boolean GoToHomePage(){
        webDriver.get(URL);
        WebElement cookiesButton = driverManager.GetWebDriverWait(webDriver,15,MAIN_COOKIES_BUTTON);
        try {
            cookiesButton.click();
        } catch (Exception e) {
            new Actions(webDriver).sendKeys(Keys.PAGE_DOWN).perform();
            cookiesButton.click();
        }
        WebElement mainPage = webDriver.findElement(MAIN_PAGE_ID);
        if(mainPage.isDisplayed()) return true;
        return false;
    }

    public WebElement FindLoginButton(){
        GoToHomePage();
        ListManager listManager = new ListManager(webDriver);
        List<WebElement> mainButtons = listManager.ElementsToList(MAIN_BUTTON_LIST);
        return listManager.FindElementFromList(mainButtons,"Bejelentkezés");
    }

}
