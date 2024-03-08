package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
    //Initialization
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;

        PageFactory.initElements(driver,this);
    }

     //WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("poojaPp@gmail.com");
    //PageFactory

    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement loginBtn;
    @FindBy(css = ".ng-trigger-flyInOut")
    WebElement errorMsg;
    By error = By.cssSelector(".ng-trigger-flyInOut");

    public ProductCatalogue loginApplication(String email, String Password){
        userEmail.sendKeys(email);
        userPassword.sendKeys(Password);
        loginBtn.click();
        //ProductCatalogue productcatalogue = new ProductCatalogue(driver);
        return new ProductCatalogue(driver);
    }
    public void goTo(){
        driver.get("https://rahulshettyacademy.com/client/");
    }
    public String getErrorMessage(){
        waitForElementToAppear(error);
        String errorMessage = errorMsg.getText();
        return errorMessage;
    }


}
