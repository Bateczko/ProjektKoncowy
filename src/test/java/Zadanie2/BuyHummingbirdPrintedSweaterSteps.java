package Zadanie2;

import PageObject.AddressPage;
import PageObject.LoginPage;
import PageObject.ShopPage;
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


    @Given("^User is logged in to PrestaShop$")
    public void userIsLoggedInToPrestaShop() {
        // Uruchom nowy egzemplarz przeglądarki Chrome
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do Google
        driver.get("https://prod-kurs.coderslab.pl/index.php?controller=authentication");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("jankowalski@vp.pl", "6434737235");

    }

    @When("^User goes to Hummingbird Printed Sweater site$")
    public void userGoesToHummingbirdPrintedSweaterSiteAndChecksDiscount() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.searchSweater();
    }

    @And("^User checks discount$")
    public void userChecksDiscount() {
        WebElement regularPriceElement = driver.findElement(By.xpath("//span[@class='regular-price']"));
        WebElement currentPriceElement = driver.findElement(By.xpath("//span[@itemprop='price']"));
        WebElement discountPercentageElement = driver.findElement(By.xpath("//div[@class='current-price']/span[@class='discount discount-percentage']"));

        double regularPrice = Double.parseDouble(regularPriceElement.getText().substring(1));
        double currentPrice = Double.parseDouble(currentPriceElement.getText().substring(1));
        String discount = discountPercentageElement.getText();
        double discountPercentage = Double.parseDouble(discount.substring(5,discount.length()-1));
        double percent = 100 - discountPercentage;
        double salePrice = regularPrice * percent / 100;

        Assert.assertEquals(currentPrice,salePrice,0);
    }

    @And("^User choices size (.*)$")
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
    @And("^User goes to proceed to checkout$")
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

    @Then("^User sees ORDER IS CONFIRMED, and takes screenshot$")
    public void userSeesORDERISCONFIRMEDAndTakesScreenshot() throws IOException {
        ShopPage shopPage = new ShopPage(driver);
        WebElement totalPriceElement = driver.findElement
                (By.xpath("//*[@id='content-hook_payment_return']//span[@class='price']"));

        totalPrice = Double.parseDouble(totalPriceElement.getText().substring(1));
        //System.out.println(price);
        shopPage.screenShot();
    }

    @And("^User goes to Order history and details page$")
    public void userGoesToOrderHistoryAndDetailsPage() {
        AddressPage addressPage = new AddressPage(driver);
        ShopPage shopPage = new ShopPage(driver);

        addressPage.clickAccountPageButton();
        shopPage.goToOrderHistoryAndDetail();

    }
    @And("^User checks if the order is on the list with Awaiting check payment status and the same amount$")
    public void userChecksIfTheOrderIsOnTheListWithAwaitingCheckPaymentStatusAndTheSameAmount() {
        WebElement lastTotalPriceElement = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[2]"));
        WebElement invoiceElement = driver.findElement(By.xpath("//*[@id='content']/table/tbody/tr[1]/td[4]/span"));


        double lastTotalPrice = Double.parseDouble(lastTotalPriceElement.getText().substring(1));

        Assert.assertEquals(totalPrice, lastTotalPrice, 0);
        Assert.assertEquals("Awaiting check payment", invoiceElement.getText());

    }
}
