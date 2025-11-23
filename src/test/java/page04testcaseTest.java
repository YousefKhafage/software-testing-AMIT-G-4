import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.page04testcase;

import java.time.Duration;

public class page04testcaseTest {
    WebDriver driver;
    page04testcase testCasePage;
    SoftAssert softassert = new SoftAssert();


    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }

    @Test()
    public void testCase7() {
        testCasePage =new page04testcase(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        testCasePage.clickOnTestcaseIcon();
        softassert.assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/test_cases","error");
        softassert.assertAll();
    }
    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}