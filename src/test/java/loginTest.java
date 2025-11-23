import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.page001Home;
import pages.page02Signup;
import pages.page03Login;

import java.time.Duration;

import static org.testng.TestRunner.PriorityWeight.dependsOnMethods;


public class loginTest {
    WebDriver driver;
    page001Home home;
    page03Login login;
    SoftAssert softassert = new SoftAssert();


    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");
    }

    @Test()
    public void testcase2loginTest() {
        home = new page001Home(driver);
        login = new page03Login(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnSignupLogin();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2")).isDisplayed());
        login.enterEmailPassword("reemegmail@project.com", "reemshahat");
        login.clickOnLoginButton();
        softassert.assertTrue(driver.findElement(By.xpath("//i[@class=\"fa fa-user\"]")).isDisplayed());
//        home.deleteAccount();
//        home.clickOnContinue();
       softassert.assertAll();


    }
    @Test
    public void testcase3InvalidLogin(){
        home = new page001Home(driver);
        login = new page03Login(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnSignupLogin();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2")).isDisplayed());
        login.enterEmailPassword("abcd518@gmail.com", "ABCD@#1234");
        login.clickOnLoginButton();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/p")).isDisplayed());
        softassert.assertAll();
    }
    @Test
    public void tesrcase4logout(){
        home = new page001Home(driver);
        login = new page03Login(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.clickOnSignupLogin();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2")).isDisplayed());
        login.enterEmailPassword("reemegmail@project.com", "reemshahat");
        login.clickOnLoginButton();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a/i")).isDisplayed());
        home.logout();
        softassert.assertAll();
    }

    @AfterMethod
    public void quitBrowser() {
          driver.quit();

    }
}
