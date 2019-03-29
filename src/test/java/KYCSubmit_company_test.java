import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class KYCSubmit_company_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private KYCpage kyc;
    private CheckingAsserts message;
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
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
        kyc = PageFactory.initElements(driver, KYCpage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickProfile();

    }

    @AfterMethod
    public void tearDown(ITestResult testResult) throws IOException {
        if(testResult.getStatus()==ITestResult.FAILURE){
            utilities.Screenshots.takeScreenshot(driver, testResult.getName());
        }
        driver.quit();
    }

    @Test
    public void FillKYCFORM() {
        kyc.fillKKYC1stStepCompany(
                "pharma",
                "pharma.com",
                "Anna",
                "Miller",
                "canavinanna@gmail.com",
                "2000",
                "12");
        kyc.selectOption("sector", "Housing");
        kyc.selectOption("country", "Antarctica");
        kyc.clickNext();
        kyc.uploadFile("file_1", "kity.jpg");
        kyc.uploadFile("file_2", "kity.jpg");
        kyc.uploadFile("file_3", "kity.jpg");
        kyc.uploadFile("file_4", "kity.jpg");
        kyc.uploadFile("file_5", "kity.jpg");
        kyc.uploadFile("file_6", "kity.jpg");
        kyc.clickNext();
        kyc.clickNext();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(),CoreMatchers.containsString("KYC was successfully submitted!"));
    }

}