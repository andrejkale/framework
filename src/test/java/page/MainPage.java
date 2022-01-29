package page;

import io.github.bonigarcia.wdm.WebDriverManager;
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

import java.io.UnsupportedEncodingException;
import java.util.List;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://mbox2.i.ua/?_rand";

	private final By userNameLocator = By.xpath("//span[@class='user_name']");

	@FindBy(className="make_message")
	private WebElement makeMessage;

	@FindBy(xpath="//span[@class='sbj']/span")
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
    public  String getUserNameFromPage() {
        WebElement userName = new WebDriverWait(driver, WAIT_TIMEOUT_DURATION)
                .until(ExpectedConditions.presenceOfElementLocated(userNameLocator));
        String userNameFormWeb = userName.getText();
        logger.info("User name is " + userNameFormWeb);
        return userNameFormWeb;
    }

    @Step("User clicks on link Create mail")
    public MailPage createNewMail() {
        logger.info("User clicks on link Create mail");
        makeMessage.click();
        return new MailPage(driver);
    }

    @Step("Return email's subject")
    public String getLastMailSubject(){
        String subject = subjectsOfEmailList.get(0).getText();
        logger.info("Subject is :" + subject);
        return subject;
    }
}
