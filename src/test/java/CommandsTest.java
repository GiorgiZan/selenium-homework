import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CommandsTest {


    @Test
    public void testDynamicControls() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        driver.manage().window().maximize();

        // Enable button-ზე დაჭერა
        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        enableButton.click();

        Thread.sleep(3000);
        // შემოწმება input field-ის და text-ის
        WebElement inputField = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        WebElement enabledText = driver.findElement(By.xpath("//*[@id=\"message\"]"));
        System.out.println(" Is input field enabled : " + inputField.isEnabled());
        System.out.println(" Is 'It's enabled' text displayed: " + enabledText.getText().contains("It's enabled!"));
        // ნახეთო თუ არის იზ დისპლეიდ და რავი ესეც დავწერე ბარემ
        System.out.println(enabledText.isDisplayed());

        // disableButton და enableButton ზუსტად ერთი და იგივე უბრალოდ თვალსაჩინოებისთვის დავწერე
        WebElement disableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));
        System.out.println(" Has button text changed from Enable to Disable: " + disableButton.getText().contains("Disable"));

        // 'Bootcamp'-ის ჩაწერა input field-ში და შემდგომ წაშლა, Threde-ი ჩავამატე რო ნათლად გამოჩენილიყო შედეგი
        inputField.sendKeys("Bootcamp");
        Thread.sleep(1000);
        inputField.clear();
        Thread.sleep(1000);
        driver.quit();
    }

    @Test
    public void testDragAndDrop() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        driver.manage().window().maximize();


        WebElement columnA = driver.findElement(By.id("column-a"));
        WebElement columnB = driver.findElement(By.id("column-b"));


        //ორი გზით გავაკეთე მაინც, ყოველი შემთხვევისთვის, assert-ი თან ამოწმებს ხოლო პრინტით გამომაქვს უბრალოდ True არის თუ False
        Assert.assertEquals(columnA.getLocation().getY(), columnB.getLocation().getY());
        System.out.println(columnA.getLocation().getY() == columnB.getLocation().getY());
        Thread.sleep(1000);
        driver.quit();
    }

}
