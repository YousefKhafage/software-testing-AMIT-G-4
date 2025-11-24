package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage{
    protected WebDriver driver;
    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    public void clickOn(By by){
        driver.findElement(by).click();
    }
    public void sendData(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
}