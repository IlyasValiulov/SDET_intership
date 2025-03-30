package tests;

import extensions.DataGeneration;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;

public class AddCustomerTests extends BaseTest {
    AddCustomerPage add_page;

    @BeforeMethod
    public void setup() {
        add_page =  xyz_page.clickAddCustomerButton();
    }

    @Test
    public void AddCustomer() {;
        var lastName = "Valiulov";
        var postCode = DataGeneration.generatePostCode();
        var firstName = DataGeneration.generateName(postCode);
        add_page.addCustomer(firstName, lastName, postCode);

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
    }

    @Test
    public void AddDublicateCustomer() {
        var lastName = "Valiulov";
        var postCode = DataGeneration.generatePostCode();
        var firstName = DataGeneration.generateName(postCode);

        add_page.addCustomer(firstName, lastName, postCode);
        driver.switchTo().alert().dismiss();
        add_page.addCustomer(firstName, lastName, postCode);

        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("Please check the details. Customer may be duplicate."));
    }

    @Test
    public void AddCustomerWithEmptyData() {
        SoftAssert softAssert = new SoftAssert();

        var lastName = "Valiulov";
        var postCode = DataGeneration.generatePostCode();
        var firstName = DataGeneration.generateName(postCode);

        add_page.addCustomer("", lastName, postCode);
        softAssert.assertTrue(add_page.isErrorMessageDisplayed(), "Field firstname is empty");
        add_page.addCustomer(firstName, "", postCode);
        softAssert.assertTrue(add_page.isErrorMessageDisplayed(), "Field lastname is empty");
        add_page.addCustomer(firstName, lastName, "");
        softAssert.assertTrue(add_page.isErrorMessageDisplayed(), "Field postcode is empty");
        softAssert.assertAll();
    }
}
