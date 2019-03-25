import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ApproveKyc_admin_test {
    public WebDriver driver;
    private SignInPage signIn;
    private DataOfUser data;
    private AdminPage admin;
    private CheckingAsserts message;

    @BeforeMethod
    public void setUp() {
        data = new DataOfUser(driver);
        System.setProperty("webdriver.chrome.driver", data.webDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
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
    public void adminApprovesKYCofCompany() {
        admin.companiesClick();
        admin.statusIsVisible(data.companyEmail);
        admin.clickKYC(data.companyEmail);
        try {
            admin.clickApprove();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            admin.clickApprove();
        }
        admin.clickYes();
        admin.statusIsVisible(data.companyEmail);
        driver.navigate().refresh();
        WebElement kycStatusOf = admin.statusIsVisible(data.companyEmail);
        message.isElementLoaded(kycStatusOf);
        Assert.assertThat(kycStatusOf.getText(), CoreMatchers.equalTo("Approved"));
    }

    @Test
    public void adminApprovesKYCofInvestor() {
        admin.investorClick();
        admin.statusIsVisible("investor14@banit.me");
        admin.clickKYC("investor14@banit.me");
        try {
            admin.clickApprove();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            admin.clickApprove();
        }
        admin.clickApprove();
        admin.clickYes();
        admin.statusIsVisible("investor14@banit.me");
        driver.navigate().refresh();
        WebElement kycStatusOf = admin.statusIsVisible("investor14@banit.me");
        message.isElementLoaded(kycStatusOf);
        Assert.assertThat(kycStatusOf.getText(), CoreMatchers.equalTo("Approved"));
    }

}


