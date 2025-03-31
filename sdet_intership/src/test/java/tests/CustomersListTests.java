package tests;

import extensions.SortType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersListPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.qameta.allure.Allure.step;

public class CustomersListTests extends BaseTest {
    CustomersListPage customer_page;
    @BeforeMethod
    public void setup() {
        customer_page = xyz_page.clickCustomersButton();
    }

    @Test
    public void sortAskFirstName() {
        customer_page.sortCustomers(SortType.Ask);
        List<String> firstNames = customer_page.getCustomersFirstName();
        List<String> expectedFirstNames = new ArrayList<>(firstNames);
        Collections.sort(expectedFirstNames);
        step("Проверка соответсвия сортировки по возрастанию", () -> {
            Assert.assertEquals(firstNames, expectedFirstNames, "First names are not sorted correctly");
        });
    }

    @Test
    public void sortDeskFirstName() {
        customer_page.sortCustomers(SortType.Desk);
        List<String> firstNames = customer_page.getCustomersFirstName();
        List<String> expectedFirstNames = new ArrayList<>(firstNames);
        Collections.sort(expectedFirstNames, Collections.reverseOrder());
        step("Проверка соотвествия сортировки по убыванию", () -> {
            Assert.assertEquals(firstNames, expectedFirstNames, "First names are not sorted correctly");
        });
    }

    @Test
    public void deleteAverageName() {
        var name = customer_page.findAverageName();
        step("Проверка на существования имени", () -> {
            Assert.assertNotNull(name);
        });
        customer_page.deleteAverageCustomer(name);
        step("Проверка удаления имени на страницы", () -> {
            Assert.assertFalse(customer_page.getCustomersFirstName().contains(name));
        });
    }
}
