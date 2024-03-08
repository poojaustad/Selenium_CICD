package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class OrdersPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;
    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;
    public OrdersPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public Boolean VerifyOrderDisplay(String productname){

        Boolean flag=productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
        return flag;
    }

}
