import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ApproveKycTest_admin {
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
        admin.companiesClick();

    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void adminApprovesKYCofCompany() {
//        admin.clickKYC("demo-company+934547680@securer.io");
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", admin.approveButton);
//        admin.clickApprove();
//        admin.clickYes();
//        admin.clickNo();
        WebElement kycStatusOf = admin.kycStatusIsVisible("company5@getnada.com");
        message.isElementLoaded(kycStatusOf);
        Assert.assertThat(kycStatusOf.getText(),CoreMatchers.equalTo("Approved"));
//        message.isElementLoaded((WebElement) admin.kycStatusIsVisible("demo-company+597059519@securer.io"));
//        Assert.assertThat(admin.kycStatusIsVisible("demo-company+597059519@securer.io"), CoreMatchers.containsString("Submitted"));
    }
}


