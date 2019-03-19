import org.openqa.selenium.WebDriver;

public class DataOfUser {

    private WebDriver driver;

    public DataOfUser(WebDriver driver) {
        this.driver = driver;
    }

    String urlCompany = "https://dev-company.securer.io";
    String urlInvestor = "https://dev-investor.securer.io";
    String companyEmail = "company@example.com";
    String investorEmail = "investor@example.com";
    String adminEmail = "admin@example.com";
    String password = "qwe123";

}

