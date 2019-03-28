import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignIn_company_test {
    private WebDriver driver;
    private SignInPage signIn;
    private CheckingAsserts message;
    private DashboardPage dashboard;
    private DataOfUser data;

    @BeforeMethod
    public void setUp() {
        data = new DataOfUser(driver);
        System.setProperty("webdriver.chrome.driver", data.webDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(data.urlCompany);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


    @Test
    public void emptyFormAndIncorrectEmail() {
        signIn.clickLogIn();
        List<String> errorLabels = Arrays.asList("Required", "Required");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
        signIn.typeEmail("demo.demo.demo.company@mail.ru")
                .typePassword(data.password)
                .clickLogIn();
        message.isElementLoaded(message.errorPopUp);
        Assert.assertThat(message.errorPopUp.getText(), CoreMatchers.containsString("Invalid credentials, wrong email or password"));
    }


    @Ignore
    public void loginAsCompany() {
        signIn.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .clickLogIn();
        message.isElementLoaded(dashboard.sideBar);
        Assert.assertThat(driver.getCurrentUrl(), CoreMatchers.equalTo(data.urlCompany + "dashboard"));
    }

}
