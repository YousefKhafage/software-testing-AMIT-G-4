package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterBeforeCheckoutPage {

    WebDriver driver;
    WebDriverWait wait;
    private By accountDeletedMsg;

    public RegisterBeforeCheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locators
    By addToCartBtn = By.cssSelector("button.btn.btn-default.cart");
    By viewCartBtn = By.cssSelector("a[href='/view_cart']");
    By proceedToCheckoutBtn = By.cssSelector(".btn.btn-default.check_out");
    By commentTextArea = By.name("message");
    By placeOrderBtn = By.xpath("//a[text()='Place Order']");
    By nameOnCard = By.name("name_on_card");
    By cardNumber = By.name("card_number");
    By cvcField = By.name("cvc");
    By expiryMonth = By.name("expiry_month");
    By expiryYear = By.name("expiry_year");
    By payAndConfirm = By.id("submit");
    By successMsg = By.xpath("//*[contains(text(), 'Congratulations! Your order has been confirmed!')]");
    By deleteAccountBtn = By.xpath("//a[contains(text(),'Delete Account')]");
    By accountDeletedMessage = By.xpath("//*[contains(text(),'Your account has been permanently deleted!')]");
    By continueBtn = By.xpath("//a[text()='Continue']");

    // Actions
    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     driver.findElement(addToCartBtn).click();
    }

    public void closeCartModalIfPresent() {
        try {
            WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal-footer .btn")));
            modal.click();
        } catch (Exception ignored) { }
    }

    public void openCart() {
        driver.findElement(viewCartBtn).click();
    }

    public void proceedToCheckout() {
        driver.findElement(proceedToCheckoutBtn).click();
    }

    public void enterOrderComment(String txt) {
        driver.findElement(commentTextArea).sendKeys(txt);
    }

    public void clickPlaceOrder() {
        driver.findElement(placeOrderBtn).click();
    }
    public void payAndConfirm() {
        WebElement payButton = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", payButton);
    }

    public void enterPaymentInfo(String name, String card, String cvc, String month, String year) {
        driver.findElement(nameOnCard).sendKeys(name);
        driver.findElement(cardNumber).sendKeys(card);
        driver.findElement(cvcField).sendKeys(cvc);
        driver.findElement(expiryMonth).sendKeys(month);
        driver.findElement(expiryYear).sendKeys(year);
    }

    public boolean isOrderSuccessMessageVisible() throws InterruptedException {
        Thread.sleep(1500); // تأخير بسيط للتأكد من ظهور العنصر
        WebElement successMsg = driver.findElement(By.xpath("//p[contains(text(),'Congratulations! Your order has been confirmed!')]"));
        return successMsg.isDisplayed(); // بدون scrollIntoView
    }

    public void deleteAccount() {
        // انتظر حتى يكون العنصر موجود في الصفحة
        WebElement deleteButton = wait.until(ExpectedConditions.presenceOfElementLocated(deleteAccountBtn));

        // اضغط على الزر باستخدام JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
    }

    public boolean isAccountDeletedVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedMessage)).isDisplayed();
    }

    public void continueAfterAccountCreated() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }
}