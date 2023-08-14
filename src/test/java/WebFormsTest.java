import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @Test
    public void testFormElements (){
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");

        //Choose programming language from dropdown and ensure that it was selected. სამივე dropDown-ზე ვქენი
        // აქ შეიძლებოდა firstDropDown.getFirstSelectedOption().isSelected()-ის გამოყენებაც, რომელიც დააბრუნებდა True-ს მაგრამ მე ამ გზით გავაკეთე რადგან დეფოლტად ყველა მონიშნული იყო უკვე. ისე მაინტერესებს რომლის გამოყენება ჯობდა ამ დავალებაში
        Select firstDropDown = new Select(driver.findElement(By.id("dropdowm-menu-1")));
        Select secondDropDown = new Select(driver.findElement(By.id("dropdowm-menu-2")));
        Select thirdDropDown = new Select(driver.findElement(By.id("dropdowm-menu-3")));
        firstDropDown.selectByVisibleText("JAVA");
        secondDropDown.selectByVisibleText("TestNG");
        thirdDropDown.selectByVisibleText("CSS");
        System.out.println("Selected option in first dropdown: " +firstDropDown.getFirstSelectedOption().getText());
        System.out.println("Selected option in second dropdown: " +secondDropDown.getFirstSelectedOption().getText() );
        System.out.println("Selected option in third dropdown: " +thirdDropDown.getFirstSelectedOption().getText() );

        // Click on all unselected checkboxes
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        for (WebElement checkbox : checkboxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }

        // Click on 'Yellow' radio button
        WebElement yellowRadioButton = driver.findElement(By.cssSelector("input[value='yellow']"));
        yellowRadioButton.click();


        // In 'Selected & Disabled' section check that 'Orange' option in dropdown is disabled
        WebElement orangeOption = driver.findElement(By.cssSelector("option[value='orange']"));
        System.out.println("Is orange enabled: " + orangeOption.isEnabled());

        }

    @AfterTest
    public void afterClass() {
        driver.quit();
    }
}
