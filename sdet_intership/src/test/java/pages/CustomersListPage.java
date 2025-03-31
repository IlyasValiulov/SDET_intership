package pages;

import extensions.SortType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CustomersListPage extends BasePage {
    @FindBy(xpath = "//a[contains(.,'First Name')]")
    WebElement fistName;

    @FindBy(css = "tbody tr")
    List<WebElement> rows;

    @FindBy(xpath = "//tbody/tr/td[1]")
    List<WebElement> firstNameList;

    public CustomersListPage(WebDriver driver) {
        super(driver);
    }

    @Step("Сортировка клиентов")
    public CustomersListPage sortCustomers(SortType type) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        var names = getCustomersFirstName();
        List<String> sortedList = new ArrayList<>(names);
        switch (type) {
            case Ask: {
                Collections.sort(sortedList);
                break;
            }
            case Desk: {
                Collections.sort(sortedList, Collections.reverseOrder());
                break;
            }
        }
        while (!names.equals(sortedList)) {
            fistName.click();
            names = getCustomersFirstName();
        }
        return new CustomersListPage(driver);
    }

    @Step("Удаление клиентов с именем близким к среднему значению всех имен")
    public void deleteAverageCustomer(String name) {
        rows.stream()
                .filter(row -> row.findElements(By.tagName("td")).get(0).getText().equals(name))
                .findFirst()
                .ifPresent(row -> row.findElement(By.tagName("button")).click());
    }

    @Step("Получение имен клиентов")
    public List<String> getCustomersFirstName() {
        List<String> names = new ArrayList<>();
        for (WebElement cell : firstNameList) {
            names.add(cell.getText());
        }
        return names;
    }

    @Step("Поиск имени")
    public String findAverageName() {
        var names = getCustomersFirstName();
        double avg = names.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);
        return names.stream().min((n1, n2) -> {
                    double diff1 = Math.abs(n1.length() - avg);
                    double diff2 = Math.abs(n2.length() - avg);
                    return Double.compare(diff1, diff2);
                })
                .orElse(null);
    }
}
