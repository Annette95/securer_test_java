import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ApproveInvest_company_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public AssetsPage asset;
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
        asset = PageFactory.initElements(driver, AssetsPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmail)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickAssets();

    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void approveInvest() throws InterruptedException {
        asset.clickOnAsset("Crowne Plaza Shanghai Parking Lot");
        asset.clickToSeeRequestInvest("ion.leahu+2@titanium-soft.com");
        message.isElementLoaded(asset.approveButton);
        asset.clickApprove();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Investment was successfully approved"));
        message.clickClosePopUp();
        Thread.sleep(1000);
        asset.clickClose();
        asset.clickToAction("ion.leahu+2@titanium-soft.com");
        asset.choosePartition("partitions-partition-3");
        asset.isPartitionSelected("partitions-partition-3");
        asset.inputAmount("1000");
        asset.clickSendTokens();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Transfer was successful"));
    }
}
