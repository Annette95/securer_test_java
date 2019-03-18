import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;

public class SignUpPage {
    public static final int EMAIL_ERROR_LABEL = 0;
    public static final int PASSWORD_ERROR_LABEL = 1;

    private WebDriver driver;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.id("email");
    private By passwordField = By.id("password");
    private By repeatPassword = By.id("passwordConfirm");
    private By register = By.xpath("//*[contains(text(), 'Register now')]");
    private String errorByText = "//*[text()='%s']";


    public SignUpPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignUpPage typePaasword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public SignUpPage repeatPass(String password) {
        driver.findElement(repeatPassword).sendKeys(password);
        return this;
    }

    public SignUpPage clickRegister() {
        driver.findElement(register).click();
        return this;
    }
}

