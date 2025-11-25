
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ScrollUPandDown;

public class ScrollTest {
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void ArrowButton() throws InterruptedException {

        driver.get("https://automationexercise.com");
        ScrollUPandDown page = new ScrollUPandDown(driver);

        page.scrollDown();
        Thread.sleep(2000);

        Assert.assertTrue(page.isSubscriptionVisible(), "Subscription section NOT visible!");

        page.clickArrowUp();
        Thread.sleep(2000);
        Assert.assertTrue(page.isTopTextVisible(), "Top text NOT visible after scrolling up!");


    }
    @Test
    public void scrollUpWithoutArrowButton() throws InterruptedException {

        driver.get("https://automationexercise.com");
        ScrollUPandDown page = new ScrollUPandDown(driver);

        page.scrollDown();
        Thread.sleep(2000);

        Assert.assertTrue(page.isSubscriptionVisible());

        page.scrollUp();
        Thread.sleep(2000);
        Assert.assertTrue(page.isTopTextVisible());
    }
    @AfterMethod
    public void QuitBrowser() {
        driver.quit();
    }
}


