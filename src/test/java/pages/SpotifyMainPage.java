package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.DriverManager;
import util.ListManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpotifyMainPage {

    private WebDriver webDriver;
    private DriverManager driverManager;
    private ListManager listManager;
    private final By SHOW_LIST_BUTTON = By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes')]");
    private final By SHOW_LIST = By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes GlueDropTarget--playlists GlueDropTarget--folders')]");
    private final By SHOW_LIST_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/nav/div[1]/div[2]/div/div[4]/div[4]/div/div/ul/div/div[2]");
    private final By SEARCH_INPUT_FIELD = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/div[3]/button");
    private final By SEARCH_INPUT_FIELD_EXISTING_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_EXISTING_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div[2]/div/div/div[2]/div[1]/div/div[3]/button");
    private final By ACCOUNT_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/header/button[2]");
    private final By LOGOUT_BUTTON = By.xpath("//*[@id=\"context-menu\"]/div/ul/li[4]/button");
    private final By MAIN = By.id("main");
    private final By OPTION_LIST = By.xpath("/html/body/div[15]/div/ul");
    private final By TOP_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div/div/section[1]/div[2]");
    private final By ALL_BUTTON = By.xpath("//*[contains(@class,'tJAIYSfsDs3HsXDAPenF')]");
    private final By TOP_LIST_CONTINUED = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/div/section/div/div/section/div[2]");
    private final By DELETE_BUTTON = By.xpath("//*[@id=\"context-menu\"]/ul/li[5]/button");
    private final By DELETE_BUTTON_2 = By.xpath("//*[contains(@class,'PzcmS_Z8j0D6n3ZEmv20 nrWs9ympjWITXKIXd_7y')]");


    public SpotifyMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public int GetShowListSize() {

        WebElement showListList = webDriver.findElement(SHOW_LIST_LIST);
        List<WebElement> list = showListList.findElements(SHOW_LIST);
        return list.size();
    }

    public List<WebElement> GetShowList() {

        WebElement showListList = webDriver.findElement(SHOW_LIST);
        List<WebElement> list = showListList.findElements(SHOW_LIST);
        return list;
    }

    public boolean AddShowList() {
        int beforeList = GetShowListSize();
        WebElement addShowListButton = webDriver.findElement(SHOW_LIST_BUTTON);
        driverManager.GetWait(webDriver,5);
        addShowListButton.click();
        driverManager.GetWait(webDriver,5);
        int afterList = GetShowListSize();
        if (beforeList < afterList) return true;
        else return false;
    }

    public boolean DeleteShowLists(){
        listManager  = new ListManager(webDriver);
        int beforeList = GetShowListSize();
        List<WebElement> existingLists = GetShowList();
        for(int i = 0; i < existingLists.size() -1; i ++) {
            if (existingLists.size() > 1) {
                Actions actions = new Actions(webDriver);
                actions.contextClick(existingLists.get(i)).perform();
                actions.contextClick(existingLists.get(i)).perform();
                WebElement deleteButton = webDriver.findElement(DELETE_BUTTON);
                deleteButton.click();
                WebElement deleteButton2;
                deleteButton2 = webDriver.findElement(DELETE_BUTTON_2);
                deleteButton2.click();
            }
        }
        int afterList = GetShowListSize();
        if(afterList <= beforeList) return true;
        else return false;
    }

    public boolean TopListToList(){
        WebElement topListElement = webDriver.findElement(TOP_LIST);
        List<WebElement> topList = topListElement.findElements(By.xpath("./div"));
        System.out.println(topList.size());
        WebElement allButton = webDriver.findElement(ALL_BUTTON);
        allButton.click();
        WebElement topListContinued = webDriver.findElement(TOP_LIST_CONTINUED);
        List<WebElement> topListContinuedList = topListContinued.findElements(By.xpath("./div"));
        for(int i = 0; i < topListContinuedList.size(); i ++){
            topList.add(topListContinuedList.get(i));
        }
        System.out.println(topList.size());
        return true;
    }

    public boolean WriteTheShowListToFile(){
        try {
            FileWriter myWriter = new FileWriter("Listanevek.txt");
            myWriter.write("a");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return true;
    }

    public boolean SpotifyLogout(){
        WebElement accountButton = webDriver.findElement(ACCOUNT_BUTTON);
        accountButton.click();
        WebElement logoutButton = webDriver.findElement(LOGOUT_BUTTON);
        logoutButton.click();
        WebElement main = webDriver.findElement(MAIN);
        if(main.isDisplayed()) return true;
        else return false;
    }
}