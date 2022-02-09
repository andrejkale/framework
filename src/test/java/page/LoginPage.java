package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;

import static driver.DriverSingleton.getDriver;
import static org.testng.Assert.assertTrue;

public class LoginPage extends AbstractPage {
    private final String PAGE_URL = "https://www.i.ua/?_rand";
    private final By alertMessageLocator = By.xpath("//*[@id='lform_errCtrl']");

    @FindBy(xpath = "//input[@name='login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@name='pass']")
    private WebElement passwordInput;

    @FindBy(xpath = "//form[@name='lform']//input[@type='submit']")
    private WebElement buttonSubmit;


    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    @Step("Open Login page")
    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        addScreenShot();
        logger.info("Login page is opened");
        return this;
    }

    @Step("Type {user.username} / {user.password} and click on submit button.")
    public MainPage login(User user) {
        logger.info("User enters login and password");
        loginInput.clear();
        loginInput.sendKeys(user.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(user.getPassword());
        addScreenShot();
        logger.info("User clicks on submit button");
        buttonSubmit.click();
        return new MainPage(driver);
    }

    @Step("Type {user.username} / {user.password} and click on submit button.")
    public LoginPage loginWithWrongLoginOrPassword(User user) {
        logger.info("User enters login and password");
        loginInput.clear();
        loginInput.sendKeys(user.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(user.getPassword());
        logger.info("User clicks on submit button");
        buttonSubmit.click();
        addScreenShot();
        return this;
    }
    @Step("Check alert message is displayed")
    public void checkAlertMessageIsDisplayed() {
        WebElement alertMessage = new WebDriverWait(driver, WAIT_TIMEOUT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(alertMessageLocator));
        logger.info("Check alert message is displayed");
        addScreenShot();
        assertTrue(alertMessage.isDisplayed());
    }

    @Override
    protected void addScreenShot() {
        Allure.addAttachment("Login page",
                new ByteArrayInputStream(((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES)));
    }
}
