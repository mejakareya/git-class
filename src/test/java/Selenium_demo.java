import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Selenium_demo {

    public static WebDriver driver;


    @BeforeTest
    public void setup(){

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        //driver.get("https://www.google.com/");
        driver.get("https://anupdamoda.github.io/AceOnlineShoePortal/index.html");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // before V4
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // after V4
        //250ms


    }



    @Test
    public void test() throws InterruptedException {

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"menu\"]/a[2]/li")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"menu\"]/a[2]/li")));
        //Thread.sleep(1000);
        //driver.findElement(By.xpath("//li[contains(text(),'Sign In Portal')]")).click();
        driver.findElement(By.xpath("//*[@id=\"menu\"]/a[2]/li")).click();
        driver.findElement(By.id("usr")).sendKeys("Bug resistance");
        driver.findElement(By.id("pwd")).sendKeys("testabc");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.xpath("//h3[contains(text(),'Formal Shoes')]")).getText();
        System.out.println(actualText);
        String expectedText = "Formal Shoes";
        Assert.assertEquals(actualText,expectedText);

        // this is edited from firstbranch
    }



    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
