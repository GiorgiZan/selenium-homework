import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebTablesTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testWebTable() {
        driver.get("http://techcanvass.com/Examples/webtable.html");
        WebElement webTable = driver.findElement(By.id("t01"));
        List<WebElement> rows = webTable.findElements(By.tagName("tr"));


        //1-დან რო ჰედერები გამოვტოვოთ, კომპანი ნამე და ეგენი
        for (int num = 1; num < rows.size(); num++) {
            List<WebElement> columns = rows.get(num).findElements(By.tagName("td"));
            //როგორც სლაკში იყო მოთხოვნილი, ყველაზე დინამუირად როგორც შევძელი ეგრე ვქენი. if რომ წავშალოთ და დავწეროთ System.out.println( columns.get(column).getText()) გამოიტანს ყველა სვეტს (მუშაობს ყველა ზომის table-ზე)
            for (int column = 0; column < columns.size(); column++) {
                if ( columns.get(column).getText().equals("Honda")) {
                    String hondasPrice = columns.get(column + 2).getText();
                    System.out.println("Price of Honda: " + hondasPrice);
                }
            }
        }


    }
    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
