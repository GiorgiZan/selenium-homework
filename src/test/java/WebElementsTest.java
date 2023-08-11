import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebElementsTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testAddRemoveElements() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addElementButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));

        for (int i = 0; i < 3; i++) {
            addElementButton.click();
        }

        // Print out the last 'Delete' button element with findElement()
        WebElement lastDeleteButton = driver.findElement(By.xpath("//button[text()='Delete'][last()]"));
        System.out.println("Last Delete Button (findElement()): " + lastDeleteButton.getText());

        // Print out the last 'Delete' button with findElements(). Use cssSelector as locator of element with class name , that starts with 'added' word
        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[class^='added']"));
        WebElement lastCssDeleteButton = deleteButtons.get(deleteButtons.size() - 1);
        System.out.println("Last Delete Button (cssSelector): " + lastCssDeleteButton.getText());

        //Print out the last 'Delete' button element with findElement(). Use relative XPath with tag, that can accept any char with class name that contains 'manually' and text() 'Delete'
        WebElement deleteButton = driver.findElement(By.xpath("//*[contains(@class,'manually') and contains(text(),'Delete')][last()]"));
        System.out.println("Last Delete Button (Use relative XPath with tag): " + deleteButton.getText());
    }
    @Test
    public void testChallengingDOM() {
        driver.get("http://the-internet.herokuapp.com/challenging_dom");

        // Using relative XPath, Print out the Lorem value of element, that has 'Apeirian9' as Ipsum value
        WebElement loremElement = driver.findElement(By.xpath("//td[text()='Apeirian9']//preceding-sibling::td[1]"));
        System.out.println("Lorem value of 'Apeirian9': " + loremElement.getText());

        // Using relative XPath, Print out the next element of element with Ipsum value 'Apeirian9'
        WebElement nextElement = driver.findElement(By.xpath("//td[text()='Apeirian9']//following-sibling::td[1]"));
        System.out.println("Next element of 'Apeirian9': " + nextElement.getText());
    }

    @AfterClass
    public void afterClass() {
            driver.quit();
}
    }