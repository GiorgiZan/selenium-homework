import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AutoComplete {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    @Test
    public void testAutoComplete() {
        driver.get("https://www.google.com/");

        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"APjFqb\"]"));
        searchBox.sendKeys("Automation");


        WebDriverWait wait = new WebDriverWait(driver,10);
        List<WebElement> suggestions = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']/li")));

        for (WebElement suggestion : suggestions) {
            System.out.println(suggestion.getText());
        }


        WebElement lastSuggestion = suggestions.get(suggestions.size() - 1);
        System.out.println("Clicked on last suggestion: "+ lastSuggestion.getText());
        lastSuggestion.click();


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

