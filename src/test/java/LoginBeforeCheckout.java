import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Duration;

public class LoginBeforeCheckout {
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
        public void placeOrder_LoginBeforeCheckout() throws InterruptedException {
            page001Home home = new page001Home(driver);
            page03Login login = new page03Login(driver);
            page05AllProduct products = new page05AllProduct(driver);
            RegisterBeforeCheckoutPage cart = new RegisterBeforeCheckoutPage(driver);
            RegisterBeforeCheckoutPage checkout = new RegisterBeforeCheckoutPage(driver);
            RegisterBeforeCheckoutPage payment = new RegisterBeforeCheckoutPage(driver);
            RegisterBeforeCheckoutPage account = new RegisterBeforeCheckoutPage(driver);

            Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

            home.clickOnSignupLogin();
            softassert.assertTrue(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2")).isDisplayed());
            login.enterEmailPassword("nm8196342@gmail.com", "nadaMohamed1912");
            login.clickOnLoginButton();

            softassert.assertTrue(driver.findElement(By.xpath("//i[@class=\"fa fa-user\"]")).isDisplayed());

            products.hoverAddToCartFirstProduct();
            products.hoverAddToCartSecondProduct();
            products.clickViewCart();

            cart.proceedToCheckout();

            checkout.enterOrderComment("IT IS MY ORDER");
            checkout.clickPlaceOrder();

            payment.enterPaymentInfo("John Doe", "4111111111111111", "123", "12", "2030");
            payment.payAndConfirm();

            Assert.assertTrue(payment.isOrderSuccessMessageVisible());

            //account.deleteAccount();
            //Assert.assertTrue(account.isAccountDeletedVisible());
            //account.continueAfterAccountCreated();
        }
    }


