import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dev-company.securer.io/register");
    }

    @After
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void checkIfEmailErrorIsVisible() {
        page = new SignUpPage(driver);
        page.typeEmail("njvnerje")
                .typePaasword("rfrrg")
                .repeatPass("rfrrg")
                .clickRegister();
        List<String> errorLabels = Arrays.asList("Invalid email","Passwords must have at least 6 signs");
        Assert.assertEquals("Incorrect errors",errorLabels,page.getErrors());
    }

}
