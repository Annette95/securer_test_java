import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ApproveDSO_admin_test {
    public WebDriver driver;
    private SignInPage signIn;
    private DataOfUser data;
    private AdminPage admin;
    private CheckingAsserts message;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        data = new DataOfUser(driver);
        driver.get(data.urlAdmin);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        admin = PageFactory.initElements(driver, AdminPage.class);
        signIn.typeEmail(data.adminEmail)
                .typePassword(data.password)
                .clickLogIn();


    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void adminApprovesDSO() {
        String dsoName = "Test";
        admin.dsoClick();
        admin.clickDSODetails(dsoName);
        admin.clickApprove();
        admin.clickYes();
        admin.statusIsVisibleForDSO(dsoName);
        driver.navigate().refresh();
        WebElement dsoStatusOf = admin.statusIsVisible(dsoName);
        message.isElementLoaded(dsoStatusOf);
        Assert.assertThat(dsoStatusOf.getText(), CoreMatchers.equalTo("Approved"));

    }
}
