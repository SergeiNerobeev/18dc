package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;


public class OtusTest {
  private final Logger logger = (Logger) LogManager.getLogger(OtusTest.class);
  private WebDriver driver;
  private final IServerConfiguration serverConfiguration = ConfigFactory.create(IServerConfiguration.class);

  private void creatorObjectDriver(ChromeOptions chromeOptions) {
    driver = new ChromeDriver(chromeOptions);
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    logger.info("Logger on");
  }

  @Before
  public void startUp() {
    WebDriverManager.chromedriver().setup();
  }

  @After
  public void end() {
    if (driver != null)
        driver.quit();
  }

  @Test
  public void headlessMode() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("headless");
    creatorObjectDriver(chromeOptions);
    driver.get("https://google.com");
    WebElement logoGoggle = driver.findElement(By.cssSelector("img[alt=Google]"));
    System.out.println(logoGoggle.isEnabled());
    Assert.assertTrue("Google",logoGoggle.isDisplayed());

  }

  @Test
  public void seeker() {
    ChromeOptions chromeOptions = new ChromeOptions();
    creatorObjectDriver(chromeOptions);
    driver.get("https://duckduckgo.com");
    driver.findElement(By.id("search_form_input_homepage")).sendKeys("Отус.ru" + Keys.ENTER);
    WebElement aboutOtus = driver.findElement(By.cssSelector("[id='r1-0']"));
    aboutOtus.isDisplayed();
    Assert.assertTrue("Otus", aboutOtus.isDisplayed());
  }

  @Test
  public void chromeKioskMode() {
    ChromeOptions chromeOptions = new ChromeOptions();
    chromeOptions.addArguments("--kiosk");
    creatorObjectDriver(chromeOptions);
    driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
    JavascriptExecutor jse = (JavascriptExecutor)driver;
    jse.executeScript("window.scrollBy(0,250)");
    driver.findElement(By.xpath("//li[@data-id='id-3']")).click();
    driver.findElement(By.xpath("//div[@style='height: 640px; width: 640px;']")).click();
    Assert.assertTrue("'height: 640px; width: 640px;'",true);
  }

  @Test
  public void getOtus() {
    driver = new ChromeDriver();
    driver.manage().window().maximize();

    driver.get(serverConfiguration.url());

    By emailFieldLocator = By.xpath("//input[@type='text'][contains(@class,'email')]");
    driver.findElement(By.xpath("//button[contains(@data-modal-id, 'new-log-reg')]")).click();

    WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    webDriverWait.until(ExpectedConditions.presenceOfElementLocated(emailFieldLocator));

    WebElement webLog = driver.findElement(emailFieldLocator);
    webLog.sendKeys(serverConfiguration.login());
    Assert.assertEquals("Logging of user doesn't correct", serverConfiguration.login(), webLog.getAttribute("value"));

    Set<Cookie> cookies = driver.manage().getCookies();
    for(Cookie cookie: cookies) {
      logger.info(cookie.getName() + " : " + cookie.getValue());
    }

  }

}








