import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddVoting_company_test {
    public WebDriver driver;
    private SignInPage signIn;
    public DashboardPage dashboard;
    private CheckingAsserts message;
    public VotingPage voting;
    private DataOfUser data;

    @BeforeMethod
    public void setUp() {
        data = new DataOfUser(driver);
        System.setProperty("webdriver.chrome.driver", data.webDriverPath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(data.urlCompany);
        signIn = PageFactory.initElements(driver, SignInPage.class);
        dashboard = PageFactory.initElements(driver, DashboardPage.class);
        voting = PageFactory.initElements(driver, VotingPage.class);
        message = PageFactory.initElements(driver, CheckingAsserts.class);
        signIn.typeEmail(data.companyEmailEx)
                .typePassword(data.password)
                .clickLogIn();
        dashboard.clickVotings();

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void addVotingAndSubmit() {
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("VOTING EVENTS"));
        voting.clickAddVoting();
        voting.selectOption("Crowne Plaza Shanghai Parking Lot");
        voting.clickSelect();
        message.isElementLoaded(message.pageTitle);
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("ASSET INFO"));
        voting.clickCreateEvent();
        List<String> errorLabels = Arrays.asList("This field is required",
                "This field is required", "You must add minimum 2 options",
                "This field is required", "This field is required",
                "You must upload at least one file");
        Assert.assertEquals("Incorrect errors", errorLabels, message.getErrors());
        String vote = "Voting - Autotest3";
        voting.fillVotingForm(vote,data.votingDescr);
        voting.typeFromDate("09/28/2019\n");
        voting.typeToDate("12/05/2019");
        voting.clickAddOption();
        voting.inputOption("1","first");
        voting.clickAddOption();
        voting.inputOption("2","second");
        voting.clickAddOption();
        voting.inputOption("3","third");
        voting.removeOption("2");
        voting.clickAddOption();
        voting.inputOption("3","fourth");
        voting.uploadFile("file","Picture6MB.jpg");
        Assert.assertThat(message.errorMessage.getText(), CoreMatchers.containsString("Picture6MB.jpg, max size is 5Mb"));
        voting.uploadFile("file","kity.jpg");
        voting.clickCreateEvent();
        message.isElementLoaded(message.successfulPopuP);
        Assert.assertThat(message.successfulPopuP.getText(), CoreMatchers.containsString("Voting event was added successfully"));
        Assert.assertThat(message.pageTitle.getText(), CoreMatchers.containsString("VOTING EVENTS"));
        voting.findVoting(vote);
        voting.votingIsVisibleAndClick(vote);

    }
}
