import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;


public class TestCase23VerifyAddressDetailsInCheckoutPage {



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

    public void clickIfExists(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                element.click();
            }
        } catch (Exception ignored) {}
    }

    @Test
    public void verifyAddressInCheckout() throws InterruptedException {

        // 1 & 2: Launch browser & navigate
        driver.get("https://automationexercise.com");
        clickIfExists(By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/button[1]/p"));

        // 3: Verify homepage is visible
        WebElement homeCarousel = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("slider-carousel")));
        Assert.assertTrue(homeCarousel.isDisplayed(), "Home page is NOT visible!");

        // 4: Click Signup / Login
        driver.findElement(By.xpath("//a[contains(text(),'Signup / Login')]")).click();

        // 5: Fill signup form (creating a unique user)
        String randomEmail = "test" + UUID.randomUUID() + "@mail.com";
        String username = "AhmedAuto";

        driver.findElement(By.xpath("//input[@data-qa='signup-name']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(randomEmail);
        driver.findElement(By.xpath("//button[@data-qa='signup-button']")).click();

        // Now the big registration form appears
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@id='id_gender1']"))).click();

        driver.findElement(By.id("password")).sendKeys("123456");

        driver.findElement(By.id("first_name")).sendKeys("Ahmed");
        driver.findElement(By.id("last_name")).sendKeys("Khalil");
        driver.findElement(By.id("company")).sendKeys("Testing Co");
        driver.findElement(By.id("address1")).sendKeys("Test Street 123");
        driver.findElement(By.id("address2")).sendKeys("Building B");

        // SAVE ADDRESS INPUTS TO VERIFY LATER
        String addr1 = "Test Street 123";
        String addr2 = "Building B";
        String fullName = "Ahmed Khalil";
        String country = "Canada";
        String state = "Ontario";
        String city = "Toronto";
        String zipcode = "A1B2C3";

        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("zipcode")).sendKeys(zipcode);
        driver.findElement(By.id("mobile_number")).sendKeys("0123456789");

        driver.findElement(By.xpath("//button[@data-qa='create-account']")).click();

        // 6: Verify account created
        WebElement accountCreated = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//b[contains(text(),'Account Created!') or contains(text(),'ACCOUNT CREATED!')]")));
        Assert.assertTrue(accountCreated.isDisplayed(), "Account was NOT created!");

        driver.findElement(By.xpath("//a[@data-qa='continue-button']")).click();

        // Sometimes an ad popup appears, try closing if it exists
        clickIfExists(By.xpath("//a[@id='dismiss-button']"));

        // 7: Verify logged in as username
        WebElement loggedInAs = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//a[contains(text(),'Logged in as')]")));
        Assert.assertTrue(loggedInAs.getText().contains(username),
                "User is NOT logged in as expected!");

        // 8: Add product to cart
        js.executeScript("window.scrollBy(0,600);");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[contains(text(),'Add to cart')])[1]"))).click();

        // Continue shopping button
        clickIfExists(By.xpath("//button[contains(text(),'Continue Shopping')]"));

        // 9: Click Cart
        driver.findElement(By.xpath("//a[@href='/view_cart']")).click();

        // 10: Verify cart page is displayed
        WebElement cartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody")));
        Assert.assertTrue(cartTable.isDisplayed(), "Cart page NOT displayed!");

        // 11: Proceed to checkout
        driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]")).click();

        // 12 & 13: Verify delivery and billing addresses
        WebElement deliveryAddressBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("address_delivery")
        ));

        WebElement billingAddressBox = driver.findElement(By.id("address_invoice"));

        String deliveryText = deliveryAddressBox.getText();
        String billingText  = billingAddressBox.getText();

        // Assertions
        Assert.assertTrue(deliveryText.contains(fullName));
        Assert.assertTrue(deliveryText.contains(addr1));
        Assert.assertTrue(deliveryText.contains(addr2));
        Assert.assertTrue(deliveryText.contains(city));
        Assert.assertTrue(deliveryText.contains(state));
        Assert.assertTrue(deliveryText.contains(zipcode));
        Assert.assertTrue(deliveryText.contains(country));

        Assert.assertTrue(billingText.contains(fullName));
        Assert.assertTrue(billingText.contains(addr1));
        Assert.assertTrue(billingText.contains(addr2));
        Assert.assertTrue(billingText.contains(city));
        Assert.assertTrue(billingText.contains(state));
        Assert.assertTrue(billingText.contains(zipcode));
        Assert.assertTrue(billingText.contains(country));

        // 14: Delete account
        driver.findElement(By.xpath("//a[contains(text(),'Delete Account')]")).click();

        // 15: Verify account deleted
        WebElement accountDeleted = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[@id=\"form\"]/div/div/div/h2/b")));
        Assert.assertTrue(accountDeleted.isDisplayed(), "Account NOT deleted!");

        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/a")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }


}
