import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.contactUsPage;
import pages.page001Home;
import pages.page03Login;

import java.time.Duration;

public class contactUsTest {
    WebDriver driver;
    page001Home home;
    contactUsPage contact;
    SoftAssert softassert = new SoftAssert();
    Faker fake = new Faker();
    String name = fake.name().fullName();
    String email = fake.internet().safeEmailAddress();


    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testCase6() {
        home = new page001Home(driver);
        contact = new contactUsPage(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnContactUs();
        softassert.assertTrue(driver.findElement(By.cssSelector("div[class=\"contact-form\"] h2[class=\"title text-center\"]")).isDisplayed());
        contact.enterEmailSubjectMessageUpload(name, email, "message", "subject", "C:\\Users\\bassa\\OneDrive\\Desktop");
        contact.clickOnSubmit();
        driver.switchTo().alert().accept();
        softassert.assertTrue(driver.findElement(By.cssSelector("div[class=\"status alert alert-success\"]")).isDisplayed());
        contact.clickOnHome();
        softassert.assertEquals(url, "https://automationexercise.com/");
        softassert.assertAll();
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}
