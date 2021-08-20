package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SpotifyLoginPage;
import pages.SpotifyMainPage;
import pages.SpotifyShowListPage;
import util.DriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpotifyTest {
    WebDriver webDriver;
    DriverManager driverManager;
    SpotifyLoginPage spotifyLoginPage;
    SpotifyMainPage spotifyMainPage;
    SpotifyShowListPage spotifyShowListPage;
    final String username = "farkasrichardev0511@gmail.com";
    final String password = "Selenium0511";
    final String file = "Azariah.txt";
    final String[] musicArr = {
            "Bon Jovi It's My Life",
            "Bon jovi Born to Follow"
    };

    @BeforeEach
    void init(){
        driverManager = new DriverManager();
        webDriver = driverManager.GetWebDriver();

    }

    @Test
    void LoginToPageTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        assertTrue(spotifyLoginPage.LoginToPage(username,password));
    }


    @Test
    void AddShowListTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertTrue(spotifyMainPage.AddShowList());

    }

    @Test
    void AddAMusicTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.AddShowList();
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        spotifyShowListPage.AddAMusicToNewList("Azariah El barto");
    }

    @Test
    void AddAShowListTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
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
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        spotifyShowListPage.SelectAnExistingList("Szerkesztésre lista",musicArr);
    }


    @Test
    void SpotifyLogoutTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.SpotifyLogout();
    }

    @Test
    void DeleteListsTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertTrue(spotifyMainPage.DeleteShowLists());
    }

    @AfterEach
    void quitWebDriver(){
        webDriver.quit();
    }



























































































}
