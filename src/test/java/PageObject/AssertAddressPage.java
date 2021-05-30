package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class AssertAddressPage {
    private WebDriver driver;

    @FindBy(xpath = "//article/div[@class='address-body']")
    private static List<WebElement> addressElements;

    public AssertAddressPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public boolean assertOfCorrectAddress
            (String alias, String address, String postcode, String city, String phone) {

        String country = "United Kingdom";
        List<String> addressContain = new ArrayList<>();
        addressContain.add(alias);
        addressContain.add(address);
        addressContain.add(postcode);
        addressContain.add(city);
        addressContain.add(country);
        addressContain.add(phone);

        String lastAddress = addressElements.get
                (addressElements.size() - 1).getText();

        for (String item : addressContain) {
            if (lastAddress.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkingIfTheUserHasOneAddress (){
        return addressElements.size() == 1;
    }
}



