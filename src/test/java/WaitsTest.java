import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WaitsTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void testProgressBar(){
        driver.get("https://demoqa.com/progress-bar");

        WebElement startButton = driver.findElement(By.id("startStopButton"));
        startButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='100%']")));
        WebElement progressBar = driver.findElement(By.id("progressBar"));
        System.out.println("Progress: " + progressBar.getText());
    }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
