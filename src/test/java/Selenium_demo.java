import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.Color;
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
        driver.manage().window().maximize();
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


        /*driver.findElement(By.id("usr")).sendKeys("Bug resistance");
        driver.findElement(By.id("pwd")).sendKeys("testabc");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        String actualText = driver.findElement(By.xpath("//h3[contains(text(),'Formal Shoes')]")).getText();
        System.out.println(actualText);
        String expectedText = "Formal Shoes";
        Assert.assertEquals(actualText,expectedText);*/

        String actualBtnColor = driver.findElement(By.id("NewRegistration")).getCssValue("background-color");
        System.out.println(actualBtnColor);
        String hexValue = Color.fromString(actualBtnColor).asHex();
        System.out.println(hexValue);

        String expecetdColor = "#007bff";
        Assert.assertEquals(hexValue,expecetdColor);

        driver.findElement(By.id("NewRegistration")).click();
        //Thread.sleep(2000);

        driver.findElement(By.id("Salutation")).click();
        //Thread.sleep(1000);
        driver.findElement(By.xpath("//option[@value='Mrs']")).click();
        //Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@type='submit']")).click();

        String actualTextSize = driver.findElement(By.xpath("//span[contains(text(),'This field is required')]")).getCssValue("font-size");
        System.out.println(actualTextSize);

        String expectedTextSize = "16px";

        Assert.assertEquals(actualTextSize,expectedTextSize);


        // this is edited from firstbranch
    }



    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
