package page;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import model.User;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.ByteArrayInputStream;

import static driver.DriverSingleton.getDriver;

public class LoginPage extends AbstractPage {
    private final String PAGE_URL = "https://www.i.ua/?_rand";

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

    @Override
    protected void addScreenShot() {
        Allure.addAttachment("Login page",
                new ByteArrayInputStream(((TakesScreenshot) getDriver())
                        .getScreenshotAs(OutputType.BYTES)));
    }
}
