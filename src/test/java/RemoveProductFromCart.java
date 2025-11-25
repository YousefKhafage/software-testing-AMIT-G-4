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

public class RemoveProductFromCart{
    WebDriver driver;
    SoftAssert softassert = new SoftAssert();


    @BeforeMethod
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://automationexercise.com/");}
    @Test
    public void removeProductsFromCart() throws InterruptedException {
        page001Home home = new page001Home(driver);
        page05AllProduct products = new page05AllProduct(driver);
        CartPage cart = new CartPage(driver);

        Assert.assertTrue(driver.getTitle().contains("Automation Exercise"));

        products.hoverAddToCartFirstProduct();

        products.clickViewCart();

       // Assert.assertTrue(cart.isCartPageVisible(), "Cart page not visible");

        String productName = "Blue Top";
        cart.removeProductByName(productName);

        Assert.assertTrue(cart.isProductRemoved(productName), "Product is NOT removed from cart");
    }
}
