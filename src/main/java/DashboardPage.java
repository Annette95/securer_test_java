import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    private WebDriver driver;

    public DashboardPage (WebDriver driver) {
        this.driver = driver;
    }

    private By assetsLink = By.xpath("//a[@href='/assets']");
    private By profileLink = By.xpath("//a[@href='/profile']");


    public DashboardPage clickAssets () {
        driver.findElement(assetsLink).click();
        return this;
    }


    public DashboardPage clickProfile () {
        driver.findElement(profileLink).click();
        return this;
    }
}

