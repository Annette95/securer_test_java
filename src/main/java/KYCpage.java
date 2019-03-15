import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class KYCpage {

    private WebDriver driver;

    public KYCpage(WebDriver driver) {
        this.driver = driver;
    }

    private By companyNameField = By.id("companyName");
    private By companyWebsiteField = By.id("companyWebsite");
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("email");
    private By sizeOfOfferingField = By.id("sizeOfOffering");
    private By yearFoundedField = By.id("yearFounded");
    private By workersCountField = By.id("workersCount");
    private By nextButton = By.id("next");


    public KYCpage fillKKYC1stStep(String company, String site, String fname, String lname, String email, String size, String year, String workers) {
        driver.findElement(companyNameField).sendKeys(company);
        driver.findElement(companyWebsiteField).sendKeys(site);
        driver.findElement(firstNameField).sendKeys(fname);
        driver.findElement(lastNameField).sendKeys(lname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(sizeOfOfferingField).sendKeys(size);
        driver.findElement(yearFoundedField).sendKeys(year);
        driver.findElement(workersCountField).sendKeys(workers);
        driver.findElement(nextButton).click();
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
        driver.findElement(nextButton).click();
        return this;
    }

    public KYCpage uploadFile(String fileSelector, String fileName) {
        String fileInput = String.format("input[name='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

}
