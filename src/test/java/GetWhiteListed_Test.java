import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class GetWhiteListed_Test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public AssetsPage asset;
    private DataOfUser data;

    String assetName = "Test";

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        data = new DataOfUser(driver);
        driver.get(data.urlInvestor);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
        asset = PageFactory.initElements(driver, AssetsPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.investorEmail)
                .typePassword(data.password)
                .clickLogIn();

    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void getWhiteListedClick() {
        dashboard.clickMarkets();
        dashboard.clickAssets();
        asset.clickOnAsset("New York Sunrise Tower");
//        asset.submitGetWhiteListed();
//        asset.clickYes();
//        message.isElementLoaded(message.successfulPopuP);
//        Assert.assertThat(message.successfulPopuP.getText(),CoreMatchers.containsString("Request was send successfully"));


    }
}
