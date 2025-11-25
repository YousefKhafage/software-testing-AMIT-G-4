import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.page05AllProduct;

public class BrandTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://automationexercise.com");
    }

    @Test
    public void verifyBrandNavigation() {

        page05AllProduct products = new page05AllProduct(driver);

        // 3 - Click Products button
        products.clickOnAllProducts();

        // 4 - Verify brands visible
        Assert.assertTrue(products.isBrandsVisible(), "Brands section is NOT visible!");

        // 5 - Click first brand
        String firstBrand = "Polo";
        products.clickBrand(firstBrand);

        // 6 - Verify brand page loaded
        Assert.assertTrue(products.isBrandPageLoaded(firstBrand),
                "Brand page for " + firstBrand + " did NOT load!");

        // 7 - Click another brand
        String secondBrand = "Madame";
        products.clickBrand(secondBrand);

        // 8 - Verify second brand page loaded
        Assert.assertTrue(products.isBrandPageLoaded(secondBrand),
                "Brand page for " + secondBrand + " did NOT load!");
    }
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
