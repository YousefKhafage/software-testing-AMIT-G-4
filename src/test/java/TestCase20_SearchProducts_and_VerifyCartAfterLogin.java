import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.List;



public class TestCase20_SearchProducts_and_VerifyCartAfterLogin {


    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @Test
    public void searchProductsAndVerifyCartAfterLogin() {

        // Step 1 & 2: Launch browser & navigate
        driver.get("https://automationexercise.com");


        // if a consent to cookies message appears:
        clickIfExists(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/button[1]/p"));


        // Step 3: Click on Products button
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/products']"))).click();

        // Step 4: Verify that user is on ALL PRODUCTS page
        WebElement allProductsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'All Products')]")));
        Assert.assertTrue(allProductsTitle.isDisplayed(), "ALL PRODUCTS title not visible!");

        // Step 5: Search for a product
        WebElement searchBox = driver.findElement(By.id("search_product"));
        searchBox.sendKeys("dress");
        driver.findElement(By.id("submit_search")).click();

        // Step 6: Verify SEARCHED PRODUCTS is visible
        WebElement searchedProductsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h2[contains(text(),'Searched Products')]")));
        Assert.assertTrue(searchedProductsTitle.isDisplayed(), "'SEARCHED PRODUCTS' title not visible!");

        // Step 7: Verify related products appear
        List<WebElement> searchedItems = driver.findElements(By.cssSelector(".product-image-wrapper"));
        Assert.assertTrue(searchedItems.size() > 0, "No products found for search!");

        // Step 8: Add all searched products to cart
        for (WebElement product : searchedItems) {
            actions.moveToElement(product).perform();
            wait.until(ExpectedConditions.elementToBeClickable(
                    product.findElement(By.xpath(".//a[contains(text(),'Add to cart')]"))
            )).click();

            // Click continue shopping to close popup
            wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(),'Continue Shopping')]")
            )).click();
        }

        // Step 9: Go to Cart and verify products are visible
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        List<WebElement> cartItemsBeforeLogin = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody/tr"))
        );

        Assert.assertTrue(cartItemsBeforeLogin.size() > 0, "No items in cart before login!");

        int numberOfItems = cartItemsBeforeLogin.size();

        // Step 10: Login
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        // Enter login credentials
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("ahmed.ismail.khalil.1994@gmail.com");
        driver.findElement(By.xpath("//input[@data-qa='login-password']")).sendKeys("test123");
        driver.findElement(By.xpath("//button[@data-qa='login-button']")).click();

        // Step 11: Navigate to Cart again
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // Step 12: Verify cart still contains same products
        List<WebElement> cartItemsAfterLogin = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tbody/tr"))
        );

        Assert.assertEquals(cartItemsAfterLogin.size(), numberOfItems,
                "Cart items count changed after login!");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }



    public void clickIfExists(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
                System.out.println("Cookie consent popup clicked.");
            }
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            System.out.println("Cookie consent popup NOT found. Continuing...");
        }
    }

}
