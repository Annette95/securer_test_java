import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static java.lang.String.format;

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

    @FindBy(xpath = "//*[contains(text(), 'Yes')]")
    private WebElement yesButton;

    @FindBy(className = "btn-danger")  //"//*[contains(text(), 'No')]"
    private WebElement noButton;

    @FindBy(className = "close-button")
    private WebElement closeButton;


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
        approveButton.click();
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

    public WebElement kycStatusIsVisible(String kycStatus) {
        String kycOfUser = String.format("div[aria-label='%s']", kycStatus);
        return  By.cssSelector(kycOfUser).findElement(driver);
    }

}
