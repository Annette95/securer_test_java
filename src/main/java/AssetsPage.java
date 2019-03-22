import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.io.File;

public class AssetsPage {
    private WebDriver driver;

    public AssetsPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "button[aria-label='add-asset']")
    private WebElement buttonAddAsset;

    @FindBy(id = "name")
    private WebElement inputName;

    @FindBy(id = "number")
    private WebElement inputNumber;

    @FindBy(id = "type")
    private WebElement inputType;

    @FindBy(id = "noOfSharesIssued")
    private WebElement inputSharesIssues;

    @FindBy(id = "address")
    private WebElement inputAddress;

    @FindBy(id = "city")
    private WebElement inputCity;

    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "description")
    private WebElement descrField;

    @FindBy(id = "details")
    private WebElement detailDescrField;

    @FindBy(css = "button[aria-label='add-new-document']")
    private WebElement buttonAddNewDoc;

    @FindBy(id = "title")
    private WebElement titleField;

    @FindBy(css = "button[aria-label='save']")
    private WebElement saveButton;

    @FindBy(xpath = "//*[contains(@class,'mr-2 btn btn-primary') and contains(text(), 'Tokenize')]")
    private WebElement tokenizeButton;

    @FindBy(id = "tokenName")
    private WebElement tokenNameField;

    @FindBy(id = "tokenSymbol")
    private WebElement tokenSymbolField;

    @FindBy(id = "percentage")
    private WebElement percentageField;

    @FindBy(id = "fiatValue")
    private WebElement fiatValueField;

    @FindBy(id = "totalSupply")
    private WebElement totalSupplyField;

    @FindBy(css = "button[aria-label='new-partition']")
    private WebElement addNewPartiton;

    @FindBy(css = "input[name='partitionName']")
    private WebElement namePartititonInput;

    @FindBy(xpath = "//*[contains(text(), 'Next')]")
    public WebElement nextPageButton;

    @FindBy(css = "button[aria-label='tokenize']")
    private WebElement tokenizeSubmit;

    @FindBy(css = "button[aria-label='get-whitelisted']")
    private WebElement getWhiteListedSubmit;

    @FindBy(className = "btn-success") //xpath = "//*[contains(text(), 'Yes')]"
    private WebElement yesButton;

    @FindBy(css = "button[value='approved']")
    public WebElement approveButton;

    @FindBy(css = "a[aria-label='get-whitelisted']")
    public WebElement investButton;

    @FindBy(css = "input[name='amount']")
    private WebElement amountField;

    public AssetsPage clickAddAsset() {
        buttonAddAsset.click();
        return this;
    }

    public AssetsPage fillEntityDetails(String name, String no, String type, String sharesIss, String address, String city) {
        inputName.sendKeys(name);
        inputNumber.sendKeys(no);
        inputType.sendKeys(type);
        inputSharesIssues.sendKeys(sharesIss);
        inputAddress.sendKeys(address);
        inputCity.sendKeys(city);
        return this;
    }

    public AssetsPage clickNext() {
        nextButton.click();
        return this;
    }

    public AssetsPage selectOption(String listName, String option) {
        String listLocator = String.format("select[name='%s']", listName);
        driver.findElement(By.cssSelector(listLocator)).click();
        String optionLocator = String.format("//select[@name = '%s']/option[@value = '%s']", listName, option);
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }


    public AssetsPage introduceDescription(String descr) {
        descrField.sendKeys(descr);
        return this;
    }

    public AssetsPage introduceDetailedDescription(String detDescr) {
        detailDescrField.sendKeys(detDescr);
        return this;
    }

    public AssetsPage uploadFile(String fileSelector, String fileName) {
        String fileInput = String.format("input[name='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

    public AssetsPage uploadDoc(String fileSelector, String fileName) {
        String fileInput = String.format("input[type='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

    public AssetsPage clickAddNewDoc() {
        buttonAddNewDoc.click();
        return this;
    }

    public AssetsPage typeTitle(String title) {
        titleField.sendKeys(title);
        return this;
    }

    public AssetsPage clickaSave() {
        saveButton.click();
        return this;
    }

    public AssetsPage clickDelete(String delete) {
        String optionLocator = String.format("button[aria-label='%s']", delete);
        driver.findElement(By.cssSelector(optionLocator)).click();
        return this;
    }

    public AssetsPage clickTokenize() {
        tokenizeButton.click();
        return this;
    }

    public AssetsPage fillTokenizeForm(String name, String symbol, String percent, String fiat, String tokens) {
        tokenNameField.sendKeys(name);
        tokenSymbolField.sendKeys(symbol);
        percentageField.sendKeys(percent);
        fiatValueField.sendKeys(fiat);
        totalSupplyField.sendKeys(tokens);
        return this;
    }

    public AssetsPage clickOnAsset(String asset) {
        String assetDetails = String.format("//h6/div[contains(text(),'%s')]", asset);
        driver.findElement(By.xpath(assetDetails)).click();
        return this;
    }


    public AssetsPage clickNextPage() {
        nextPageButton.click();
        return this;
    }

    public AssetsPage clickAddNewPartition() {
        addNewPartiton.click();
        return this;
    }

    public AssetsPage typePatrition(String part) {
        namePartititonInput.sendKeys(part);
        return this;
    }

    public AssetsPage submitTokenization() {
        tokenizeSubmit.click();
        return this;
    }

    public AssetsPage submitGetWhiteListed() {
        getWhiteListedSubmit.click();
        return this;
    }

    public AssetsPage clickYes() {
        yesButton.click();
        return this;
    }

    public AssetsPage clickKYCInvestor(String kyc) {
        String KYCdetails = String.format("svg[aria-label='%s-kyc']", kyc);
        driver.findElement(By.cssSelector(KYCdetails)).click();
        return this;
    }

    public AssetsPage clickApprove() {
        approveButton.click();
        return this;
    }

    public AssetsPage clickInvest() {
        investButton.click();
        return this;
    }

    public AssetsPage clickRequestInvest() {
        String requestInvest = String.format("//button[@class = 'btn btn-primary' and contains(text(),'Request Invest')]");
        driver.findElement(By.xpath(requestInvest)).click();
        return this;
    }

    public AssetsPage inputAmount(String amount){
        amountField.sendKeys(Keys.BACK_SPACE);
        amountField.sendKeys(amount);
        return this;
    }

    public AssetsPage clickRequest() {
        String request = String.format("//button[@type = 'submit' and contains(text(),'Request')]");
        driver.findElement(By.xpath(request)).click();
        return this;
    }


}
