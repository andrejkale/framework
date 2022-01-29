package test;

import model.TestData;
import model.User;
import org.testng.annotations.Test;
import page.LoginPage;
import page.MainPage;
import service.TestDataCreator;
import service.UserCreator;


import static org.testng.Assert.assertEquals;

public class CreateMailTest extends CommonConditions {
    @Test
    public void canCreateEmail() {
        User testUser = UserCreator.withCredentialsFromProperty();
        TestData testData = TestDataCreator.emailFromProperty();
        MainPage mailPage = new LoginPage(driver)
                .openPage()
                .login(testUser)
                .createNewMail()
                .createAndSendMail(testData.getTestEmail(), testData.getTestSubject(), testData.getTestEmailText())
                .returnToInboxMail();
        assertEquals(testData.getTestSubject(), mailPage.getLastMailSubject());
    }
}


