import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class DividendPage {
    private WebDriver driver;

    public DividendPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Add New Dividend Event')]")
    private WebElement addDividendButton;

    @FindBy(xpath = "//*[@class ='btn btn-primary' and contains(text(), 'Select')]")
    private WebElement selectButton;

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "description")
    private WebElement descriptionInput;

    @FindBy(id = "amount")
    private WebElement amountInput;

    @FindBy(css = "input[name='fromDate']")
    private WebElement fromDateInput;

    @FindBy(css = "input[name='toDate']")
    private WebElement toDateInput;

    @FindBy(xpath = "//*[@class ='button-content' and contains(text(), 'Create Dividend Event')]")
    private WebElement createDividendButton;

    @FindBy(css = "input[placeholder='Filter by']")
    private WebElement filterInput;

    @FindBy(xpath = "//*[text()='Transfer']")
    private WebElement transferButton;

    public DividendPage clickAddDividend() {
        addDividendButton.click();
        return this;
    }

    public DividendPage clickSelect() {
        selectButton.click();
        return this;
    }

    public DividendPage selectOption(String option) {
        String optionLocator = String.format("//select//option[text() = '%s']", option);
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }


    public DividendPage fillDividendForm(String title, String description, String amount) {
        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        amountInput.sendKeys(amount);
        return this;
    }


    public DividendPage selectCurrency(String currency) {
        String optionCur = String.format("//select[@name = 'currency']/option[text() = '%s']", currency);
        driver.findElement(By.xpath(optionCur)).click();
        return this;
    }

    public DividendPage typeFromDate(String fromDate) {
        fromDateInput.sendKeys(fromDate);
        return this;
    }

    public DividendPage typeToDate(String toDate) {
        toDateInput.sendKeys(toDate);
        return this;
    }

    public DividendPage uploadFile(String fileSelector, String fileName) {
        String fileInput = String.format("input[type='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

    public DividendPage clickCreateDividend() {
        createDividendButton.click();
        return this;
    }

    public DividendPage findDividend(String divid) {
        filterInput.sendKeys(divid);
        filterInput.sendKeys(Keys.ENTER);
        return this;
    }

    public DividendPage dividendIsVisibleAndClick(String dividend) {
        String dividendCard = String.format("//*[contains(text(), '%s')]", dividend);
        driver.findElement(By.xpath(dividendCard)).click();
        return this;
    }

    public DividendPage checkInvestor(String number) {
        String investorDiv = String.format("//div[2]/div[%s]/div/div[1]/input",number);
        driver.findElement(By.xpath(investorDiv)).click();
        return this;
    }

    public DividendPage clickTransfer() {
        transferButton.click();
        return this;
    }


}
