package Zadanie1;

import PageObject.AddressPage;
import PageObject.LoginPage;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewUserAddressSteps {
    private WebDriver driver;

    @Given("^User is logged in to shop$")
    public void userIsLoggedInToShop() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski@vp.pl", "6434737235");
    }

    @When("^User goes to Address page$")
    public void userGoesToAddressPage() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.clickAccountPageButton();
        addressPage.addNewAddress();
    }

    @And("^User inputs alias (.*), address (.*), postcode (.*), city (.*), phone (.*)$")
    public void userInputsAddressPostcodeCity(String alias, String address, String postcode, String city, String phone) {
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

    @Then("^User see Address successfully added! alias (.*), address (.*), postcode (.*), city (.*), phone (.*) and country is correct.$")
    public void userSeeAddressSuccessfullyAdded(String alias, String address, String postcode, String city, String phone) {
        String country = "United Kingdom";
        List <WebElement> addressElements = driver.findElements(By.xpath("//article/div[@class='address-body']"));

        //Tworzenie listy wpisanych danych
        List<String> addressContain = new ArrayList<>();
        addressContain.add(alias);
        addressContain.add(address);
        addressContain.add(postcode);
        addressContain.add(city);
        addressContain.add(country);
        addressContain.add(phone);

        //Wyswietlenie ostatnio dodanego adresu
        //System.out.println(addressElements.get(addressElements.size() - 1).getText());
        String lastAddress = addressElements.get(addressElements.size() - 1).getText();

        //Sprawdzenie czy ostatnio dodany adres zgadza sie z wpisanymi danymi
        boolean correct = false;
        for (String item : addressContain) {
            if (lastAddress.contains(item)) {
                correct = true;
                break;
            }
        }
        Assert.assertTrue(correct);

    }

    @And("^User deletes address, and removes the address and logs out of the account$")
    public void userDeletesAddressAndRemovesTheAddressAndLogsOutOfTheAccount() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.deleteAddressAndLogOut();
    }

    @After
    public void tearDown() {
        //driver.quit();
    }
}
