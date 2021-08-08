package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.SpotifyHomePage;
import pages.SpotifyLoginPage;
import util.DriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpotifyTest {
    WebDriver webDriver;
    DriverManager driverManager;
    SpotifyHomePage spotifyHomePage;
    SpotifyLoginPage spotifyLoginPage;

    @BeforeEach
    void init(){
        driverManager = new DriverManager();
        webDriver = driverManager.GetWebDriver();
    }

    @Test
    void GoToHomePageTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        assertTrue(spotifyHomePage.GoToHomePage());
    }

    @Test
    void GoToLoginPageTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
    }



    @AfterEach
    void quitWebDriver(){
        webDriver.quit();
    }



























































































}
