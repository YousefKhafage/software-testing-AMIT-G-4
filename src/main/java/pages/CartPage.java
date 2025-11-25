package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.Instant;


public class CartPage extends base{
    WebDriverWait wait;
    WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private final By footer = By.xpath("//*[@id=\"footer\"]");
    private final By emailLocator = By.id("susbscribe_email");
    private final By arrowButton = By.id("subscribe");
    private final By removeBtn =By.xpath("//td[text()='Blue Top']/following-sibling::td[@class='cart_delete']/a[@class='cart_quantity_delete']");

    public void scrollToFooter() {
        clickOn(footer);
    }
    public void enterSubscribeEmail(String email) {
        sendData(emailLocator, email);
    }

    public void clickONarrowButton() {
        clickOn(arrowButton);
    }
    public void removeProductByName(String productName) {
        WebElement removeBtn = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']/ancestor::tr//a[@class='cart_quantity_delete']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", removeBtn);
    }

    // دالة للتحقق إن المنتج تم حذفه
    public boolean isProductRemoved(String productName) {
        try {
            By removedProduct = By.xpath("//td[@class='cart_description']//a[text()='" + productName + "']");
            wait.until(ExpectedConditions.invisibilityOfElementLocated(removedProduct));
            return true;
        } catch (Exception e) {
            return false;
        }

    }}
