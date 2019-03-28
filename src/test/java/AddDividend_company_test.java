import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddDividend_company_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public DividendPage dividend;
    private DataOfUser data;

    String assetForDSO = "Crowne Plaza Shanghai Parking Lot";
    String newDivivend = "Dividend - autotest6";

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
        dividend = PageFactory.initElements(driver, DividendPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmailEx)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickDividends();

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void addDividendAndSubmit() {
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("DIVIDENDS HISTORY"));
        dividend.clickAddDividend();
        dividend.selectOption(assetForDSO);
        dividend.clickSelect();
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("ASSET INFO"));
        dividend.clickCreateDividend();
        List<String> errorLabels = Arrays.asList("This field is required",
                "This field is required", "This field is required",
                "This field is required", "This field is required",
                "This field is required", "You must upload at least one file");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
        dividend.fillDividendForm("Dividend - autotest5",
                data.dividendDescr,
                "1000");
        dividend.selectCurrency("RMB");
        dividend.typeFromDate("03/01/2019\n");
        dividend.typeToDate("03/26/2019");
        dividend.uploadFile("file", "Picture6MB.jpg");
        Assert.assertThat(message.errorMessage.getText(), CoreMatchers.containsString("Picture6MB.jpg, max size is 5Mb"));
        dividend.uploadFile("file", "kity.jpg");
        dividend.uploadFile("file", "parking.jpg");
        dividend.uploadFile("file", "passport.jpg");
        dividend.clickCreateDividend();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Dividend was added successfully"));
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("DIVIDENDS HISTORY"));
        dividend.findDividend(newDivivend);
        dividend.dividendIsVisibleAndClick(newDivivend);
    }

    @Test
    public void makeTransfer() {
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("DIVIDENDS HISTORY"));
        dividend.findDividend(newDivivend);
        dividend.dividendIsVisibleAndClick(newDivivend);
        dividend.checkInvestor("1");
        dividend.clickTransfer();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Transfer success"));
    }
}