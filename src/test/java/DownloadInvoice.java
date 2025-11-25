import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.PlaceOrderPage;

public class DownloadInvoice {

    WebDriver driver;
    PlaceOrderPage Invoice;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Invoice = new PlaceOrderPage(driver);
    }



    @Test
    public void OrderInvoice() throws Exception {

        // 1-2. Navigate to URL
        driver.get("https://automationexercise.com");
        Assert.assertTrue(Invoice.isHomePageVisible(), "Home page not displayed!");
        Invoice.clickProductsMenu();
        Invoice.clickFirstViewProduct();
        Invoice.addToCart();
        Invoice.closeCartModalIfPresent();
        Invoice.openCart();
        Assert.assertTrue(driver.getTitle().contains("Checkout") || driver.getCurrentUrl().contains("view_cart"));
        Invoice.proceedToCheckout();
        Invoice.clickRegisterLogin();
        String email = "user" + System.currentTimeMillis() + "@test.com";
        Invoice.signupUser("TestUser", email);
        Invoice.fillAccountInfo();
        Invoice.continueAfterAccountCreated();
        Assert.assertTrue(Invoice.isLoggedInAsVisible(), "'Logged in as' not visible!");
        Invoice.openCart();
        Invoice.proceedToCheckout();
        Assert.assertTrue(driver.getPageSource().contains("Address Details"));
        Assert.assertTrue(driver.getPageSource().contains("Review Your Order"));
        Invoice.enterOrderComment("Please deliver fast.");
        Invoice.clickPlaceOrder();
        Invoice.enterPaymentInfo("MyName", "1111222233334444", "123", "12", "2028");
        Invoice.payAndConfirm();
        driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'Download Invoice')]")).click();
        Thread.sleep(2000);
        driver.findElement(org.openqa.selenium.By.xpath("//a[contains(text(),'Continue')]")).click();
        Invoice.deleteAccount();
        Assert.assertTrue(Invoice.isAccountDeletedVisible(), "'ACCOUNT DELETED!' is not visible!");
    }

    @AfterMethod
    public void QuitBrowser() {
        driver.quit();
    }
}



