import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CalculatorPage {
    private WebDriver driver;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "input[name='amount']")
    public WebElement amountField;

    @FindBy(css = "input[name='tokens']")
    public WebElement tokensField;

    @FindBy(css = "button[aria-label='invest']")
    private WebElement investButton;

    @FindBy(css = "button[aria-label='okey']")
    private WebElement okayButton;


    public CalculatorPage typeAmount(String amount){
        amountField.clear();
        amountField.sendKeys(amount);
        return this;
    }

    public CalculatorPage typeToken(String token){
        tokensField.clear();
        tokensField.sendKeys(token);
        return this;
    }

    public CalculatorPage selectOption(String listName, String option) {
        String listLocator = String.format("select[name='%s']", listName);
        driver.findElement(By.cssSelector(listLocator)).click();
        String optionLocator = String.format("//select[@name = '%s']/option[@value = '%s']", listName, option);
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }

    public CalculatorPage clickInvest(){
        investButton.click();
        return this;
    }

    public CalculatorPage clickOkay(){
        okayButton.click();
        return this;
    }

}
