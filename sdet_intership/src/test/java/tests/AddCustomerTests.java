package tests;

import extensions.DataGeneration;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.AddCustomerPage;

import static io.qameta.allure.Allure.step;

@Feature("Тесты страницы добавления пользователя")
public class AddCustomerTests extends BaseTest {
    AddCustomerPage add_page;

    @BeforeMethod
    @Step("Загрузка страницы добавления пользователя")
    public void setup() {
        add_page = xyz_page.clickAddCustomerButton();
    }

    @Test
    public void AddCustomer() {;
        var lastName = "Valiulov";
        var postCode = DataGeneration.generatePostCode();
        var firstName = DataGeneration.generateName(postCode);
        add_page.addCustomer(firstName, lastName, postCode);
        Alert alert = getDriver().switchTo().alert();
        step("Проверка соответствия выражения", () -> {
            Assert.assertTrue(alert.getText().contains("Customer added successfully with customer id :"));
        });
    }

    @Test
    public void AddDublicateCustomer() {
        var lastName = "Valiulov";
        var postCode = DataGeneration.generatePostCode();
        var firstName = DataGeneration.generateName(postCode);
        var driver = getDriver();

        add_page.addCustomer(firstName, lastName, postCode);

        step("Пропуск уведомления", () -> {
            driver.switchTo().alert().dismiss();
        });

        add_page.addCustomer(firstName, lastName, postCode);

        step("Получение уведомление о дублирующих данных", () -> {
            Alert alert = driver.switchTo().alert();
            Assert.assertTrue(alert.getText().contains("Please check the details. Customer may be duplicate."));
        });
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
        step("Проверка о невозможности создать пользователя с отсутствующем полем", () -> {
            softAssert.assertAll();
        });
    }
}
