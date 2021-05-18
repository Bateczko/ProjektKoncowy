package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class LoginPage {

        private WebDriver driver;

        @FindBy(name = "email")
        private static WebElement emailInput;

        @FindBy(name = "password")
        private static WebElement passwordInput;

        @FindBy(id = "submit-login")
        private static WebElement signInButton;

        public LoginPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }

        public void loginAs(String email, String password) {
            emailInput.click();
            emailInput.clear();
            emailInput.sendKeys(email);

            passwordInput.click();
            passwordInput.clear();
            passwordInput.sendKeys(password);

            signInButton.click();
        }

        public String getLoggedUsername() {
            WebElement userName = driver.findElement(By.xpath("//a[@class='account']"));
            return userName.getText();
        }

        public String getFailedLoginMessage() {
            return driver.findElement(By.cssSelector("#content li")).getText();
        }
    }
