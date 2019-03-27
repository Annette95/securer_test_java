import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

public class VotingPage {
    private WebDriver driver;

    public VotingPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(text(), 'Create New Voting Event')]")
    private WebElement addVotingButton;

    @FindBy(xpath = "//*[@class ='btn btn-primary' and contains(text(), 'Select')]")
    private WebElement selectButton;

    @FindBy(id = "title")
    private WebElement titleInput;

    @FindBy(id = "description")
    private WebElement descriptionInput;

    @FindBy(css = "input[name='fromDate']")
    private WebElement fromDateInput;

    @FindBy(css = "input[name='toDate']")
    private WebElement toDateInput;

    @FindBy(css = "button[aria-label='add-option']")
    private WebElement addOptionButton;

    @FindBy(xpath = "//*[@class ='button-content' and contains(text(), 'Create Event')]")
    private WebElement createVotingButton;

    @FindBy(css = "input[placeholder='Filter by']")
    private WebElement filterInput;

    @FindBy(xpath = "//*[@type ='button' and contains(text(), 'Submit')]")
    private WebElement submitButton;

    public VotingPage clickAddVoting(){
        addVotingButton.click();
        return this;
    }

    public VotingPage clickSelect() {
        selectButton.click();
        return this;
    }

    public VotingPage selectOption(String option) {
        String optionLocator = String.format("//select//option[text() = '%s']", option);
        driver.findElement(By.xpath(optionLocator)).click();
        return this;
    }

    public VotingPage fillVotingForm(String title, String description) {
        titleInput.sendKeys(title);
        descriptionInput.sendKeys(description);
        return this;
    }

    public VotingPage typeFromDate(String fromDate) {
        fromDateInput.sendKeys(fromDate);
        return this;
    }

    public VotingPage typeToDate(String toDate) {
        toDateInput.sendKeys(toDate);
        return this;
    }

    public VotingPage clickAddOption(){
        addOptionButton.click();
        return this;
    }

    public VotingPage inputOption(String option,String name){
        String optionLocator = String.format("option-%s", option);
        driver.findElement(By.id(optionLocator)).sendKeys(name);
        return this;
    }

    public VotingPage removeOption(String option){
        String optionLoc = String.format("//div[%s]/div/div/div/span",option);
        driver.findElement(By.xpath(optionLoc)).click();
        return this;
    }

    public VotingPage uploadFile(String fileSelector, String fileName) {
        String fileInput = String.format("input[type='%s']", fileSelector);
        driver.findElement(By.cssSelector(fileInput)).sendKeys(new File("src/main/java/imageHelpers/" + fileName).getAbsolutePath());
        return this;
    }

    public VotingPage clickCreateEvent(){
        createVotingButton.click();
        return this;
    }

    public VotingPage findVoting(String voting) {
        filterInput.sendKeys(voting);
        filterInput.sendKeys(Keys.ENTER);
        return this;
    }


    public VotingPage votingIsVisibleAndClick(String voting) {
        String dividendCard = String.format("//*[contains(text(), '%s')]", voting);
        driver.findElement(By.xpath(dividendCard)).click();
        return this;
    }

    public VotingPage selectOptionInVoting(){
        String optionVote = String.format("//label[@class='disabled form-check-label']");
        driver.findElement(By.xpath(optionVote)).click();
        return this;
    }

    public VotingPage clickSubmit(){
        submitButton.click();
        return this;
    }
}
