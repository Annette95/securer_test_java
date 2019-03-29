import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AdminPage {
    private WebDriver driver;

    public AdminPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//a[@href='/companies']")
    private WebElement companiesLink;

    @FindBy(xpath = "//a[@href='/investors']")
    private WebElement investorsLink;

    @FindBy(xpath = "//a[@href='/assets']")
    private WebElement assetsLink;

    @FindBy(xpath = "//a[@href='/dso']")
    private WebElement dsoLink;

    @FindBy(xpath = "//a[@href='/reports']")
    private WebElement reportsLink;

    @FindBy(css = "button[value='approved']")
    public WebElement approveButton;

    @FindBy(className = "btn-success")////*[contains(text(), 'Yes')]
    private WebElement yesButton;

    @FindBy(className = "btn-danger")  //"//*[contains(text(), 'No')]"
    private WebElement noButton;

    @FindBy(className = "close-button")
    private WebElement closeButton;

    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    public WebElement nextButton;

    public AdminPage companiesClick() {
        companiesLink.click();
        return this;
    }

    public AdminPage investorClick() {
        investorsLink.click();
        return this;
    }

    public AdminPage assetsClick() {
        assetsLink.click();
        return this;
    }

    public AdminPage dsoClick() {
        dsoLink.click();
        return this;
    }

    public AdminPage reportsClick() {
        reportsLink.click();
        return this;
    }

    public AdminPage clickKYC(String kycDetails) {
        String kycOfUser = String.format("button[aria-label='%s']", kycDetails);
        driver.findElement(By.cssSelector(kycOfUser)).click();
        return this;
    }

    public AdminPage clickApprove() {
        JavascriptExecutor ex = (JavascriptExecutor) driver;
        ex.executeScript("arguments[0].click();", approveButton);
     //   approveButton.click();
        return this;
    }

    public AdminPage clickYes() {
        yesButton.click();
        return this;
    }

    public AdminPage clickClose() {
        closeButton.click();
        return this;
    }

    public AdminPage clickNo() {
        noButton.click();
        return this;
    }

    public WebElement statusIsVisible(String status) {
        String statusOfUser = String.format("div[aria-label='%s']", status);
        return  By.cssSelector(statusOfUser).findElement(driver);
    }

    public AdminPage clickNext(){
        nextButton.click();
        return this;
    }

    public AdminPage clickAssetDetails(String assetDetails) {
        String assetOfUser = String.format("button[value='%s']", assetDetails);
        driver.findElement(By.cssSelector(assetOfUser)).click();
        return this;
    }

    public AdminPage clickDSODetails(String dso){
        String dsoDetails = String.format("button[aria-label='%s']", dso);
        driver.findElement(By.cssSelector(dsoDetails)).click();
        return this;
    }

    public WebElement statusIsVisibleForDSO(String status) {
        String statusOfUser = String.format("div[aria-label='%s-status']", status);
        return  By.cssSelector(statusOfUser).findElement(driver);
    }



}
