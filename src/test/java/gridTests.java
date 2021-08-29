import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class gridTests {
    private WebDriver driver;
    @BeforeClass
    public static void setupClass() {
        WebDriverManager.firefoxdriver().setup();
    }

    @Before
    public void setupTest() {
        driver = new FirefoxDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addForecast() {
       driver.get("https://localhost:44351");
        WebElement gridTabButton = driver.findElement(By.cssSelector("a[href='/grid']"));
        gridTabButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[class='k-button telerik-blazor k-button-icontext k-primary']")));
        WebElement addForecastButton = driver.findElement(By.cssSelector("button[class='k-button telerik-blazor k-button-icontext k-primary']"));
        addForecastButton.click();
        assertTrue(driver.findElement(By.cssSelector("button[class='k-button telerik-blazor k-button-icontext'] span[class='k-icon k-i-save ']")).isDisplayed());
        driver.findElement(By.cssSelector("input[class='k-textbox k-state-valid telerik-blazor']")).sendKeys("Hell");
        driver.findElement(By.cssSelector("button[class='k-button telerik-blazor k-button-icontext'] span[class='k-icon k-i-save ']")).click();
        assertEquals("Hell", driver.findElement(By.xpath("//tr[@class='k-master-row  '][1]/td[@data-col-index='4']")).getText());

    }

    @Test
    public void deleteForecast(){
        driver.get("https://localhost:44351");
        WebElement gridTabButton = driver.findElement(By.cssSelector("a[href='/grid']"));
        gridTabButton.click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@class='k-master-row  '][1]//td[@class='k-command-cell   '][1]//button[@class='k-button telerik-blazor k-button-icontext'][1]")));
        driver.findElement(By.xpath("//tr[@class='k-master-row  '][1]//td[@class='k-command-cell   '][1]//button[@class='k-button telerik-blazor k-button-icontext'][1]")).click();
        assertEquals("Tuesday, 31 Aug 2021", driver.findElement(By.xpath("//tr[@class='k-master-row  '][1]/td[@data-col-index='1']")).getText());
    }
}
