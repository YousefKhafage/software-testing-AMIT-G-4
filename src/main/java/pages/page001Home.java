package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page001Home extends base {
    public page001Home(WebDriver driver) {
        super(driver);
    }

    private final By sigUPLoginButton = By.cssSelector("ul[class=\"nav navbar-nav\"] i[class=\"fa fa-lock\"]");
    private final By deleteAccount = By.cssSelector("ul[class=\"nav navbar-nav\"]  i[class=\"fa fa-trash-o\"]");
    private final By continueAfterDelete = By.cssSelector("a[data-qa=\"continue-button\"]");
    private final By logoutButton = By.cssSelector("i[class=\"fa fa-lock\"]");
    private final By contactUsButton = By.cssSelector("i[class=\"fa fa-envelope\"]");
    private final By CartIcon=By.linkText("Cart");


    public void clickOnSignupLogin() {
        clickOn(sigUPLoginButton);
    }


    public void deleteAccount() {
        clickOn(deleteAccount);
    }

    public void clickOnContinue() {
        clickOn(continueAfterDelete);
    }


    public void logout() {
        clickOn(logoutButton);
    }
    public void clickOnContactUs(){
        clickOn(contactUsButton);
    }
    public void goToCart(){
        clickOn(CartIcon);
    }
}
