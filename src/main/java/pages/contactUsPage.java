package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class contactUsPage extends base {
    public contactUsPage(WebDriver driver) {
        super(driver);
    }
Faker fake=new Faker();
    private final By getInTouch = By.cssSelector("div[class=\"contact-form\"] h2[class=\"title text-center\"]");
    private final By nameLocator = By.cssSelector("input[data-qa=\"name\"]");
    private final By emailLocator = By.cssSelector("input[data-qa=\"email\"]");
    private final By subjectLocator = By.cssSelector("input[data-qa=\"subject\"]");
    private final By messageLocator = By.cssSelector("textarea[data-qa=\"message\"]");
    private final By uploadFileLocator = By.xpath("//input[@name=\"upload_file\"]");
    private final By submitButtonLocator = By.xpath("//input[@type=\"submit\"]");
    private final By homeButton = By.xpath("//i[@class=\"fa fa-angle-double-left\"]");

public void enterEmailSubjectMessageUpload(String name,String email,
                                           String subject,String message,String upload){
    sendData(nameLocator,name);
    sendData(emailLocator,email);
    sendData(subjectLocator,subject);
    sendData(messageLocator,message);
    sendData(uploadFileLocator,upload);

}public void clickOnSubmit(){
    clickOn(submitButtonLocator);
    }
    public void clickOnHome(){
    clickOn(homeButton);
    }
}
