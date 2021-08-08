package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.DriverManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SpotifyShowListPage {

    private WebDriver webDriver;
    private DriverManager driverManager;
    private final By SEARCH_INPUT_FIELD = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div/div/div/div[2]/div[1]/div/div[3]/button");
    private final By SEARCH_INPUT_FIELD_EXISTING_LIST = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/section/div/div/input");
    private final By ADD_EXISTING_BUTTON = By.xpath("//*[@id=\"main\"]/div/div[2]/div[3]/main/div[2]/div[2]/div/div/div[2]/section/div[2]/div[3]/div[2]/div/div/div[2]/div[1]/div/div[3]/button");


    public SpotifyShowListPage(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public void AddAMusicToNewList(String musicName) {
        WebElement searchField = webDriver.findElement(SEARCH_INPUT_FIELD);
        searchField.sendKeys(musicName);
        WebElement addButton = webDriver.findElement(ADD_BUTTON);
        addButton.click();
        searchField.clear();
    }

    public List<String> CreateAnListFromFile(String file) {
        List<String> showList = new ArrayList<>();
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                showList.add(data);
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Nem található a fájl.");
            e.printStackTrace();
        }
        return showList;
    }

    public void AddAMusicAnExistingList(String musicName){
            driverManager.GetWait(webDriver,5);
            WebElement searchField = webDriver.findElement(SEARCH_INPUT_FIELD_EXISTING_LIST);
            searchField.clear();
            searchField.sendKeys(musicName);
            driverManager.GetWait(webDriver,5);
            WebElement addButton = driverManager.GetWebDriverWait(webDriver,15,ADD_EXISTING_BUTTON);
            addButton.click();
            searchField.clear();
    }
}