import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class JSexecutor {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // ამითაც მუშაობს ყველაფერი გარდა moveToElement-ისა
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
//        driver = new ChromeDriver(options);

        //აქ HtmlUnitDriver გამოვიყენე, რომ moveToElement-ზე იმუშავოს (მადლობა მირიანს სლაკში რჩევისთვის)
        driver = new HtmlUnitDriver(true);

    }

    @Test
    public void testToDoList(){
        driver.get("http://webdriveruniversity.com/To-Do-List/index.html");

        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement practiceMagicDel = driver.findElement(By.xpath("//*[@id=\"container\"]/ul/li[3]/span"));

        actions.moveToElement(practiceMagicDel).perform();

        js.executeScript("arguments[0].click();", practiceMagicDel);

    }

    @Test
    public void testScrolling(){
        driver.get("http://webdriveruniversity.com/Scrolling/index.html");

        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement leftBoxLocator = driver.findElement( By.xpath("//*[@id=\"zone2\"]"));
        js.executeScript("arguments[0].scrollIntoView();",leftBoxLocator);

        actions.moveToElement(leftBoxLocator).perform();

        String expectedText = "1 Entries";

        //ორი გზით გავაკეთე მაინც, ყოველი შემთხვევისთვის, assert-ი თან ამოწმებს ხოლო პრინტით გამომაქვს უბრალოდ True არის თუ False
        System.out.println(expectedText.equals(leftBoxLocator.getText()));
        Assert.assertEquals(leftBoxLocator.getText(), expectedText );



    }
    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
