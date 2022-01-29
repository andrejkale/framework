package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class AbstractPage {
    protected WebDriver driver;

    protected abstract AbstractPage openPage();
    //protected final int WAIT_TIMEOUT_SECONDS = 10;
    protected final Duration WAIT_TIMEOUT_DURATION = Duration.ofSeconds(10);

    protected AbstractPage(WebDriver driver) {
        this.driver = driver;
    }
}
