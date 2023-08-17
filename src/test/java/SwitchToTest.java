import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SwitchToTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testIframe(){
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));

        WebElement textField = driver.findElement(By.xpath("//*[@id=\"tinymce\"]/p"));
        textField.clear();
        textField.sendKeys("Here Goes");

        driver.switchTo().defaultContent();
        WebElement alignCenterButton = driver.findElement(By.cssSelector("button[title='Align center']"));
        alignCenterButton.click();

    }

    @Test
    public void testAlerts() {
        driver.get("https://demoqa.com/alerts");

        WebElement alertButton =  driver.findElement(By.id("alertButton"));
        alertButton.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
