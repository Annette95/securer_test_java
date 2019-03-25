import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.experimental.theories.Theories;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InvestTokens_investor_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public AssetsPage asset;
    private DataOfUser data;
    private CalculatorPage calculator;

    String assetName = "Test";

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
        asset = PageFactory.initElements(driver, AssetsPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        calculator = PageFactory.initElements(driver, CalculatorPage.class);
        signIn.typeEmail(data.investorEmail)
                .typePassword(data.password)
                .clickLogIn();
    }

//    @AfterMethod
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void investTokens() throws InterruptedException {
        dashboard.clickProfile();
        driver.get("https://dev-investor.securer.io/assets");
        asset.clickOnAsset("Huayuan Underground Parking Lot");
        asset.clickInvest();
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("CALCULATOR"));
        Thread.sleep(500);
        calculator.selectOption("currency", "usd");
        calculator.typeAmount("100");
        Assert.assertThat(calculator.tokensField.getAttribute("value"), CoreMatchers.containsString("100.0000"));
        calculator.clickInvest();
        Thread.sleep(500);
        message.investmentInformation("100.0000", "100", "USD");
        calculator.clickOkay();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("You successfully invested!"));
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("OVERVIEW"));
    }

    @Test
    public void requestInvestTokens() throws InterruptedException {
        dashboard.clickProfile();
        driver.get("https://dev-investor.securer.io/assets");
        asset.clickOnAsset("Huayuan Underground Parking Lot");
        asset.clickRequestInvest();
        asset.inputAmount("500");
        asset.clickRequest();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("You successfully requested invest!"));
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("OVERVIEW"));
    }

}
