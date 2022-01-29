package page;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailPage extends AbstractPage {
    private final Logger logger = LogManager.getRootLogger();
    private final String PAGE_URL = "https://mbox2.i.ua/compose";
    private final By inboxMailLocator = By.xpath("//a[contains(@href, 'INBOX')]");

	@FindBy(name="send")
	private WebElement sendButton;

	@FindBy(id="to")
	private WebElement toInput;

	@FindBy(name="subject")
	private WebElement subjectInput;

	@FindBy(id="text")
	private WebElement textInput;




    @Override
    protected MailPage openPage() {
        driver.navigate().to(PAGE_URL);
        return this;
    }

    MailPage(WebDriver driver){
	    super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Step("User enters email, User enters subject, User enters mail text")
    public MailPage createAndSendMail(String email, String subject, String text){
        logger.info("User enters email :" + email);
        toInput.sendKeys(email);
        logger.info("User enters subject :" + subject);
        subjectInput.sendKeys(subject);
        logger.info("User enters mail text :" + text);
        textInput.sendKeys(text);
        sendButton.click();
        return this;
    }

    @Step("User opens inbox page")
    public MainPage returnToInboxMail(){
        WebElement inboxMail = new WebDriverWait(driver, WAIT_TIMEOUT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(inboxMailLocator));
        logger.info("User opens inbox page");
        inboxMail.click();
        return new MainPage(driver);
    }

}
