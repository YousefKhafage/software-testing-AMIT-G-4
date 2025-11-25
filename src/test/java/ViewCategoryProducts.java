import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CategoryPage;

public class ViewCategoryProducts{

    WebDriver driver;
    CategoryPage categoryPage;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        categoryPage = new CategoryPage(driver);
    }

    @Test
    public void testCategoryNavigation() {
        Assert.assertTrue(categoryPage.areCategoriesVisible(), "Categories are not visible!");
        categoryPage.openWomenDressCategory();
        Assert.assertTrue(
                categoryPage.isCorrectCategoryDisplayed("WOMEN - DRESS PRODUCTS"),
                "Women Dress category page text is incorrect!"
        );


        categoryPage.openMenTshirtsCategory();
        Assert.assertTrue(
                categoryPage.isCorrectCategoryDisplayed("MEN - TSHIRTS PRODUCTS"),
                "Men Tshirts category page text is incorrect!"
        );
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}