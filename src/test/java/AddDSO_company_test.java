import com.sun.org.apache.xpath.internal.operations.String;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddDSO_company_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public DSOPage dso;
    private DataOfUser data;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        data = new DataOfUser(driver);
        driver.get(data.urlCompany);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
        dso = PageFactory.initElements(driver, DSOPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickDSO();

    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void addDSOAndSubmit() throws InterruptedException {
        Thread.sleep(1000);
        dso.clickAddDSO();
        dso.selectOption("assetId", "Test");
        dso.clickAddPartition();
        dso.typePartition("fdfdsf");
        dso.clickSelectPartition();
        dso.selectOption("partitionId", "First");
        dso.clickNext();
        dso.fillDSOForm("03/21/2019\n","12/21/2019\n","1","20000","12","10000");
        dso.selectOption("tokenCurrency","USD");
        java.lang.String[] currency = {"EUR\n","USD\n","ETH\n"};
        dso.typeInvestmentCurrencies(currency);
        dso.clickNext();
        dso.clickSubmit();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("DSO was added successfully"));
        message.isElementLoaded(message.pageTitle);
        dso.submitDSO("Test");
        dso.clickYes();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("DSO was submitted successfully"));
    }
}