import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;


public class CheckingAsserts {
    private WebDriver driver;

    public CheckingAsserts(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "div.toastr.animated.rrt-success")
    public WebElement successfulPopuP;

    @FindBy(css = "div.toastr.animated.rrt-error")
    public WebElement errorPopUp;

    @FindBy(css = "div.invalid-feedback")
    public WebElement errorMessage;

    @FindBy(css = "h6.element-header.text-bold-500")
    public WebElement pageTitle;

    public By errorTexts = By.cssSelector("div.invalid-feedback");

    private String errorByText = "//*[text()='%s']";


    public WebElement isElementLoaded(WebElement elementToBeLoaded) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        WebElement element = wait.until(ExpectedConditions.visibilityOf(elementToBeLoaded));
        return element;
    }

    public List<String> getErrors() {
        List<WebElement> elements = driver.findElements(errorTexts);

        List<String> errorMessages = new ArrayList<String>();
        for (WebElement element : elements) {
            if (!element.getText().isEmpty()) {
                errorMessages.add(element.getText());
            }
        }
        return errorMessages;
    }

    public boolean isErrorVisible(String message) {
        return driver.findElements(By.xpath(format(errorByText, message))).size() > 0;
    }

    public String[] getErrorLabels() {
        return (String[]) getErrors().toArray();
    }

}
