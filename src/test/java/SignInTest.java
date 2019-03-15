import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.concurrent.TimeUnit;

public class SignInTest {
    private WebDriver driver;
    private SignInPage signIn;
    private DataForTests data;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/developer/Desktop/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://dev-company.securer.io");
    }

//    @After
//    public void closeDriver() {
//        driver.quit();
//    }

    @Test
    public void loginAdCompany() {
        signIn = new SignInPage(driver);
        signIn.typeEmail("demo-company+333293098@securer.io")
                .typePassword("qwe123")
                .clickLogIn();

    }

}
