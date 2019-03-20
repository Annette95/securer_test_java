import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddTokenize_Test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public AssetsPage asset;
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
        asset = PageFactory.initElements(driver, AssetsPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickAssets();

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void addingTokenization() {
        asset.clickOnAsset("Test");
        asset.clickTokenize();
        asset.fillTokenizeForm("AutoToken", "AUT", "70", "1", "10000");
        asset.uploadFile("logo", "kity.jpg");
        asset.selectOption("isRedeemable", "No");
        asset.selectOption("fiatCurrency", "USD");
        asset.selectOption("canIssueNewTokens", "No");
        asset.clickAddNewPartition();
        asset.typePatrition("First");
        asset.clickaSave();
        asset.clickAddNewPartition();
        asset.typePatrition("Second");
        asset.clickaSave();
        asset.clickAddNewPartition();
        asset.typePatrition("Third");
        asset.clickaSave();
        asset.submitTokenization();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(),CoreMatchers.containsString("Asset was successfully tokenized"));
    }
}