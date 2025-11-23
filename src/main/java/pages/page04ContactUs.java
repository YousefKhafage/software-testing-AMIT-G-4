package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class page04ContactUs extends base {

    public page04ContactUs(WebDriver driver) {
        super(driver);
    }

    private final By contactUsButton = By.xpath("//a[@href='/contact_us']");
    private final By getInTouchHeader = By.xpath("//h2[normalize-space()='Get In Touch']");
    private final By nameField = By.name("name");
    private final By emailField = By.name("email");
    private final By subjectField = By.name("subject");
    private final By messageField = By.id("message");
    private final By uploadFileInput = By.name("upload_file");
    private final By submitButton = By.name("submit");
    private final By successMessage = By.cssSelector(".status.alert.alert-success");
    private final By homeButton = By.xpath("//a[normalize-space()='Home']");
    private final By homeImage = By.xpath("//img[@alt='Website for automation practice']");

    public void clickContactUs() {
        clickOn(contactUsButton);

    }

    public boolean isGetInTouchVisible() {
        return driver.findElement(getInTouchHeader).isDisplayed();
    }

    public void fillForm(String name, String email, String subject, String message) {
        sendData(nameField, name);
        sendData(emailField, email);
        sendData(subjectField, subject);
        sendData(messageField, message);
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public void submitForm() {
        clickOn(submitButton);
    }

    public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public boolean isSuccessMessageVisible() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public void clickHomeButton() {
        clickOn(homeButton);
    }

    public boolean isHomePageVisible() {
        return driver.findElement(homeImage).isDisplayed();
    }
}
