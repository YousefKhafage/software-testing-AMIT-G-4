import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.footer;
import pages.page001Home;

import java.time.Duration;

public class cartTest {
    Faker fake=new Faker();
    WebDriver driver;
    page001Home home;
    CartPage cart;
    SoftAssert softassert=new SoftAssert();
    String email = fake.internet().safeEmailAddress();

    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }
    @Test
    public void testCase11() {
        home = new page001Home(driver);
        cart = new CartPage(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        home.goToCart();
        cart.scrollToFooter();
        String title = driver.findElement(By.cssSelector("div[class=\"col-sm-3 col-sm-offset-1\"] h2")).getText();
        softassert.assertEquals(title, "SUBSCRIPTION");
        cart.enterSubscribeEmail(email);
        cart.clickONarrowButton();
        String message = driver.findElement(By.xpath("//div[@class=\"container\"]//div[@class=\"alert-success alert\"]")).getText();
        softassert.assertEquals(message, "You have been successfully subscribed!");
        softassert.assertAll();
    }
    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}
