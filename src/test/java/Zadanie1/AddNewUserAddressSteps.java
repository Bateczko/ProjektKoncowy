package Zadanie1;

import PageObject.AddressPage;
import PageObject.AssertAddressPage;
import PageObject.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class AddNewUserAddressSteps {
    private WebDriver driver;

    @Before
    public void setUp(){
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://prod-kurs.coderslab.pl/" +
                "index.php?controller=authentication");
    }

    @Given("^User is logged in to shop$")
    public void userIsLoggedInToShop() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski@vp.pl", "6434737235");
    }

    @And("^User went to Address page$")
    public void userGoesToAddressPage() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.clickAccountPageButton();
        addressPage.addNewAddress();
    }

    @When("^User inputs alias (.*), address (.*), postcode (.*), city (.*), phone (.*)$")
    public void userInputsAddressPostcodeCity
            (String alias, String address, String postcode, String city, String phone) {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.changeAddress(alias, address, postcode, city, phone);
    }

    @And("^User selects country$")
    public void userSelectsCountry() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.selectCountry();
    }

    @And("^User saves new address$")
    public void userSavesNewAddress() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.saveNewAddress();
    }

    @Then("^Alias (.*), address (.*), postcode (.*), city (.*), phone (.*) and country is correct.$")
    public void userSeeAddressSuccessfullyAdded
            (String alias, String address, String postcode, String city, String phone) {
        AssertAddressPage assertAddressPage = new AssertAddressPage(driver);
        Assert.assertTrue(assertAddressPage.assertOfCorrectAddress
                (alias, address, postcode, city,phone));
    }

    @When("^User removes the address$")
    public void userDeletesAddressAndRemovesTheAddressAndLogsOutOfTheAccount() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.deleteAddress();
    }

    @Then("^User has one address$")
    public void userHasOneAddress() {
        AssertAddressPage assertAddressPage = new AssertAddressPage(driver);
        Assert.assertTrue(assertAddressPage.checkingIfTheUserHasOneAddress());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
