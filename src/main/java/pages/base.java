package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class base {
    protected WebDriver driver;
    public base(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    public base() {
    }

    public void clickOn(By by){
        driver.findElement(by).click();
    }
    public void sendData(By by,String text){
        driver.findElement(by).sendKeys(text);
    }
}
