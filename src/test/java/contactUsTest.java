import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import pages.page04ContactUs;

public class contactUsTest {

    WebDriver driver;
    page04ContactUs contact;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://automationexercise.com");
        contact = new page04ContactUs(driver);
    }

    @Test
    public void ContactUsFormTest6() {

        contact.clickContactUs();

        contact.fillForm("Yousef", "test@test.com", "Test", "test the contact us form automatic");

        String filePath = "C:\\Users\\Excellent Store\\OneDrive\\Pictures\\Screenshots\\test.png";
        contact.uploadFile(filePath);

        contact.submitForm();
        contact.acceptAlert();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(contact.isSuccessMessageVisible());

        contact.clickHomeButton();
        Assert.assertTrue(contact.isHomePageVisible());
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}
