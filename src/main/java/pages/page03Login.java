package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page03Login extends base{
    public page03Login(WebDriver driver) {
        super(driver);
    }
    private final By Email = By.cssSelector("input[data-qa=\"login-email\"]");
    private final By password = By.cssSelector("input[data-qa=\"login-password\"]");
    private final By loginButton = By.cssSelector("button[data-qa=\"login-button\"]");

    public void enterEmailPassword( String yourEmail,String yourPassword){
        sendData(Email,yourEmail);
        sendData(password,yourPassword);
    }


    public void clickOnLoginButton() {
        clickOn(loginButton);
    }
}
