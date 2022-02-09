package test;

import model.User;
import org.testng.annotations.Test;
import page.LoginPage;
import service.UserCreator;

public class UserLoginWithWrongCredentials extends CommonConditions {
    @Test
    public void loginWithWrongPassword() {
        User testUser = UserCreator.withWrongPassword();
        LoginPage loginPage = new LoginPage(driver)
                .openPage()
                .loginWithWrongLoginOrPassword(testUser);
        loginPage.checkAlertMessageIsDisplayed();

    }

    @Test
    public void loginWithWrongUserName() {
        User testUser = UserCreator.withWrongUsername();
        LoginPage loginPage = new LoginPage(driver)
                .openPage()
                .loginWithWrongLoginOrPassword(testUser);
        loginPage.checkAlertMessageIsDisplayed();

    }
}
