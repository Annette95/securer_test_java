import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class KYC
{
    private WebDriver driver;
    private SignInPage signIn;
    private DashboardPage dashboard;
    private KYCpage kyc;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dev-company.securer.io");
        signIn = new SignInPage(driver);
        dashboard = new DashboardPage(driver);
        kyc = new KYCpage(driver);
        signIn.typeEmail("demo-company+33293098@securer.io")
                .typePassword("qwe123")
                .clickLogIn();
        dashboard.clickProfile();


    }

//    @After
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void FillKYCFORM() {
        kyc.fillKKYC1stStep("pharma","pharma.com","Anna","Miller","canavinanna@gmail.com","200000", "2000", "12");
        kyc.selectSector("Select sector", "Housing");
        kyc.selectCountry("Select country","Antarctica");
        kyc.clickNext();
        kyc.uploadFile("file_1","kity.jpg");
        kyc.uploadFile("file_2","kity.jpg");
        kyc.uploadFile("file_3","kity.jpg");
        kyc.uploadFile("file_4","kity.jpg");
        kyc.uploadFile("file_5","kity.jpg");
        kyc.clickNext();
        kyc.clickNext();

    }

}
