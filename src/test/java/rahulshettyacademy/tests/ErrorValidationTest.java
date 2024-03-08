package rahulshettyacademy.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

    @Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
    public void LoginWrongMailIdValidation() {
        ProductCatalogue productcatalogue = landingpage.loginApplication("poojaPp@gmail.com", "Pp2345");
        String errorMessage = landingpage.getErrorMessage();
        Assert.assertEquals("Incorrect email password.",errorMessage);
    }
    @Test(groups = {"ErrorHandling"})
    public void LoginWrongPasswordValidation() {
        ProductCatalogue productcatalogue = landingpage.loginApplication("pooja@gmail.com", "Pp2345678");
        String errorMessage = landingpage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.",errorMessage);
    }

}
