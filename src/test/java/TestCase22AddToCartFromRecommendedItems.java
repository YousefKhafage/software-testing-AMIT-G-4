import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestCase22AddToCartFromRecommendedItems {


    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    // Helper to click cookie popup if present
    public void clickIfExists(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
            }
        } catch (Exception ignored) {}
    }

    @Test
    public void addRecommendedItemToCart() {

        // Step 1 & 2: Launch browser + navigate
        driver.get("https://automationexercise.com");

        // Handle consent popup if any
        clickIfExists(By.xpath("//html/body/div/div[2]/div[2]/div[2]/div[2]/button[1]/p"));

        // Step 3: Scroll to bottom of the page
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Step 4: Verify 'RECOMMENDED ITEMS' is visible
        WebElement recommendedLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'recommended items') or contains(text(),'Recommended Items')]")
        ));
        Assert.assertTrue(recommendedLabel.isDisplayed(), "Recommended Items section is NOT visible!");

        // Step 5: Click on 'Add To Cart' on the first recommended product
        WebElement firstRecommendedAddToCart = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//div[@id='recommended-item-carousel']//a[contains(text(),'Add to cart')])[1]")
        ));
        firstRecommendedAddToCart.click();

        // Step 6: Click on 'View Cart' button
        WebElement viewCartBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//u[contains(text(),'View Cart')]")
        ));
        viewCartBtn.click();

        // Step 7: Verify product is displayed in cart
        WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody/tr")
        ));
        Assert.assertTrue(cartTable.isDisplayed(), "Product is NOT displayed in the cart!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
