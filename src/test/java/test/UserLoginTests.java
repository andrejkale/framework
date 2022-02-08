package test;

import model.TestData;
import model.User;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import service.TestDataCreator;
import service.UserCreator;

public class UserLoginTests extends CommonConditions {
    @Test
    public void canLogin() {
        User testUser = UserCreator.withCredentialsFromProperty();
        TestData testData = TestDataCreator.fromProperty();
        MainPage loginPage = new LoginPage(driver)
                .openPage()
                .login(testUser);
        loginPage.verifyLoginUserName(loginPage.getUserNameFromPage(), testData.getUserNameForComparing());
    }
}
