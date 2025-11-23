import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.page001Home;
import pages.page02Signup;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class signUpTest {
    Faker fake = new Faker();
    String email = fake.internet().safeEmailAddress();
    String password = fake.internet().password();
    WebDriver driver;
    page001Home home;
    page02Signup signUp;
    SoftAssert softassert = new SoftAssert();

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");
    }

    @Test
    public void testcase1Register() {
        home = new page001Home(driver);
        signUp = new page02Signup(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnSignupLogin();
        String header = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2")).getText();
        softassert.assertEquals(header, "New User Signup!");
        signUp.enterName(fake.name().name());
        signUp.enterEmail(email);
        signUp.clickOnSignupButton();
        WebElement title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b"));
        softassert.assertTrue(title.isDisplayed());
        signUp.selectGender();
        signUp.enterPassword(password);
        signUp.selectDayMonthYear("1", "3", "1999");
        signUp.clickOnCheckboxNewsletter();
        signUp.clickOnCheckBoxSpecialOffer();
        signUp.FillDetailsFirstnameLastnameCompanyAddress2CountryStateCityZipcodeMobileNumber("reem",
                "mohamed", "company", "adresss1", "address2",
                "India", "dddd", "city", "12555", "010000000");
        signUp.ClickOnCreateAccount();
        WebElement title2Created = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
        softassert.assertTrue(title2Created.isDisplayed());
        signUp.ClickOnContinue();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/i")).isDisplayed());
        home.deleteAccount();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")).isDisplayed());
        home.clickOnContinue();
        softassert.assertAll();

    }

    @Test
    public void testcase5invalidRegister() {
        home = new page001Home(driver);
        signUp = new page02Signup(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnSignupLogin();
        String header = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/h2")).getText();
        softassert.assertEquals(header, "New User Signup!");
        signUp.enterName("reem shahat");
        signUp.enterEmail("reemegmail@project.com");
        signUp.clickOnSignupButton();
        WebElement errorMessage = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[3]/div/form/p"));
        softassert.assertTrue(errorMessage.isDisplayed());
        softassert.assertAll();
    }

    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}
