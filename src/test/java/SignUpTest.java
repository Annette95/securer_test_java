import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest {
    private WebDriver driver;
    private SignUpPage page;
    private CheckingAsserts message;
    private DashboardPage dashboard;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dev-company.securer.io/register");
        page = new SignUpPage(driver);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


    @Test
    public void emptyForm() {
        page.clickRegister();
        List<String> errorLabels = Arrays.asList("Required", "Required", "Required");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
    }

    @Test
    public void introducingInvalidEmail() {
        page.typeEmail("company@example.com")
                .typePaasword("qwe123")
                .repeatPass("qwe123")
                .clickRegister();
        message.isElementLoaded(message.errorPopUp);
        Assert.assertThat(message.errorPopUp.getText(), CoreMatchers.containsString("Duplicate Entry"));
    }

    @Test
    public void introducingIncorrectEmail() {
        page.typeEmail("company")
                .typePaasword("qwe123")
                .repeatPass("qwe123")
                .clickRegister();
        Assert.assertThat(message.errorMessage.getText(), CoreMatchers.containsString("Invalid email"));
    }

    @Test
    public void introducingDifferentPasswords() {
        page.typeEmail("company@example.com")
                .typePaasword("qwe123")
                .repeatPass("23432234")
                .clickRegister();
        List<String> errorLabels = Arrays.asList("Passwords don't match");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
    }

    @Test
    public void successfullRegister() {
        page.typeEmail("demo-company+3@securer.io")
                .typePaasword("qwe123")
                .repeatPass("qwe123")
                .clickRegister();
        message.isElementLoaded(dashboard.sideBar);
        Assert.assertThat(driver.getCurrentUrl(),CoreMatchers.equalTo(message.urlCompany +"dashboard"));
    }
}

