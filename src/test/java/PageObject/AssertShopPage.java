package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssertShopPage {

    @FindBy(xpath = "//span[@class='regular-price']")
    private static WebElement regularPriceElement;

    @FindBy(xpath = "//span[@itemprop='price']")
    private static WebElement currentPriceElement;

    @FindBy(xpath = "//div[@class='current-price']/" +
            "span[@class='discount discount-percentage']")
    private static WebElement discountPercentageElement;

    @FindBy(xpath = "//*[@id='_desktop_cart']//span[@class='cart-products-count']")
    private static WebElement cartElement;

    @FindBy(xpath = "//*[@id='content-hook_order_confirmation']//h3")
    private static WebElement orderInformationElement;

    @FindBy(xpath = "//*[@id='content']/table/tbody/tr[1]/td[2]")
    private static WebElement lastTotalPriceElement;

    @FindBy(xpath = "//*[@id='content']/table/tbody/tr[1]/td[4]/span")
    private static WebElement invoiceElement;


    public AssertShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isDiscountIsCorrect(){
        double regularPrice = Double.parseDouble
                (regularPriceElement.getText().substring(1));
        double currentPrice = Double.parseDouble
                (currentPriceElement.getText().substring(1));
        String discount = discountPercentageElement.getText();
        double discountPercentage = Double.parseDouble
                (discount.substring(5,discount.length()-1));
        double percent = 100 - discountPercentage;
        double salePrice = regularPrice * percent / 100;

        return currentPrice == salePrice;
    }

    public String getAmountOfProductsInCart(){
        return cartElement.getText().substring
                (1, cartElement.getText().length()-1);
    }

    public String getOrderInformation(){
        return orderInformationElement.getText();
    }

    public double getTotalPriceAtOrderHistory(){
        return Double.parseDouble
                (lastTotalPriceElement.getText().substring(1));
    }

    public String getOrderStatus(){
        return invoiceElement.getText();
    }
}
