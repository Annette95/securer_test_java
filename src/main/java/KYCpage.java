import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;


public class KYCpage {

    private WebDriver driver;

    public KYCpage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(id = "companyName")
    private WebElement companyNameField;
    @FindBy(id = "companyWebsite")
    private WebElement companyWebsiteField;
    @FindBy(id = "firstName")
    private WebElement firstNameField;
    @FindBy(id = "lastName")
    private WebElement lastNameField;
    @FindBy(id = "email")
    private WebElement emailField;
    @FindBy(id = "sizeOfOffering")
    private WebElement sizeOfOfferingField;
    @FindBy(id = "yearFounded")
    private WebElement yearFoundedField;
    @FindBy(id = "workersCount")
    private WebElement workersCountField;
    @FindBy(id = "next")
    private WebElement nextButton;

    @FindBy(id = "city")
    private WebElement cityField;
    @FindBy(id = "state")
    private WebElement stateField;
    @FindBy(id = "phone-input")
    private WebElement phoneNumberField;
    @FindBy(css = "input[name='dateOfBirth']")
    private WebElement dateOfBirthField;


    public KYCpage fillKKYC1stStepCompany(String company, String site,
                                          String fname, String lname,
                                          String email, String size, String year,
                                          String workers) {
        companyNameField.sendKeys(company);
        companyWebsiteField.sendKeys(site);
        firstNameField.sendKeys(fname);
        lastNameField.sendKeys(lname);
        emailField.sendKeys(email);
        sizeOfOfferingField.sendKeys(size);
        yearFoundedField.sendKeys(year);
        workersCountField.sendKeys(workers);
        nextButton.click();
        return this;
    }

    public KYCpage selectOption(String listName, String option) {
        String listLocator = String.format("select[name='%s']", listName);
        String optionLocator = String.format("//*[contains(text(), '%s')]", option);
        driver.findElement(By.cssSelector(listLocator)).click();
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }


    public KYCpage clickNext() {
        nextButton.click();
        return this;
    }

    public KYCpage uploadFile(String fileSelector, String fileName) {
        String fileInput = String.format("input[name='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

    public KYCpage fillKKYC1stStepInvestorIndividual(String fName, String lName,
                                           String email, String city,
                                           String state, String phone, String birthday) {
        firstNameField.sendKeys(fName);
        lastNameField.sendKeys(lName);
        emailField.sendKeys(email);
        cityField.sendKeys(city);
        stateField.sendKeys(state);
        phoneNumberField.sendKeys(phone);
        dateOfBirthField.sendKeys(birthday);
        return this;
    }


}

