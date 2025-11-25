package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
public class ScrollUPandDown extends base{

        public ScrollUPandDown(WebDriver driver) {
            super(driver);
        }

        By subscriptionText = By.xpath("//*[contains(text(),'Subscription')]");
        By arrowButton = By.id("scrollUp");
    By topText = By.xpath("//*[contains(text(),'Full-Fledged practice website')]");

        public void scrollDown() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        }


        public void scrollUp() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
        }

        public boolean isSubscriptionVisible() {
            return driver.findElement(subscriptionText).isDisplayed();
        }
    public boolean isTopTextVisible() {
        return driver.findElement(topText).isDisplayed();
    }

        public void clickArrowUp() {
            clickOn(arrowButton);
        }
    }



