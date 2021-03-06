package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.DriverManager;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class SpotifyMainPage {

    private WebDriver webDriver;
    private DriverManager driverManager;
    private final By SHOW_LIST_BUTTON = By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes')]");
    private final By SHOW_LIST = By.xpath("//*[contains(@class,'GlueDropTarget GlueDropTarget--albums GlueDropTarget--tracks GlueDropTarget--episodes GlueDropTarget--playlists GlueDropTarget--folders')]");
    private final By SHOW_LIST_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/nav/div[1]/div[2]/div/div[4]/div[4]/div/div/ul/div/div[2]");
    private final By ACCOUNT_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/header/button[2]");
    private final By LOGOUT_BUTTON = By.xpath("//*[@id=\"context-menu\"]/div/ul/li[4]/button");
    private final By MAIN = By.id("main");
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

    public void DeleteShowLists() {
        List<WebElement> existingLists = GetShowList();
        if (existingLists.size() != 0 && existingLists.size() != 1) {
            for (int i = 0; i < existingLists.size() - 1; i++) {
                driverManager.GetWait(webDriver, 5);
                Actions actions = new Actions(webDriver);
                actions.contextClick(existingLists.get(i)).perform();
                WebElement deleteButton = webDriver.findElement(DELETE_BUTTON);
                deleteButton.click();
                WebElement deleteButton2;
                deleteButton2 = webDriver.findElement(DELETE_BUTTON_2);
                deleteButton2.click();
            }
        }
        else
        {
            AddShowList();
            DeleteShowLists();
        }
    }

    public int TopListToList(){
        WebElement topListElement = webDriver.findElement(TOP_LIST);
        List<WebElement> topList = topListElement.findElements(By.xpath("./div"));
        WebElement allButton = webDriver.findElement(ALL_BUTTON);
        allButton.click();
        WebElement topListContinued = webDriver.findElement(TOP_LIST_CONTINUED);
        List<WebElement> topListContinuedList = topListContinued.findElements(By.xpath("./div"));
        for(int i = 0; i < topListContinuedList.size(); i ++){
            topList.add(topListContinuedList.get(i));
        }
        return topList.size();
    }

    public int WriteTheShowListToFile(){
        List<WebElement> showList = GetShowList();
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("Listanevek.txt"));
            writer.print("");
            writer.close();
            for (int i = 0; i < showList.size(); i ++) {
                FileWriter myWriter;
                myWriter = new FileWriter("Listanevek.txt",true);
                myWriter.write(showList.get(i).getText() + "\n");
                myWriter.close();
            }
            BufferedReader reader = new BufferedReader(new FileReader("Listanevek.txt"));
            int lines = 0;
            while (reader.readLine() != null) lines++;
            reader.close();
            return lines;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return 0;
        }
    }

    public boolean SpotifyLogout(){
        WebElement accountButton = webDriver.findElement(ACCOUNT_BUTTON);
        accountButton.click();
        try{
            WebElement logoutButton = webDriver.findElement(LOGOUT_BUTTON);
            logoutButton.click();
            return true;
        }catch (Exception e){
            return false;
        }


    }
}