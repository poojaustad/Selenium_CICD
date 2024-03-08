package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
    WebDriver driver;
    public CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver=driver;
    }
    @FindBy(css = "input[placeholder='Select Country']")
    WebElement country;
   By  buttonavailable = By.cssSelector(".ta-results");
      @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement placeOrderBtn;

    public void SelectCountry(String countryName){
        Actions action = new Actions(driver);
        action.sendKeys(country,countryName).build().perform();
        waitForElementToAppear(buttonavailable);
        selectCountry.click();
    }

        public ConfirmationPage PlaceTheOrder(){

        placeOrderBtn.click();
        return new ConfirmationPage(driver);
    }


}
