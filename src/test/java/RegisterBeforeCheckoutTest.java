import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.page001Home;
import pages.page02Signup;
import pages.RegisterBeforeCheckoutPage;
import java.time.Duration;
public class RegisterBeforeCheckoutTest{
    WebDriver driver;
    page001Home home;
    page02Signup signUp;
    SoftAssert softassert = new SoftAssert();


    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");}

    @Test
    public void placeOrderRegisterBeforeCheckout() throws InterruptedException {

        SoftAssert softassert = new SoftAssert();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        home = new page001Home(driver);
        signUp = new page02Signup(driver);
        RegisterBeforeCheckoutPage registerBeforeCheckout = new RegisterBeforeCheckoutPage(driver);

        String password = "123456";

        // Step 3: Verify home page
        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        // Step 4: Click 'Signup / Login' button
        home.clickOnSignupLogin();

        // Step 5: Fill signup details
        String email = "test" + System.currentTimeMillis() + "@mail.com";
        signUp.enterName("TestUser");
        signUp.enterEmail(email);
        signUp.clickOnSignupButton();

        WebElement title = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div[1]/h2/b"));
        softassert.assertTrue(title.isDisplayed());

        signUp.selectGender();
        signUp.enterPassword(password);
        signUp.selectDayMonthYear("11", "6", "2002");
        signUp.clickOnCheckboxNewsletter();
        signUp.clickOnCheckBoxSpecialOffer();

        signUp.FillDetailsFirstnameLastnameCompanyAddress2CountryStateCityZipcodeMobileNumber(
                "Test", "User", "MyCompany", "123 Street", "Apt 4",
                "United States", "CA", "Los Angeles", "90001", "1234567890"
        );

        signUp.ClickOnCreateAccount();

        driver.get("https://automationexercise.com/product_details/1");
        registerBeforeCheckout.addToCart();
        registerBeforeCheckout.closeCartModalIfPresent();


        registerBeforeCheckout.openCart();


        Assert.assertTrue(driver.getTitle().contains("Checkout"));


        registerBeforeCheckout.proceedToCheckout();

        registerBeforeCheckout.enterOrderComment("This is my order");
        registerBeforeCheckout.clickPlaceOrder();

        registerBeforeCheckout.enterPaymentInfo("Test User", "4242424242424242", "123", "12", "2025");
        registerBeforeCheckout.payAndConfirm();

        Assert.assertTrue(registerBeforeCheckout.isOrderSuccessMessageVisible(), "Congratulations! Your order has been confirmed!");
        registerBeforeCheckout.isOrderSuccessMessageVisible();

        registerBeforeCheckout.deleteAccount();
        Assert.assertTrue(registerBeforeCheckout.isAccountDeletedVisible(), "Your account has been permanently deleted!");
        registerBeforeCheckout.continueAfterAccountCreated();
    }
        @AfterMethod
        public void quitBrowser() {
            driver.quit();

        }}
