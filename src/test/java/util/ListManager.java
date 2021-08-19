package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListManager {
    private WebDriver webDriver;
    private DriverManager driverManager;

    public ListManager(WebDriver webDriver){
        this.webDriver = webDriver;
        driverManager = new DriverManager();
    }

    public List<WebElement> ElementsToList(By LIST, String path){
        WebElement list = driverManager.GetWebDriverWait(webDriver,5,LIST);
        List<WebElement> result = list.findElements(By.xpath(path));
        return result;
    }

    public WebElement FindElementFromList(List<WebElement> elements, String text){
        WebElement result = null;
        for(WebElement element: elements){
            if(element.getText().contains(text)){
                result = element;
            }
        }
        return result;
    }
}
