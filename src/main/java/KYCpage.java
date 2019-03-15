import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;
import java.util.Random;

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


    public KYCpage fillKKYC1stStep(String company, String site,
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

    public KYCpage selectSector(String listName, String sector) {
        String listLocator = String.format("select[name='sector']", listName);
        String optionLocator = String.format("//*[contains(text(), '%s')]", sector);
        driver.findElement(By.cssSelector(listLocator)).click();
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }

    public KYCpage selectCountry(String listName, String country) {
        String listLocator = String.format("select[name='country']", listName);
        String optionLocator = String.format("//*[contains(text(), '%s')]", country);
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

}
