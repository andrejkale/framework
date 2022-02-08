package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.util.List;

import static driver.DriverSingleton.getDriver;
import static org.testng.Assert.assertEquals;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://mbox2.i.ua/?_rand";

    private final By userNameLocator = By.xpath("//span[@class='user_name']");

    @FindBy(className = "make_message")
    private WebElement makeMessage;

    @FindBy(xpath = "//span[@class='sbj']/span")
    private List<WebElement> subjectsOfEmailList;


    protected MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    protected MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    @Step("Return user name from web page")
    public String getUserNameFromPage() {
        WebElement userName = new WebDriverWait(driver, WAIT_TIMEOUT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(userNameLocator));
        String userNameFormWeb = userName.getText();
        logger.info("User name is " + userNameFormWeb);
        return userNameFormWeb;
    }

    @Step("User clicks on link Create mail")
    public MailPage createNewMail() {
        logger.info("User clicks on link Create mail");
        addScreenShot();
        makeMessage.click();
        return new MailPage(driver);
    }

    @Step("Return email's subject")
    public String getLastMailSubject() {
        String subject = subjectsOfEmailList.get(0).getText();
        addScreenShot();
        logger.info("Subject is :" + subject);
        return subject;
    }

    @Step("Compare username {0} on Main page with username {1} from testData")
    public void verifyLoginUserName(String userNameFromPage, String userNameFromTestData) {
        addScreenShot();
        assertEquals(userNameFromPage, userNameFromTestData);
    }

    @Step("Compare testSubject {0} on Main page with testSubject {1} from testData")
    public void verifyMailSubject(String mailSubjectFromPage, String mailSubjectFromTestData) {
        addScreenShot();
        assertEquals(mailSubjectFromPage, mailSubjectFromTestData);
    }

    @Override
    protected void addScreenShot() {
        Allure.addAttachment("Main page",
                new ByteArrayInputStream(((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES)));
    }
}
