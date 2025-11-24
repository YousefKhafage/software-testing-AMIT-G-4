package Pages;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Signup extends BasePage{

    public Signup(WebDriver driver) {
        super(driver);
    }
    private final By sigUpButton = By.xpath("//button[@data-qa=\"signup-button\"]");
    private final By name = By.name("name");
    private final By email = By.cssSelector("input[data-qa=\"signup-email\"]");
    private final By gender1 = By.id("id_gender1");
    private final By gender2 = By.id("id_gender2");
    private final By password = By.id("password");
    private final By dayLocator = By.id("days");
    private final By monthLocator = By.id("months");
    private final By yearLocator = By.id("years");
    private final By newsletter  = By.id("newsletter");
    private final By  specialOffers = By.id("optin");
    private final By firstNameLocator = By.cssSelector("input[data-qa=\"first_name\"]");
    private final By lastNameLocator = By.cssSelector("input[data-qa=\"last_name\"]");
    private final By companyLocator = By.cssSelector("input[data-qa=\"company\"]");
    private final By address1Locator = By.cssSelector("input[data-qa=\"address\"]");
    private final By address2Locator = By.cssSelector("input[data-qa=\"address2\"]");
    private final By countryLocator  = By.cssSelector("select[data-qa=\"country\"]");
    private final By  stateLocator= By.cssSelector("input[data-qa=\"state\"]");
    private final By cityLocator = By.cssSelector("input[data-qa=\"city\"]");
    private final By zipCodeLocator = By.id("zipcode");
    private final By  mobileLocator= By.id("mobile_number");
    private final By createAccount = By.xpath("//button[@data-qa=\"create-account\"]");
    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");

    public void enterName(String yourName){
        sendData(name,yourName);

    }
    public void enterEmail(String yourEMAIL){
        sendData(email,yourEMAIL);
    }
    public void clickOnSignupButton(){
        clickOn(sigUpButton);
    }
    public void selectGender(){
        clickOn(gender1);
    }
    public void enterPassword(String PASSWORD){
        sendData(password,PASSWORD);
    }

    public void selectDayMonthYear(String day,String month,String year){
        Select days = new Select(driver.findElement(dayLocator));
        days.selectByValue(day);
        Select months = new Select(driver.findElement(monthLocator));
        months.selectByValue(month);
        Select years = new Select(driver.findElement(yearLocator));
        years.selectByValue(year);}
    public void clickOnCheckboxNewsletter(){
        clickOn(newsletter);
    }
    public void clickOnCheckBoxSpecialOffer(){
        clickOn(specialOffers);
    }
    public void  FillDetailsFirstnameLastnameCompanyAddress2CountryStateCityZipcodeMobileNumber(String firstname,String lastname,String companyname,String address1,String address2,String country,String state,String city,String Zipcode,String mobilenumber){
        sendData(firstNameLocator,firstname);
        sendData(lastNameLocator,lastname);
        sendData(companyLocator,companyname);
        sendData(address1Locator,address1);
        sendData(address2Locator,address2);
        Select countrySelector = new Select(driver.findElement(countryLocator));
        countrySelector.selectByValue(country);
        sendData(stateLocator,state);
        sendData(cityLocator,city);
        sendData(zipCodeLocator,Zipcode);
        sendData(mobileLocator,mobilenumber);
    }



    public void ClickOnCreateAccount(){
        clickOn(createAccount);
    }
    public void ClickOnContinue(){clickOn(continueButton);

    }
}