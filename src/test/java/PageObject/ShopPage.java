package PageObject;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.io.File;
import java.io.IOException;

public class ShopPage {

    private WebDriver driver;

    @FindBy(xpath = "//*[@name='s']")
    private static WebElement searchElement;

    @FindBy(xpath = "//*[@id='ui-id-1']/li[1]")
    private static WebElement sweaterProduct;

    @FindBy(xpath = "//*[@id='group_1']")
    private static WebElement sizeElement;

    @FindBy(xpath = "//*[@id='quantity_wanted']")
    private static WebElement quantityElement;

    @FindBy(xpath = "//button[@data-button-action='add-to-cart']")
    private static WebElement addToCartButton;

    @FindBy(xpath = "//div[@class='cart-content-btn']/a")
    private static WebElement proceedToCheckOutButton;

    @FindBy(xpath = "//*[@class='btn btn-primary']")
    private static WebElement proceedToCheckOutOnShoppingCartButton;

    @FindBy(xpath = "//*[@name='id_address_delivery']")
    private static WebElement addressRadioButton;

    @FindBy(xpath = "//*[@name='confirm-addresses']")
    private static WebElement confirmAddressButton;

    @FindBy(xpath = "//*[@id='delivery_option_1']")
    private static WebElement pickUpInStoreButton;

    @FindBy(xpath = "//*[@name='confirmDeliveryOption']")
    private static WebElement confirmShippingMethodButton;

    @FindBy(xpath = "//*[@id='payment-option-1']")
    private static WebElement payByCheckButton;

    @FindBy(xpath = "//*[@id='conditions_to_approve[terms-and-conditions]']")
    private static WebElement agreeToTheTermsOfServiceButton;

    @FindBy(xpath = "//*[@id='payment-confirmation']/div/button[@type='submit']")
    private static WebElement orderWithAnObligationToPayButton;

    @FindBy(xpath = "//*[@id='content-hook_payment_return']//span[@class='price']")
    private static WebElement totalPriceElement;

    @FindBy(xpath = "//*[@id='history-link']")
    private static WebElement orderHistoryAndDetailsButton;

    public ShopPage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void searchSweater(){
        //Wyszukanie i wybranie produktu
        searchElement.click();
        searchElement.clear();
        searchElement.sendKeys("Hummingbird Printed Sweater");
        sweaterProduct.click();
    }

    public void choiceSize(String size){
        sizeElement.click();
        Select roleDropDown = new Select(sizeElement);
        //System.out.println(size);

        switch (size) {
            case "S":
                roleDropDown.selectByValue("1");
                break;
            case "M":
                roleDropDown.selectByValue("2");
                break;
            case "L":
                roleDropDown.selectByValue("3");
                break;
            case "XL":
                roleDropDown.selectByValue("4");
                break;
            default:
                System.out.println("Nieprawidlowy rozmiar");
                break;
        }
    }

    public void choiceQuantity(String quantity){
        new Actions(driver).click(quantityElement)
                .pause(200).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
                .pause(200).sendKeys(Keys.BACK_SPACE)
                .pause(200).sendKeys(quantity).perform();
    }

    public void addToCart(){
        addToCartButton.click();
        proceedToCheckOutButton.click();
    }

    public void goToProceedToCheckout(){
        proceedToCheckOutOnShoppingCartButton.click();
    }

    public void confirmTheAddress(){
        if(!addressRadioButton.isSelected()) {
            addressRadioButton.click();
        }
    }

    public void goToShippingMethod(){
        confirmAddressButton.click();
    }

    public void choiceDelivery(){
        if(!pickUpInStoreButton.isSelected()) {
            pickUpInStoreButton.click();
        }
    }

    public void goToPaymentMethod(){
        confirmShippingMethodButton.click();
    }

    public void choicePayment(){
        if(!payByCheckButton.isSelected()) {
            payByCheckButton.click();
        }
    }

    public void selectAgreeToTheTermsOfServiceButton(){
        if(!agreeToTheTermsOfServiceButton.isSelected()){
            agreeToTheTermsOfServiceButton.click();
        }
    }

    public void orderWithObligatoryToPay(){
        orderWithAnObligationToPayButton.click();
    }

    public void screenShot() throws IOException {
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File
                ("E:\\CL automatyzacja\\ProjektKoncowy\\screenShot"));
    }

    public double savePrice(){
        return Double.parseDouble(totalPriceElement.getText().substring(1));
    }

    public void goToOrderHistoryAndDetail(){
        orderHistoryAndDetailsButton.click();
    }
}
