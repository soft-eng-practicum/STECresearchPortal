import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class STECTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUpChrome() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Austin\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void subjectTest() throws Exception {
        driver.get("https://etc.cardboard.software/stec");
        WebElement subject = driver.findElement(By.name("subject"));

        Select selectSubject = new Select(subject);

        selectSubject.selectByValue("Bio");

        WebElement visibility = driver.findElement(By.id("list--Bio"));

        String block = visibility.getCssValue("display");

        visibility = driver.findElement(By.id("list--Che"));

        String none = visibility.getCssValue("display");

        Assert.assertEquals("none", block);
        Assert.assertEquals("none", none);

        selectSubject.selectByValue("any-subject");

        visibility = driver.findElement(By.id("list--Bio"));

        block = visibility.getCssValue("display");

        visibility = driver.findElement(By.id("list--Che"));

        none = visibility.getCssValue("display");

        Assert.assertEquals("block", block);
        Assert.assertEquals("block", none);

        selectSubject.selectByValue("Che");

        visibility = driver.findElement(By.id("list--Bio"));

        block = visibility.getCssValue("display");

        visibility = driver.findElement(By.id("list--Che"));

        none = visibility.getCssValue("display");

        Assert.assertEquals("none", block);
        Assert.assertEquals("block", none);


    }

    @Test
    public void professorTest() throws Exception {
        driver.get("file:///C:/Users/Austin/Desktop/STECresearchPortal/app/index.html");
        WebElement prof = driver.findElement(By.name("professor"));

        Select profSelect = new Select(prof);
        profSelect.selectByValue("cachatme");

        WebElement visibility = driver.findElement(By.xpath("//*[@id=\"list--Bio\"]/li[1]"));

        String display1 = visibility.getCssValue("display");

        Assert.assertEquals("block", display1);
    }


}
