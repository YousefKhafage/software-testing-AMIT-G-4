import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;



public class TestCase21AddReviewToProduct {


    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Helper: click cookie popup if exists
    public void clickIfExists(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
            }
        } catch (Exception e) {
            // ignore if not found
        }
    }

    @Test
    public void addReviewOnProduct() {

        // Step 1 & 2: Launch browser and go to site
        driver.get("https://automationexercise.com");

        // Handle cookie popup if it appears
        clickIfExists(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/button[1]/p"));

        // Step 3: Click Products
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

        // Step 4: Verify ALL PRODUCTS page is visible
        WebElement allProductsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'All Products')]")));
        Assert.assertTrue(allProductsTitle.isDisplayed(), "ALL PRODUCTS page not loaded!");

        // Step 5: Click first 'View Product' button
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'View Product')])[1]"))).click();

        // Step 6: Verify 'Write Your Review' is visible
        WebElement reviewTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(), 'Write Your Review')]")));
        Assert.assertTrue(reviewTitle.isDisplayed(), "'Write Your Review' is NOT visible!");

        // Step 7: Enter name, email, review
        driver.findElement(By.id("name")).sendKeys("Ahmed Khalil Tester");
        driver.findElement(By.id("email")).sendKeys("ahmed@example.com");
        driver.findElement(By.id("review")).sendKeys("Great product! Automated test review.");

        // Step 8: Click Submit button
        driver.findElement(By.id("button-review")).click();

        // Step 9: Verify success message
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(text(),'Thank you for your review.')]")));

        Assert.assertTrue(successMsg.isDisplayed(), "Success message NOT displayed!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
