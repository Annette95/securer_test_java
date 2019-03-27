import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {

    public WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/assets']")
    public WebElement assetsLink;

    @FindBy(xpath = "//a[@href='/profile']")
    public WebElement profileLink;

    @FindBy(linkText = "//a[@href='/dashboard']")
    public WebElement dashboardLink;

    @FindBy(css = "div.scrollbar-container.sidebar-content.ps")
    public WebElement sideBar;

    @FindBy(xpath = "//a[@href='/dso']")
    public WebElement dsoLink;

    @FindBy(xpath = "//*[contains(@class,'menu-item-text d-inline') and contains(text(), 'Markets')]")
    private WebElement marketsLink;

    @FindBy(xpath = "//a[@href='/dividends']")
    public WebElement dividendsLink;

    @FindBy(xpath = "//a[@href='/votings']")
    public WebElement votingsLink;


    public DashboardPage clickAssets() {
        assetsLink.click();
        return this;
    }


    public DashboardPage clickProfile() {
        profileLink.click();
        return this;
    }

    public DashboardPage clickDashboard() {
        dashboardLink.click();
        return this;
    }

    public DashboardPage clickMarkets() {
        marketsLink.click();
        return this;
    }

    public DashboardPage clickDSO(){
        dsoLink.click();
        return this;
    }

    public DashboardPage clickDividends(){
        dividendsLink.click();
        return this;
    }

    public DashboardPage clickVotings(){
        votingsLink.click();
        return this;
    }
}

