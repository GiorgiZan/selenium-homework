import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CrossBrowserTest {
    WebDriver driver;

    @BeforeTest
    @Parameters("browser")
    public void setup(String browser) throws Exception{
        if(browser.equalsIgnoreCase("Chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        else if(browser.equalsIgnoreCase("Edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        else {
            throw new Exception("Wrong browser");
        }
    }


    @Test
    public void testHandlingExceptions1() {
        driver.get("https://demoqa.com/alerts");
        WebElement buttonLocator =  driver.findElement( By.id("timerAlertButton"));
        buttonLocator.click();
        try {
            new WebDriverWait(driver,4).until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            System.out.println("TimeoutException INVOKED AND CAUGHT");
        }

        //აქ იყო ძველი კომენტარი
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            System.out.println("NoAlertPresentException CAUGHT");
        }

        try {
            driver.navigate().refresh();
            buttonLocator.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("StaleElementReferenceException INVOKED AND CAUGHT");
        }



    }


    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}



