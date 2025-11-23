import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.page04testcase;
import pages.page05AllProduct;

import java.time.Duration;
import java.util.ArrayList;

public class page05AllProductsTest {
    WebDriver driver;
    page05AllProduct AllProducts;
    ;
    SoftAssert softassert = new SoftAssert();


    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://automationexercise.com/");
    }

    @Test()
    public void testCase8() throws InterruptedException {
        AllProducts = new page05AllProduct(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        AllProducts.clickOnAllProducts();
        //product list is visible
        WebElement list = driver.findElement(By.xpath("//*[@class=\"features_items\"]/h2"));
        softassert.assertTrue(list.isDisplayed());
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        AllProducts.clickOnFirstProduct();
        //user is land to product details page
        softassert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/product_details/1");
        //verify all details name,category,price,availability,condition,brand
        WebElement name = driver.findElement(By.xpath("//*[@class=\"product-information\"]/h2"));
        name.getText();
        softassert.assertTrue(name.isDisplayed());
        WebElement category = driver.findElement(By.xpath("//*[@class=\"product-information\"]/p[1]"));
        category.getText();
        softassert.assertTrue(category.isDisplayed());
        WebElement price = driver.findElement(By.xpath("//*[@class=\"product-information\"]/span/span"));
        price.getText();
        softassert.assertTrue(price.isDisplayed());
        WebElement avaliabitity = driver.findElement(By.xpath("//*[@class=\"product-information\"]/p[2]/b"));
        avaliabitity.getText();
        softassert.assertTrue(avaliabitity.isDisplayed());
        WebElement condition = driver.findElement(By.xpath("//*[@class=\"product-information\"]/p[3]/b"));
        condition.getText();
        softassert.assertTrue(condition.isDisplayed());
        WebElement brand = driver.findElement(By.xpath("//*[@class=\"product-information\"]/p[4]/b"));
        brand.getText();
        softassert.assertTrue(brand.isDisplayed());
        softassert.assertAll();
    }

    @Test()
    public void testCase9() throws InterruptedException {
        AllProducts = new page05AllProduct(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        AllProducts.clickOnAllProducts();
        WebElement list = driver.findElement(By.xpath("//*[@class=\"features_items\"]/h2"));
        softassert.assertTrue(list.isDisplayed());
        AllProducts.searchForProduct("top");
        AllProducts.clickOnSearchButton();
        String searchedItems = driver.findElement(By.cssSelector("h2[class=\"title text-center\"]")).getText();
        softassert.assertEquals(searchedItems, "SEARCHED PRODUCTS");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)");
        int numOfProduct = driver.findElements(By.xpath("//*[@class=\"features_items\"]//*[@class=\"col-sm-4\"] ")).size();
        System.out.println(numOfProduct);
        for (int i = 1; i <= numOfProduct; i++) {
            String nameOfProduct = driver.findElement(By.xpath("//*[@class=\"features_items\"]//*[@class=\"col-sm-4\"][" + i + "]")).getText();
            softassert.assertTrue(nameOfProduct.contains("Top"));
        }
        softassert.assertAll();
    }

    @Test()
    public void testCase12() throws InterruptedException {
        AllProducts = new page05AllProduct(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        AllProducts.clickOnAllProducts();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
        Thread.sleep(1000);
       // Hover over first product and click 'Add to cart'
        AllProducts.hoverAddToCartFirstProduct();
        AllProducts.clickContinueShopping();
        //Hover over second product and click 'Add to cart'
        AllProducts.hoverAddToCartSecondProduct();
        //Click 'View Cart' button
        AllProducts.clickViewCart();
        // Verify both products are added to Cart
        softassert.assertTrue(driver.findElement(By.xpath("//*[@href=\"/product_details/1\"]")).isDisplayed());
        softassert.assertTrue(driver.findElement(By.xpath("//*[@href=\"/product_details/2\"]")).isDisplayed());
        //Verify their prices, quantity and total price
        String price1 = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_price\"]")).getText();
        softassert.assertTrue(price1.equals("Rs. 500"));
        String price2 = driver.findElement(By.cssSelector("tr[id=\"product-2\"] td[class=\"cart_price\"]")).getText();
        softassert.assertTrue(price2.equals("Rs. 400"));
        String quantity1 = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_quantity\"]")).getText();
        softassert.assertTrue(quantity1.equals("1"));
        String quantity2 = driver.findElement(By.cssSelector("tr[id=\"product-2\"] td[class=\"cart_quantity\"]")).getText();
        softassert.assertTrue(quantity2.equals("1"));
        String totalPrice1 = driver.findElement(By.cssSelector("tr[id=\"product-1\"] p[class=\"cart_total_price\"]")).getText();
        softassert.assertTrue(totalPrice1.equals("Rs. 500"));
        String totalPrice2 = driver.findElement(By.cssSelector("tr[id=\"product-2\"] p[class=\"cart_total_price\"]")).getText();
        softassert.assertTrue(totalPrice2.equals("Rs. 400"));
        softassert.assertAll();
    }@Test
    public void testCase13() throws InterruptedException {
        AllProducts = new page05AllProduct(driver);
        String url = driver.getCurrentUrl();
        softassert.assertEquals(url, "https://automationexercise.com/");
        AllProducts.clickOnAllProducts();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
        AllProducts.clickOnFirstProduct();
        String url2=driver.getCurrentUrl();
        softassert.assertEquals(url2,"https://automationexercise.com/product_details/1");
        AllProducts.updateQuantity();
        AllProducts.AddToCartInPageDetails();
        Thread.sleep(Long.parseLong("1000"));
        AllProducts.clickViewCart();
        softassert.assertTrue(driver.findElement(By.xpath("//*[@href=\"/product_details/1\"]")).isDisplayed());
        String quantity1 = driver.findElement(By.cssSelector("tr[id=\"product-1\"] td[class=\"cart_quantity\"]")).getText();
        softassert.assertTrue(quantity1.equals("4"));
        softassert.assertAll();
    }
    @AfterMethod
    public void quitBrowser() {
        driver.quit();

    }
}
