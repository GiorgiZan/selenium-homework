import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exceptions {
    WebDriver driver;
    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
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

        //Avoid NoAlertPresentException რომ ეწერა ვიფიქრე რომ try-ს კოდს არ უნდა გაესროლა Exception, თუ არასწორედ მივხდი და აქაც უნდა გაგვესროლა NoAlertPresentException მაშინ უბრალოდ მოვშლით ერთ ხაზს (ქვემოთ მოვნიშნე რომელი) და შემდეგ ჩვენი catch დაიჭერს.
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.alertIsPresent()); //ეს
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
