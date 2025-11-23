package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page04testcase extends base {

    public page04testcase(WebDriver driver) {
        super(driver);
    }
    protected final By testcaseButton=By.linkText("Test Cases");

    public void clickOnTestcaseIcon(){
        clickOn(testcaseButton);
    }
}
