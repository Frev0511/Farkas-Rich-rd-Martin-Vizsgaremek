package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.SpotifyLoginPage;
import pages.SpotifyMainPage;
import pages.SpotifyRegisterPage;
import pages.SpotifyShowListPage;
import util.DriverManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SpotifyTest {
    WebDriver webDriver;
    DriverManager driverManager;
    SpotifyRegisterPage spotifyRegisterPage;
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
    void ReadPrivacyPolicyTest(){
        spotifyRegisterPage = new SpotifyRegisterPage(webDriver);
        spotifyRegisterPage.GoToRegisterPage();
        assertTrue(spotifyRegisterPage.ReadPrivacyPolicy());
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
    void AddAMusicToNewListTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.AddShowList();
        spotifyShowListPage = new SpotifyShowListPage(webDriver);
        assertTrue(spotifyShowListPage.AddAMusicToNewList("Azariah El barto"));
    }

    @Test
    void CreateAnListFromFileTest(){
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
        assertTrue(spotifyShowListPage.SelectAnExistingList("Szerkesztésre lista",musicArr));
    }

    @Test
    void DeleteListsTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        spotifyMainPage.DeleteShowLists();
        assertEquals(2,spotifyMainPage.GetShowListSize());
    }

    @Test
    void TopListToListTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertTrue(spotifyMainPage.TopListToList() > 5);

    }

    @Test
    void WriteTheShowListToFileTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertEquals(spotifyMainPage.GetShowListSize(),spotifyMainPage.WriteTheShowListToFile());
    }

    @Test
    void SpotifyLogoutTest(){
        spotifyLoginPage = new SpotifyLoginPage(webDriver);
        spotifyLoginPage.LoginToPage(username,password);
        spotifyMainPage = new SpotifyMainPage(webDriver);
        assertTrue(spotifyMainPage.SpotifyLogout());
    }

    @AfterEach
    void quitWebDriver(){
        webDriver.quit();
    }
}
