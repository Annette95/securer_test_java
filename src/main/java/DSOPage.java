import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;

public class DSOPage {
    private WebDriver driver;

    public DSOPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "button[aria-label='add-issuance']")
    private WebElement addDSOButton;

    @FindBy(xpath= "//*[@class='mt-2 css-s2x0a3 form-text text-muted' and contains(text(),'Add partition')]")
    private WebElement addPartitionButton;

    @FindBy(id = "partitionId")
    private WebElement partitionInput;

    @FindBy(xpath = "//*[@class='button-content' and contains(text(),'Next')]")
    private WebElement nextPageButton;

    @FindBy(xpath= "//*[@class='mt-2 css-s2x0a3 form-text text-muted' and contains(text(),'Select partition')]")
    private WebElement selectPartitionButton;

    @FindBy(css = "input[name='startDate']")
    private WebElement startDateInput;

    @FindBy(css = "input[name='endDate']")
    private WebElement endDateInput;

    @FindBy(css = "input[name='tokenValue']")
    private WebElement tokenValueInput;

    @FindBy(id = "react-select-2-input")
    private WebElement investmentCurrenciesInput;

    @FindBy(css = "input[name='tokensToIssue']")
    private WebElement tokensToIssueInput;

    @FindBy(css = "input[name='sharesToIssue']")
    private WebElement sharesToIssueInput;

    @FindBy(css = "input[name='softCap']")
    private WebElement softCapInput;

    @FindBy(xpath = "//*[@class='button-content' and contains(text(),'Submit')]")
    private WebElement submitButton;

    @FindBy(className = "btn-success")
    private WebElement yesButton;

    public DSOPage clickAddDSO(){
        addDSOButton.click();
        return this;
    }

    public DSOPage selectOption(String listName, String option) {
        String listLocator = String.format("select[name='%s']", listName);
        driver.findElement(By.cssSelector(listLocator)).click();
        String optionLocator = String.format("//select[@name = '%s']/option[text() = '%s']", listName, option);
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }

    public DSOPage clickAddPartition(){
        addPartitionButton.click();
        return this;
    }

    public DSOPage clickSelectPartition(){
        selectPartitionButton.click();
        return this;
    }

    public DSOPage typePartition(String partition){
        partitionInput.sendKeys(partition);
        return this;
    }

    public DSOPage clickNext(){
        nextPageButton.click();
        return this;
    }

    public DSOPage fillDSOForm(String sdate, String edate, String tokenVal, String tokenIss, String shareIss, String softcap){
        startDateInput.sendKeys(sdate);
        endDateInput.sendKeys(edate);
        tokenValueInput.sendKeys(tokenVal);
        tokensToIssueInput.sendKeys(tokenIss);
        sharesToIssueInput.sendKeys(shareIss);
        softCapInput.sendKeys(softcap);
        return this;
    }

    public DSOPage typeInvestmentCurrencies(String[] currency){
        for (int i =0; i< currency.length; i++){
            investmentCurrenciesInput.sendKeys(currency[i]);
        }
        return this;
    }

    public DSOPage clickSubmit(){
        submitButton.click();
        return this;
    }

    public DSOPage submitDSO(String dso){
        String dsoDetails = String.format("button[aria-label='%s-submit']", dso);
        driver.findElement(By.cssSelector(dsoDetails)).click();
        return this;
    }

    public DSOPage clickYes(){
        yesButton.click();
        return this;
    }
}
