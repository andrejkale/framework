package test;

import model.TestData;
import model.User;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import service.TestDataCreator;
import service.UserCreator;

import java.io.UnsupportedEncodingException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserLoginTests extends CommonConditions{
    @Test
    public void canLogin() {
        User testUser = UserCreator.withCredentialsFromProperty();
        TestData testData = TestDataCreator.fromProperty();
        MainPage loginPage = new LoginPage(driver)
                .openPage()
                .login(testUser);
        assertThat(loginPage.getUserNameFromPage(), is(equalTo(testData.getUserNameForComparing()
        )));
    }
}
