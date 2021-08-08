package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SpotifyHomePage;
import pages.SpotifyLoginPage;
import pages.SpotifyMainPage;
import pages.SpotifyShowListPage;
import util.DriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpotifyTest {
    WebDriver webDriver;
    DriverManager driverManager;
    SpotifyHomePage spotifyHomePage;
    SpotifyLoginPage spotifyLoginPage;
    SpotifyMainPage spotifyMainPage;
    SpotifyShowListPage spotifyShowListPage;
    final String username = "farkasrichardev0511@gmail.com";
    final String password = "Selenium0511";
    final String file = "Azariah.txt";
    final String[] musicArr = {
            "Bon Jovi It's My Life",
            "Bon jovi Born to Follow",

    };

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

    @Test
    void LoginToPageTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        assertTrue(spotifyLoginPage.LoginToPage(username,password));
    }

    @Test
    void PressCloseKeyTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        spotifyLoginPage.LoginToPage(username,password);
        assertTrue(spotifyLoginPage.PressCloseKey());
    }

    @Test
    void AddShowListTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        spotifyLoginPage.LoginToPage(username,password);
        spotifyLoginPage.PressCloseKey();
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertTrue(spotifyMainPage.AddShowList());

    }

    @Test
    void AddAMusicTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        spotifyLoginPage.LoginToPage(username,password);
        spotifyLoginPage.PressCloseKey();
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.AddShowList();
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        spotifyShowListPage.AddAMusicToNewList("Azariah El barto");
    }

    @Test
    void AddAShowListTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        spotifyLoginPage.LoginToPage(username,password);
        spotifyLoginPage.PressCloseKey();
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.AddShowList();
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        List<String> showList = spotifyShowListPage.CreateAnListFromFile(file);
        spotifyShowListPage.AddAMusicToNewList(showList.get(0));
        driverManager.GetWait(webDriver,5);
        for(int i = 1; i < showList.size(); i ++){
            spotifyShowListPage.AddAMusicAnExistingList(showList.get(i));
        }
    }

    @Test
    void SelectAnExistingListTest(){
        spotifyHomePage = new SpotifyHomePage(webDriver);
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.GoToLoginPage(spotifyHomePage.FindLoginButton());
        spotifyLoginPage.LoginToPage(username,password);
        spotifyLoginPage.PressCloseKey();
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        spotifyShowListPage.SelectAnExistingList("Szerkesztésre lista",musicArr);
    }

    @AfterEach
    void quitWebDriver(){
        webDriver.quit();
    }



























































































}
