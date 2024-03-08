package rahulshettyacademy.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class SubmitOrderTest extends BaseTest {
    String productname = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void SubmitOrder(HashMap<String, String> input) throws IOException
    {

        //LandingPage landingpage = launchApplication();
        ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));

        List<WebElement> productlist = productcatalogue.getProductList();
        productcatalogue.addProductToCart(input.get("product"));
        MyCartPage mycart = productcatalogue.goToCart();
        boolean flag = mycart.ProductVerify(input.get("product"));
        Assert.assertEquals(flag, true);
        CheckoutPage checkoutPage = mycart.checkoutProduct();
        checkoutPage.SelectCountry("India");
        ConfirmationPage confirmationpage = checkoutPage.PlaceTheOrder();
        String confirmationMessage = confirmationpage.getConfirmationMessage();
        Assert.assertTrue(confirmationMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }

    @Test(dependsOnMethods = {"SubmitOrder"})
    public void OrderHistoryTest() {

        ProductCatalogue productcatalogue = landingpage.loginApplication("poojaPp@gmail.com", "Pp2345678");
        OrdersPage orderspage = productcatalogue.goToOrders();
        boolean flag = orderspage.VerifyOrderDisplay(productname);
        Assert.assertEquals(flag, true);
    }



    @DataProvider
    public Object[][] getData() throws IOException {
      List<HashMap<String,String>> data =  getJasonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}};
    }
/*
    @DataProvider
    public Object[][] getData()
    {
        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email","poojaPp@gmail.com");
        map1.put("password","Pp2345678");
        map1.put("product","ZARA COAT 3");

        HashMap<String,String> map2 = new HashMap<String,String>();
        map2.put("email","anusree@gmail.com");
        map2.put("password","anusreeA123");
        map2.put("product","ADIDAS ORIGINAL");

       return new Object[][] {{map1}, {map2}};
    */
}
