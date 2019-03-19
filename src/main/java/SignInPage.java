import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage {

        private WebDriver driver;

        public SignInPage (WebDriver driver) {
            this.driver = driver;
        }

        @FindBy (id = "email")
        private WebElement emailField;
        @FindBy(id = "password")
        private WebElement passwordField;
        @FindBy(css = "button[type='submit']")
        private WebElement logInMeClick;


    public SignInPage typeEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignInPage clickLogIn() {
        logInMeClick.click();
        return this;
    }
}
