package rahulshettyacademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.time.Duration;
import java.util.stream.Collectors;


public class StandAloneTest {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/ustadmobile/IdeaProjects/SeleniumFrameworkDesign/src/Drivers/chromedriver");
       // WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client/");
        driver.manage().window().maximize();
        String productname= "ZARA COAT 3";
        driver.findElement(By.id("userEmail")).sendKeys("anusree@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("anusreeA123");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
      List<WebElement> productlist=driver.findElements(By.cssSelector(".mb-3"));
        WebElement product=productlist.stream().filter(s->
        s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);

        product.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".toast-container"))));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        //driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
      driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/h1")));
       List<WebElement> cartproducts=  driver.findElements(By.cssSelector(".cartSection h3"));
       Boolean match=cartproducts.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
       Assert.assertTrue(match);

       driver.findElement(By.cssSelector(".totalRow button")).click();
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"India").build().perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
        //action.click(driver.findElement(By.xpath("//span[normalize-space()='India']//i"))).build().perform();
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
        String note=driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(note.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();
    }
}
