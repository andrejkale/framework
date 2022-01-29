package page;

import io.qameta.allure.Step;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {
    private final String PAGE_URL = "https://www.i.ua/?_rand";

	@FindBy(xpath="//input[@name='login']")
	private WebElement loginInput;

	@FindBy(xpath="//input[@name='pass']")
	private WebElement passwordInput;

	@FindBy(xpath="//form[@name='lform']//input[@type='submit']")
	private WebElement buttonSubmit;



    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    @Step("Open Login page")
    public LoginPage openPage() {
        driver.navigate().to(PAGE_URL);
        logger.info("Login page is opened");
        return this;
    }

    @Step("User enters login and password")
    public MainPage login(User user) {
        logger.info("User enters login and password");
        loginInput.clear();
        loginInput.sendKeys(user.getUsername());
        passwordInput.clear();
        passwordInput.sendKeys(user.getPassword());
        logger.info("User clicks on submit button");
        buttonSubmit.click();
        return new MainPage(driver);
    }
}
