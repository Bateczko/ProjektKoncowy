package Zadanie2;

import PageObject.AddressPage;
import PageObject.AssertShopPage;
import PageObject.LoginPage;
import PageObject.ShopPage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuyHummingbirdPrintedSweaterSteps {

    private WebDriver driver;
    double totalPrice;

    @Before
    public void setUp(){
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://prod-kurs.coderslab.pl/" +
                "index.php?controller=authentication");
    }

    @Given("^User is logged in to PrestaShop$")
    public void userIsLoggedInToPrestaShop() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski@vp.pl", "6434737235");
    }

    @When("^User goes to Hummingbird Printed Sweater site$")
    public void userGoesToHummingbirdPrintedSweaterSiteAndChecksDiscount() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.searchSweater();
    }

    @Then("^User checks discount$")
    public void userChecksDiscount() {
        AssertShopPage assertShopPage = new AssertShopPage(driver);
        Assert.assertTrue(assertShopPage.isDiscountIsCorrect());
    }

    @When("^User choices size (.*)$")
    public void userChoicesSizeSizeAndQuantityQuantity(String size) {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceSize(size);
    }

    @And("^User choices quantity (.*)$")
    public void userChoicesQuantity(String quantity) {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceQuantity(quantity);
    }

    @And("^User adds product to cart$")
    public void userAddsProductToCart() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.addToCart();
    }

    @Then("^User has (.*) products in the cart$")
    public void userHasProductsInTheCart(String amountOfProducts) {
        AssertShopPage assertShopPage = new AssertShopPage(driver);
        Assert.assertEquals(amountOfProducts, assertShopPage.getAmountOfProductsInCart());
    }

    @When("^User goes to proceed to checkout$")
    public void userGoesToProceedToCheckout() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToProceedToCheckout();
    }

    @And("^User selects the address$")
    public void userConfirmsTheAddress() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.confirmTheAddress();
    }

    @And("^User goes to shipping method$")
    public void userGoesToShippingMethod() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToShippingMethod();
    }

    @And("^User choices the method of delivery$")
    public void userChoosesTheMethodOfDelivery() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceDelivery();
    }

    @And("^User goes to payment$")
    public void userGoesToPayment() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToPaymentMethod();
    }

    @And("^User choices payment$")
    public void userChoosesPayment() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choicePayment();
        shopPage.selectAgreeToTheTermsOfServiceButton();
    }

    @And("^User clicks the order with an obligation to pay$")
    public void userClicksTheOrderWithAnObligationToPay() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.orderWithObligatoryToPay();
    }

    @Then("^User sees (.*)$")
    public void userSeesOrderIsConfirmed(String orderInformation){
        AssertShopPage assertShopPage = new AssertShopPage(driver);
        Assert.assertTrue(assertShopPage.getOrderInformation().contains(orderInformation));
    }

    @When("^User takes screenShot and saves price$")
    public void userTakesScreenShotAndSavesPrice() throws IOException {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.screenShot();
        totalPrice = shopPage.savePrice();
    }


    @And("^User goes to Order history and details page$")
    public void userGoesToOrderHistoryAndDetailsPage() {
        AddressPage addressPage = new AddressPage(driver);
        addressPage.clickAccountPageButton();

        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToOrderHistoryAndDetail();
    }

    @Then("^User checks if the order is on the list with (.*) status and the same amount$")
    public void userChecksIfTheOrderIsOnTheListWithAwaitingCheckPaymentStatusAndTheSameAmount(String status) {
        AssertShopPage assertShopPage = new AssertShopPage(driver);
        Assert.assertEquals(totalPrice, assertShopPage.getTotalPriceAtOrderHistory(), 0);
        Assert.assertEquals(status, assertShopPage.getOrderStatus());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
