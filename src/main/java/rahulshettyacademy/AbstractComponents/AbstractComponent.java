package rahulshettyacademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.OrdersPage;

import java.time.Duration;

public class AbstractComponent {

    WebDriver driver;
    public AbstractComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="[routerlink*='cart']")
    WebElement cartBtn;
    @FindBy(css = "button[routerlink='/dashboard/myorders']")
    WebElement ordersBtn;
    public void waitForElementToAppear(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));

    }
    public void waitForElementToDisappear(By findBy){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(findBy));

    }

    public OrdersPage goToOrders(){

        ordersBtn.click();
        //MyCartPage mycart = new MyCartPage(driver);
        return new OrdersPage(driver);
    }
    public MyCartPage goToCart(){

        cartBtn.click();
        //MyCartPage mycart = new MyCartPage(driver);
        return new MyCartPage(driver);
    }
}
