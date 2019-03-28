import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddAsset_company_test {
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
    public void fillAssetAndSubmit() {
        asset.clickAddAsset();
        asset.selectOption("country", "France");
        asset.fillEntityDetails(data.assetName, "12", "OOO", "12", "Selenium", "Paris");
        asset.clickNext();
        asset.selectOption("isPublic", "Public");
        asset.selectOption("assetType", "Shares");
        asset.introduceDescription("Sed ut perspiciatis unde omnis iste natus error ");
        asset.introduceDetailedDescription(data.longDescription);
        asset.uploadFile("image", "kity.jpg");
        asset.clickNext();
        asset.clickAddNewDoc();
        asset.typeTitle("licence");
        asset.uploadDoc("file", "kity.jpg");
        asset.clickaSave();
        asset.clickAddNewDoc();
        asset.typeTitle("buletin");
        asset.uploadDoc("file", "kity.jpg");
        asset.clickaSave();
        asset.clickDelete("buletin");
        asset.clickNext();
        asset.clickNext();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Asset was added successfully"));
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("ASSETS LIST"));
    }
}
