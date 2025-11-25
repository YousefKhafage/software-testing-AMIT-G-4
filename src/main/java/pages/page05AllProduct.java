package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.time.Duration;

public class page05AllProduct extends base{
    Actions actions;

    public page05AllProduct(WebDriver driver) {
        super(driver);
        actions=new Actions(driver);
    }
    protected final By allProductsButton=By.xpath("//*[@href=\"/products\"]");
    protected final By product1Button=By.cssSelector("a[href=\"/product_details/1\"]");
    protected final By searchbarLocator=By.cssSelector("input[id=\"search_product\"]");
    protected final By searchButtonLocator=By.xpath("//*[@id=\"submit_search\"]");
   protected final By addToCartProduct1=By.xpath("/html/body/section[2]/div/div/div[2]/div/div[2]/div/div[1]/div[2]/div/a");
   protected final By addToCartProduct2=By.xpath("/html/body/section[2]/div/div/div[2]/div/div[3]/div/div[1]/div[2]/div/a/i");
    protected final By Product1=By.xpath("//*[@class=\"productinfo text-center\"]//*[@data-product-id=\"1\"]");
    protected final By Product2=By.xpath("//*[@class=\"productinfo text-center\"]//*[@data-product-id=\"2\"]");
    protected final By continueShopping=By.cssSelector("button[class=\"btn btn-success close-modal btn-block\"]");
    protected final By viewCart=By.xpath("//u[text()='View Cart']");
    protected final By quantity=By.id("quantity");
    protected final By addToCartInPageDetails=By.cssSelector("button[class=\"btn btn-default cart\"]");

    public void clickOnAllProducts(){
        clickOn(allProductsButton);
    }
    public void clickOnFirstProduct(){
        clickOn(product1Button);
    }
    public void searchForProduct(String text){
        sendData(searchbarLocator,text);
    }
    public void clickOnSearchButton(){
        clickOn(searchButtonLocator);
    }

    public void hoverAddToCartFirstProduct() throws InterruptedException {
        WebElement product = driver.findElement(Product1);
        actions.moveToElement(product).perform();
        WebElement addBtn = driver.findElement(addToCartProduct1);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addBtn);
    }

    public void hoverAddToCartSecondProduct() {
        WebElement product2 = driver.findElement(Product2);
        actions.moveToElement(product2).perform();
        WebElement addBtn = driver.findElement(addToCartProduct2);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", addBtn);
        actions.moveToElement(driver.findElement(Product2)).perform();

    }

    public void clickContinueShopping() throws InterruptedException {
       Thread.sleep(1000);
        clickOn(continueShopping);
    }


    public void clickViewCart() {
        By viewCartBtn = viewCart; // موجود أصلاً عندك
        try {
            // Option 1: Use WebDriverWait to wait until clickable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(viewCartBtn)).click();
        } catch (Exception e) {
            // Option 2: Fallback to JavaScript click if normal click fails
            WebElement viewCartElement = driver.findElement(viewCartBtn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", viewCartElement);
        }
    }
    public void updateQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity));
        driver.findElement(quantity).clear();
        sendData(quantity,"4");
    }
    public void AddToCartInPageDetails(){
        clickOn(addToCartInPageDetails);
    }
}

