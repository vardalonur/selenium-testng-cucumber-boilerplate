package stepDefinitions;


import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.LoginPage;

public class LoginSteps {

    private LoginPage loginPage;

    public LoginSteps() {
        loginPage = new LoginPage();
    }

}
