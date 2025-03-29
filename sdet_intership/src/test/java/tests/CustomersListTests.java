package tests;

import extensions.SortType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CustomersListPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        Assert.assertEquals(firstNames, expectedFirstNames, "First names are not sorted correctly");
    }

    @Test
    public void sortDeskFirstName() {
        customer_page.sortCustomers(SortType.Desk);
        List<String> firstNames = customer_page.getCustomersFirstName();
        List<String> expectedFirstNames = new ArrayList<>(firstNames);
        Collections.sort(expectedFirstNames, Collections.reverseOrder());
        Assert.assertEquals(firstNames, expectedFirstNames, "First names are not sorted correctly");
    }

    @Test
    public void deleteAverageName() {
        var name = customer_page.findAverageName();
        Assert.assertNotNull(name);
        customer_page.deleteAverageCustomer(name);
        Assert.assertFalse(customer_page.getCustomersFirstName().contains(name));
    }
}
