package Zadanie2;

import PageObject.AddressPage;
import PageObject.LoginPage;
import PageObject.ShopPage;
import cucumber.api.java.After;
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
    String quantityOfProducts;

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
        driver.get("https://prod-kurs.coderslab.pl/" +
                "index.php?controller=authentication");

        String userMail = "jankowalski@vp.pl";
        String userPassword = "6434737235";
        String userName = "Jan Kowalski";

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(userMail, userPassword);

        WebElement userAccount = driver.findElement
                (By.xpath("//a[@title='View my customer account']"));
        Assert.assertEquals(userName, userAccount.getText());
    }

    @When("^User goes to Hummingbird Printed Sweater site$")
    public void userGoesToHummingbirdPrintedSweaterSiteAndChecksDiscount() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.searchSweater();

        WebElement hummingbirdPrintedSweaterElement = driver.findElement(
                By.xpath("//*[@id='main']//h1"));
        Assert.assertEquals("HUMMINGBIRD PRINTED SWEATER",
                hummingbirdPrintedSweaterElement.getText());
    }

    @And("^User checks discount$")
    public void userChecksDiscount() {
        WebElement regularPriceElement = driver.findElement
                (By.xpath("//span[@class='regular-price']"));
        WebElement currentPriceElement = driver.findElement
                (By.xpath("//span[@itemprop='price']"));
        WebElement discountPercentageElement = driver.findElement
                (By.xpath("//div[@class='current-price']/" +
                        "span[@class='discount discount-percentage']"));

        double regularPrice = Double.parseDouble
                (regularPriceElement.getText().substring(1));
        double currentPrice = Double.parseDouble
                (currentPriceElement.getText().substring(1));
        String discount = discountPercentageElement.getText();
        double discountPercentage = Double.parseDouble
                (discount.substring(5,discount.length()-1));
        double percent = 100 - discountPercentage;
        double salePrice = regularPrice * percent / 100;

        Assert.assertEquals(currentPrice,salePrice,0);
    }

    @And("^User choices size (.*)$")
    public void userChoicesSizeSizeAndQuantityQuantity(String size) {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceSize(size);

        //System.out.println(shopPage.sizeSelected());
        Assert.assertEquals(size, shopPage.sizeSelected());
    }

    @And("^User choices quantity (.*)$")
    public void userChoicesQuantity(String quantity) {
        quantityOfProducts = "(" + quantity + ")";

        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceQuantity(quantity);

        WebElement quantityElement = driver.findElement
                (By.xpath("//*[@id='quantity_wanted']"));
        //System.out.println(quantityElement.getAttribute("value"));
        Assert.assertEquals(quantity,quantityElement.getAttribute("value"));
    }

    @And("^User adds product to cart$")
    public void userAddsProductToCart() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.addToCart();

        WebElement cartElement = driver.findElement
                (By.xpath("//*[@id='_desktop_cart']//span[@class='cart-products-count']"));
        //System.out.println(cartElement.getText());
        Assert.assertEquals(quantityOfProducts, cartElement.getText());
    }

    @And("^User goes to proceed to checkout$")
    public void userGoesToProceedToCheckout() {
        String currentUrl = "https://prod-kurs.coderslab.pl/index.php?controller=order";
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToProceedToCheckout();

        Assert.assertEquals(currentUrl,driver.getCurrentUrl());
    }

    @And("^User selects the address$")
    public void userConfirmsTheAddress() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.confirmTheAddress();

        WebElement addressRadioButton = driver.findElement
                (By.xpath("//*[@name='id_address_delivery']"));
        Assert.assertTrue(addressRadioButton.isSelected());
    }

    @And("^User goes to shipping method$")
    public void userGoesToShippingMethod() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToShippingMethod();

        WebElement addressConfirmDoneElement = driver.findElement
                (By.xpath("//*[@id='checkout-addresses-step']/h1/i"));
        Assert.assertTrue(addressConfirmDoneElement.isDisplayed());
    }

    @And("^User choices the method of delivery$")
    public void userChoosesTheMethodOfDelivery() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choiceDelivery();

        WebElement picUpInStoreButton = driver.findElement
                (By.xpath("//*[@id='delivery_option_1']"));
        Assert.assertTrue(picUpInStoreButton.isSelected());
    }

    @And("^User goes to payment$")
    public void userGoesToPayment() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.goToPaymentMethod();

        WebElement deliveryConfirmDoneElement = driver.findElement
                (By.xpath("//*[@id='checkout-delivery-step']/h1/i"));
        Assert.assertTrue(deliveryConfirmDoneElement.isDisplayed());
    }

    @And("^User choices payment$")
    public void userChoosesPayment() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.choicePayment();
        shopPage.selectAgreeToTheTermsOfServiceButton();

        WebElement payByCheckButton = driver.findElement
                (By.xpath("//*[@id='payment-option-1']"));
        WebElement agreeToTheTermsServiceButton = driver.findElement
                (By.xpath("//*[@id='conditions_to_approve[terms-and-conditions]']"));
        Assert.assertTrue(payByCheckButton.isSelected());
        Assert.assertTrue(agreeToTheTermsServiceButton.isSelected());
    }

    @And("^User clicks the order with an obligation to pay$")
    public void userClicksTheOrderWithAnObligationToPay() {
        ShopPage shopPage = new ShopPage(driver);
        shopPage.orderWithObligatoryToPay();

        WebElement orderInformationElement = driver.findElement
                (By.xpath("//*[@id='content-hook_order_confirmation']//h3"));
        Assert.assertTrue(orderInformationElement.getText().contains("YOUR ORDER IS CONFIRMED"));
    }

    @Then("^User sees ORDER IS CONFIRMED, and takes screenshot$")
    public void userSeesORDERISCONFIRMEDAndTakesScreenshot() throws IOException {
        WebElement totalPriceElement = driver.findElement
                (By.xpath("//*[@id='content-hook_payment_return']//span[@class='price']"));
        totalPrice = Double.parseDouble(totalPriceElement.getText().substring(1));

        ShopPage shopPage = new ShopPage(driver);
        shopPage.screenShot();

        WebElement orderInformationElement = driver.findElement
                (By.xpath("//*[@id='content-hook_order_confirmation']//h3"));
        Assert.assertTrue(orderInformationElement.getText().contains("YOUR ORDER IS CONFIRMED"));
    }

    @And("^User goes to Order history and details page$")
    public void userGoesToOrderHistoryAndDetailsPage() {
        String orderHistoryUrl = "https://prod-kurs.coderslab.pl/index.php?controller=history";
        AddressPage addressPage = new AddressPage(driver);
        ShopPage shopPage = new ShopPage(driver);

        addressPage.clickAccountPageButton();
        shopPage.goToOrderHistoryAndDetail();

        Assert.assertEquals(orderHistoryUrl, driver.getCurrentUrl());
    }

    @And("^User checks if the order is on the list with Awaiting check payment status and the same amount$")
    public void userChecksIfTheOrderIsOnTheListWithAwaitingCheckPaymentStatusAndTheSameAmount() {
        WebElement lastTotalPriceElement = driver.findElement
                (By.xpath("//*[@id='content']/table/tbody/tr[1]/td[2]"));
        WebElement invoiceElement = driver.findElement
                (By.xpath("//*[@id='content']/table/tbody/tr[1]/td[4]/span"));

        double lastTotalPrice = Double.parseDouble
                (lastTotalPriceElement.getText().substring(1));

        Assert.assertEquals(totalPrice, lastTotalPrice, 0);
        Assert.assertEquals("Awaiting check payment", invoiceElement.getText());
    }

    @And("^User logs out$")
    public void userLogsOut() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.signOut();

        WebElement signInSignOutElement = driver.findElement
                (By.xpath("//*[@id='_desktop_user_info']//span"));
        Assert.assertEquals("Sign in", signInSignOutElement.getText());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
