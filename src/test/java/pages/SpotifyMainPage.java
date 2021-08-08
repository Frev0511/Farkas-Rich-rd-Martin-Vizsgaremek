package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.DriverManager;
import util.ListManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpotifyMainPage {

    private WebDriver webDriver;
    private DriverManager driverManager;
    private ListManager listManager;
    private final By SHOW_LIST_BUTTON = By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes')]");
    private final By SHOW_LIST_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/nav/div[1]/div[2]/div/div[4]/div[4]/div/div/ul/div/div[2]");
    private final By SEARCH_INPUT_FIELD = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/div[3]/button");
    private final By SEARCH_INPUT_FIELD_EXISTING_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_EXISTING_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div[2]/div/div/div[2]/div[1]/div/div[3]/button");

    public SpotifyMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public int GetShowListSize() {
        WebElement showListList = webDriver.findElement(SHOW_LIST_LIST);
        List<WebElement> list = showListList.findElements(By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes GlueDropTarget--playlists GlueDropTarget--folders')]"));
        return list.size();
    }

    public boolean AddShowList() {
        int beforeList = GetShowListSize();
        System.out.println(beforeList);
        WebElement addShowListButton = webDriver.findElement(SHOW_LIST_BUTTON);
        addShowListButton.click();
        driverManager.GetWait(webDriver,5);
        int afterList = GetShowListSize();
        System.out.println(afterList);
        if (beforeList < afterList) return true;
        else return false;
    }
}