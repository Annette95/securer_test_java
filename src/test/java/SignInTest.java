import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignInTest {
    private WebDriver driver;
    private SignInPage signIn;
    private CheckingAsserts message;
    private DashboardPage dashboard;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dev-company.securer.io");
        signIn = new SignInPage(driver);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


    @Test
    public void emptyForm() {
        signIn.clickLogIn();
        List<String> errorLabels = Arrays.asList("Required", "Required");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
    }

    @Test
    public void incorrectEmail() {
        signIn.typeEmail("demo.demo.demo.company@mail.ru")
                .typePassword("qwe123")
                .clickLogIn();
        message.isElementLoaded(message.errorPopUp);
        Assert.assertThat(message.errorPopUp.getText(), CoreMatchers.containsString("Invalid credentials, wrong email or password"));
    }


    @Test
    public void loginAsCompany() {
        signIn.typeEmail("demo-company+3@securer.io")
                .typePassword("qwe123")
                .clickLogIn();
        message.isElementLoaded(dashboard.sideBar);
        Assert.assertThat(driver.getCurrentUrl(), CoreMatchers.equalTo(message.urlCompany + "dashboard"));
    }

}
