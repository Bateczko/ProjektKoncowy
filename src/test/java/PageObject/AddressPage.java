package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

    public class AddressPage {

        private WebDriver driver;

        @FindBy(xpath = "//a[@title='View my customer account']")
        private static WebElement accountPageButton;

        @FindBy (xpath = "//*[@id='main']/header")
        private static WebElement pageHeaderMessage;

        @FindBy(xpath = "//a[contains(@id,'address')]")
        private static WebElement addFirstAddressButton;

        @FindBy(xpath = "//*[@data-link-action='add-address']")
        private static WebElement createNewAddressButton;

        @FindBy(name = "alias")
        private static WebElement aliasInput;

        @FindBy(name = "address1")
        private static WebElement addressInput;

        @FindBy(name = "postcode")
        private static WebElement postcodeInput;

        @FindBy(name = "city")
        private static WebElement cityInput;

        @FindBy(name = "id_country")
        private static WebElement countryInput;

        @FindBy(name = "phone")
        private static WebElement phoneInput;

        @FindBy(xpath = "//footer/button")
        private static WebElement saveButton;

        @FindBy(xpath = "//*[@data-link-action='delete-address']")
        private static WebElement deleteButton;

        @FindBy(xpath = "//*[@id='_desktop_user_info']")
        private static WebElement signOutButton;

        @FindBy (xpath = "//*[@data-link-action='delete-address']")
        private static List <WebElement> deleteElements;


        public AddressPage(WebDriver driver){
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }

        public void clickAccountPageButton() {
            accountPageButton.click();
        }

        public void addNewAddress() {
            addFirstAddressButton.click();

            String urlAddAddress = "addresses";
            String url = driver.getCurrentUrl();
            //System.out.println("adres: " + url);

            //Porownanie adresow url
            if (url.contains(urlAddAddress)) {
                createNewAddressButton.click();
            }
        }

        public void changeAddress (String alias, String address, String postcode, String city, String phone){
            aliasInput.click();
            aliasInput.clear();
            aliasInput.sendKeys(alias);

            addressInput.click();
            addressInput.clear();
            addressInput.sendKeys(address);

            postcodeInput.click();
            postcodeInput.clear();
            postcodeInput.sendKeys(postcode);

            cityInput.click();
            cityInput.clear();
            cityInput.sendKeys(city);

            phoneInput.click();
            phoneInput.clear();
            phoneInput.sendKeys(phone);
        }

        public void selectCountry(){
            countryInput.click();
            Select roleDropDown = new Select(countryInput);
            roleDropDown.selectByValue("17");
        }

        public void saveNewAddress(){
            saveButton.click();
        }

        public void deleteAddressAndLogOut(){
            //Mozliwosc usuniecia wszystkich dodanych adresow jeśli i = 0
            //Obecnie zostawia 1 adres w celu wykonania zadania 2
            int deleteElementsCount = deleteElements.size();
            for (int i = 1; i < deleteElementsCount; i++){
                deleteButton.click();
            }
            System.out.println("Liczba adresów użytkownika: " + deleteElements.size());
            signOutButton.click();
        }
    }

