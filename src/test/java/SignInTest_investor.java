import org.hamcrest.CoreMatchers;
import org.junit.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;


import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SignInTest_investor {
    private WebDriver driver;
    private SignInPage signIn;
    private CheckingAsserts message;
    private DashboardPage dashboard;
    private DataOfUser data;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        data = new DataOfUser(driver);
        driver.get(data.urlInvestor);
        signIn = PageFactory.initElements(driver, SignInPage.class);
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
        signIn.typeEmail("demo.demo.demo.investor@mail.ru")
                .typePassword(data.password)
                .clickLogIn();
        message.isElementLoaded(message.errorPopUp);
        Assert.assertThat(message.errorPopUp.getText(), CoreMatchers.containsString("Invalid credentials, wrong email or password"));
    }


    @Test
    public void loginAsCompany() {
        signIn.typeEmail("demo-investor+2@securer.io")
                .typePassword(data.password)
                .clickLogIn();
        message.isElementLoaded(dashboard.sideBar);
        Assert.assertThat(driver.getCurrentUrl(), CoreMatchers.equalTo(data.urlInvestor + "dashboard"));
    }

}
