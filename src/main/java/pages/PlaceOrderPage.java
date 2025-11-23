package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PlaceOrderPage {

    WebDriver driver;

    public PlaceOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isHomePageVisible() {
        return driver.getTitle().contains("Automation");
    }

    public void clickProductsMenu() throws InterruptedException {
        Thread.sleep(2000);
        WebElement productsMenu = driver.findElement(By.xpath("//a[contains(text(),'Products')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productsMenu);
        productsMenu.click();
    }

    public void clickFirstViewProduct() throws InterruptedException {
        Thread.sleep(2000);
        WebElement viewProduct = driver.findElement(By.xpath("(//a[contains(text(),'View Product')])[1]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", viewProduct);
        viewProduct.click();
    }

    public void addToCart() throws InterruptedException {
        Thread.sleep(1500);
        WebElement addCart = driver.findElement(By.xpath("//button[@class='btn btn-default cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addCart);
        addCart.click();
    }
    public void closeCartModalIfPresent() throws InterruptedException {
        Thread.sleep(1500);
        try {
            WebElement modalClose = driver.findElement(By.cssSelector("#cartModal .close-modal"));
            if(modalClose.isDisplayed()) {
                modalClose.click();
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("Cart modal not present, continuing...");
        }
    }

    public void openCart() throws InterruptedException {
        Thread.sleep(1000);
        WebElement cartLink = driver.findElement(By.xpath("//a[@href='/view_cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cartLink);
        cartLink.click();
    }

    public void proceedToCheckout() throws InterruptedException {
        Thread.sleep(1500);
        WebElement checkoutBtn = driver.findElement(By.xpath("//a[contains(text(),'Proceed To Checkout')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutBtn);
        checkoutBtn.click();
    }

    public void clickRegisterLogin() throws InterruptedException {
        Thread.sleep(1500);
        WebElement regLogin = driver.findElement(By.xpath("//u[contains(text(),'Register / Login')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", regLogin);
        regLogin.click();
    }

    public void signupUser(String name, String email) throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys(email);
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();
    }

    public void fillAccountInfo() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.id("id_gender1")).click();
        driver.findElement(By.id("password")).sendKeys("123456");
        driver.findElement(By.id("days")).sendKeys("5");
        driver.findElement(By.id("months")).sendKeys("May");
        driver.findElement(By.id("years")).sendKeys("1995");

        driver.findElement(By.id("first_name")).sendKeys("Test");
        driver.findElement(By.id("last_name")).sendKeys("User");
        driver.findElement(By.id("company")).sendKeys("Company");
        driver.findElement(By.id("address1")).sendKeys("Cairo");
        driver.findElement(By.id("country")).sendKeys("Canada");
        driver.findElement(By.id("state")).sendKeys("Cairo");
        driver.findElement(By.id("city")).sendKeys("Cairo");
        driver.findElement(By.id("zipcode")).sendKeys("12345");
        driver.findElement(By.id("mobile_number")).sendKeys("0123456789");

        driver.findElement(By.cssSelector("button[data-qa='create-account']")).click();
    }

    public void continueAfterAccountCreated() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a[data-qa='continue-button']")).click();
    }

    public boolean isLoggedInAsVisible() {
        return driver.findElement(By.xpath("//*[contains(text(),'Logged in as')]")).isDisplayed();
    }

    public void enterOrderComment(String comment) throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.name("message")).sendKeys(comment);
    }

    public void clickPlaceOrder() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[text()='Place Order']")).click();
    }

    public void enterPaymentInfo(String name, String cardNumber, String cvc, String month, String year) throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.name("name_on_card")).sendKeys(name);
        driver.findElement(By.name("card_number")).sendKeys(cardNumber);
        driver.findElement(By.name("cvc")).sendKeys(cvc);
        driver.findElement(By.name("expiry_month")).sendKeys(month);
        driver.findElement(By.name("expiry_year")).sendKeys(year);
    }

    public void payAndConfirm() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.id("submit")).click();
    }
    public boolean isOrderSuccessMessageVisible() {
        return driver.findElement(By.xpath("//*[contains(text(),'successfully')]")).isDisplayed();
    }
    public void deleteAccount() throws InterruptedException {
        Thread.sleep(1500);
        driver.findElement(By.xpath("//a[@href='/delete_account']")).click();
    }
    public boolean isAccountDeletedVisible() {
        return driver.findElement(By.cssSelector("h2[data-qa='account-deleted']")).isDisplayed();
    }
}
