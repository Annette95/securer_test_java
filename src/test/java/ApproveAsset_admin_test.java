import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ApproveAsset_admin_test {
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

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void adminApprovesAsset() throws InterruptedException {
        admin.assetsClick();
        Thread.sleep(2000);
        String asset = driver.findElement(By.xpath("//*[@id='root']/div/main/div/div[3]/div/div/div[1]/div[2]/div[1]/div/div[1]")).getText();
        admin.clickAssetDetails(asset);
        admin.clickApprove();
        admin.clickYes();
        admin.statusIsVisible(asset);
        driver.navigate().refresh();
        WebElement assetStatus = admin.statusIsVisible(asset);
        message.isElementLoaded(assetStatus);
        Assert.assertThat(assetStatus.getText(), CoreMatchers.equalTo("Approved"));
    }
}
