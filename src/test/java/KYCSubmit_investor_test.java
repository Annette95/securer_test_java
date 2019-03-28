import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class KYCSubmit_investor_test {
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
        driver.get(data.urlInvestor);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
        kyc = PageFactory.initElements(driver, KYCpage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.investorEmail)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickProfile();

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void FillKYCFORM() {
        kyc.fillKKYC1stStepInvestorIndividual("12/24/1900\n","Anna","Anna",
                "canavinanna@gmail.com","Belgium",
                "Belgium");
        kyc.selectOption("country", "Belgium");
        kyc.selectFlag("Moldova");
        kyc.typePhone("78081512");
        kyc.selectOption("country", "Belgium");
        kyc.clickNext();
        kyc.uploadFile("passport", "kity.jpg");
        kyc.uploadFile("selfie", "kity.jpg");
        kyc.clickNext();
        kyc.clickNext();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(),CoreMatchers.containsString("KYC was successfully submitted!"));
    }

}
