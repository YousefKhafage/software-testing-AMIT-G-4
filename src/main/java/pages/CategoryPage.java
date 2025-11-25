package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryPage {

    WebDriver driver;
    WebDriverWait wait;

    public CategoryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private final By categoryPanel = By.id("accordian");
    private final By womenCategory = By.xpath("//a[contains(@href,'#Women')]");
    private final By womenDress = By.xpath("//div[@id='Women']//a[contains(text(),'Dress')]");
    private final By menCategory = By.xpath("//a[contains(@href,'#Men')]");
    private final By menTshirts = By.xpath("//div[@id='Men']//a[contains(text(),'Tshirts')]");
    private final By categoryHeader = By.xpath("//h2[@class='title text-center']");

    public boolean areCategoriesVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(categoryPanel)).isDisplayed();
    }

    public void openWomenDressCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(womenCategory)).click();
        wait.until(ExpectedConditions.elementToBeClickable(womenDress)).click();
    }

    public boolean isCorrectCategoryDisplayed(String expectedText) {
        String actual = wait.until(ExpectedConditions.visibilityOfElementLocated(categoryHeader)).getText();
        return actual.trim().equalsIgnoreCase(expectedText.trim());
    }

    public void openMenTshirtsCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(menCategory)).click();
        wait.until(ExpectedConditions.elementToBeClickable(menTshirts)).click();
    }
}
