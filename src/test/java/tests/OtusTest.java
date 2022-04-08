package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

    public class OtusTest {
        private final Logger logger = (Logger) LogManager.getLogger(OtusTest.class);
        private WebDriver driver;
        private final IServerConfiguration serverConfiguration  = ConfigFactory.create(IServerConfiguration.class);

    @Before
    public void startUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        logger.info("Logger on");
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
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com");

    }
    @Test
    public void seeker() throws InterruptedException {
        driver.get("https://duckduckgo.com");
        driver.findElement(By.id("search_form_input_homepage")).sendKeys("OTUS.ru" + Keys.ENTER);
        driver.findElement(By.cssSelector("div[id='r1-0'] a[class='result__a js-result-title-link']")).click();
        Thread.sleep(3000);
        driver.close();
    }
    @Test
    public void chromeKioskMode() throws InterruptedException {
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.findElement(By.cssSelector("a[title='Category 2']")).click();
        driver.findElement(By.xpath("//a[@class='image-zoom']//div[@class='content-overlay']")).click();
        Thread.sleep(3000);
    }
    @Test
    public void getOtus ()throws InterruptedException{
        driver.manage().window().maximize();
        driver.get(serverConfiguration.url());
        driver.findElement(By.xpath("//button[@class='header2__auth js-open-modal']")).click();
        driver.findElement(By.cssSelector("input[placeholder='Электронная почта'][name='email'][type='text']")).clear();
        driver.findElement(By.cssSelector("input[placeholder='Электронная почта'][name='email'][type='text']")).sendKeys(serverConfiguration.login());
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).clear();
        driver.findElement(By.xpath("//input[@placeholder='Введите пароль']")).sendKeys(serverConfiguration.password());
        driver.findElement(By.xpath("//button[@class='new-button new-button_full new-button_blue new-button_md']")).click();
        Thread.sleep(3000);
//        driver.close();
    }
    @Test
    public void outputIntoLogCookies()throws InterruptedException{
        driver.get(serverConfiguration.url());
        driver.manage().getCookies();
        logger.info("g");
        Thread.sleep(3000);
//        driver.close();

    }
    }








