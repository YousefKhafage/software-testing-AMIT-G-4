package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class footer extends base {

    public footer(WebDriver driver) {
        super(driver);
    }

    private final By footer = By.xpath("//*[@id=\"footer\"]");
    private final By emailLocator = By.id("susbscribe_email");
    private final By arrowButton = By.id("subscribe");

    public void scrollToFooter() {
        clickOn(footer);
    }
    public void enterSubscribeEmail(String email) {
        sendData(emailLocator, email);
    }

    public void clickONarrowButton() {
        clickOn(arrowButton);
    }

}
