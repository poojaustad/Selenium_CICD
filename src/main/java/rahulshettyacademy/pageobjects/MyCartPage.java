package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class MyCartPage extends AbstractComponent {
    WebDriver driver;
    @FindBy(css = ".cartSection h3")
    private List<WebElement> productlist;
    @FindBy(css = ".totalRow button")
    WebElement checkoutBtn;
    public MyCartPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }

    public Boolean ProductVerify(String productname){


        Boolean flag=productlist.stream().anyMatch(s->s.getText().equalsIgnoreCase(productname));
        return flag;
    }
public CheckoutPage checkoutProduct(){
        checkoutBtn.click();
    //CheckoutPage checkoutPage= new CheckoutPage(driver);
    return new CheckoutPage(driver);
}


}
