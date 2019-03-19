import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage {
    public static final int EMAIL_ERROR_LABEL = 0;
    public static final int PASSWORD_ERROR_LABEL = 1;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy (id = "email")
    private WebElement emailField;
    @FindBy (id = "password")
    private WebElement passwordField;
    @FindBy (id = "passwordConfirm")
    private WebElement repeatPassword;
    @FindBy (xpath= "//*[contains(text(), 'Register now')]")
    private WebElement register;


    public SignUpPage typeEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public SignUpPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public SignUpPage repeatPass(String password) {
        repeatPassword.sendKeys(password);
        return this;
    }

    public SignUpPage clickRegister() {
       register.click();
        return this;
    }
}

