import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

        private WebDriver driver;

        public SignInPage (WebDriver driver) {
            this.driver = driver;
        }

        private By emailField = By.id("email");
        private By passwordField = By.id("password");
        private By logInMeClick = By.cssSelector("button[type='submit']");

    public SignInPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignInPage clickLogIn() {
        driver.findElement(logInMeClick).click();
        return  this;
    }
}
