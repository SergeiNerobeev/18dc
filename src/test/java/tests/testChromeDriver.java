package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class testChromeDriver {
    private Logger logger = (Logger) LogManager.getLogger(testChromeDriver.class);
    WebDriver driver;
    private IServerConfiguration serverConfiguration  = ConfigFactory.create(IServerConfiguration.class);

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Driver is Up!");
    }
    @After
    public void close(){
        if(driver != null)
            driver.close();
    }

    @Test
    public void testLog() {
        logger.warn("I am warning");
        logger.info("I am info");
    }
    @Test
    public void openPageOtus(){
        driver.get("http://otus.ru");
    }
}
