import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;

public class PlaceOrderTest {

    WebDriver driver;
    PlaceOrderPage placeOrder;

    @BeforeMethod
    public void setup() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
        Thread.sleep(3000);

        placeOrder = new PlaceOrderPage(driver);
    }

    @Test
    public void PlaceOrderTest14() throws InterruptedException {
        Assert.assertTrue(placeOrder.isHomePageVisible());
        placeOrder.clickProductsMenu();
        placeOrder.clickFirstViewProduct();
        placeOrder.addToCart();
        placeOrder.closeCartModalIfPresent(); // 🔹 FIX MODAL
        placeOrder.openCart();
        placeOrder.proceedToCheckout();
        placeOrder.clickRegisterLogin();
        placeOrder.signupUser("TestUser", "test" + System.currentTimeMillis() + "@mail.com");
        placeOrder.fillAccountInfo();
        placeOrder.continueAfterAccountCreated();
        Assert.assertTrue(placeOrder.isLoggedInAsVisible());
        placeOrder.openCart();
        placeOrder.proceedToCheckout();
        placeOrder.enterOrderComment("Please deliver ASAP");
        placeOrder.clickPlaceOrder();
        placeOrder.enterPaymentInfo("Test User", "1111222233334444", "123", "12", "2030");
        placeOrder.deleteAccount();
        Assert.assertTrue(placeOrder.isAccountDeletedVisible());
    }

    @AfterMethod
    public void BrowserQuit() {
        driver.quit();
    }
}
