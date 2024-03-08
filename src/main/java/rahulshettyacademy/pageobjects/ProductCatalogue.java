package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

import java.util.List;

public class ProductCatalogue extends AbstractComponent {
    //Initialization
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }


    @FindBy(css=".mb-3")
    List<WebElement> products;

    By productBy = By.cssSelector(".mb-3");

    By addToCartBtn = By.cssSelector(".card-body button:last-of-type");
    By loadingScreenBy = By.cssSelector(".toast-container");
    By annimationBy = By.cssSelector(".ng-animating");


    public List<WebElement> getProductList()
    {

        waitForElementToAppear(productBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {

        WebElement product=getProductList().stream().filter(s->
                s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        return product;

    }

    public void addProductToCart(String productName){

        WebElement product  = getProductByName(productName);
        product.findElement(addToCartBtn).click();
        waitForElementToAppear(loadingScreenBy);
        waitForElementToDisappear(annimationBy);

    }

}
