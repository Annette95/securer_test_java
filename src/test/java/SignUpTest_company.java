import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignUpTest_company {
    private WebDriver driver;
    private SignUpPage page;
    private CheckingAsserts message;
    private DashboardPage dashboard;
    private DataOfUser data;


    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        data = new DataOfUser(driver);
        driver.get(data.urlCompany+"/register");
        page = PageFactory.initElements(driver, SignUpPage.class);
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
        page.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .repeatPass(data.password)
                .clickRegister();
        message.isElementLoaded(message.errorPopUp);
        Assert.assertThat(message.errorPopUp.getText(), CoreMatchers.containsString("Duplicate Entry"));
    }

    @Test
    public void introducingIncorrectEmail() {
        page.typeEmail("company")
                .typePassword(data.password)
                .repeatPass(data.password)
                .clickRegister();
        Assert.assertThat(message.errorMessage.getText(), CoreMatchers.containsString("Invalid email"));
    }

    @Test
    public void introducingDifferentPasswords() {
        page.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .repeatPass("23432234")
                .clickRegister();
        List<String> errorLabels = Arrays.asList("Passwords don't match");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
    }

    @Test
    public void successfullRegister() {
        page.typeEmail("demo-company+3@securer.io")
                .typePassword(data.password)
                .repeatPass(data.password)
                .clickRegister();
        message.isElementLoaded(dashboard.sideBar);
        Assert.assertThat(driver.getCurrentUrl(),CoreMatchers.equalTo(data.urlCompany +"dashboard"));
    }
}

