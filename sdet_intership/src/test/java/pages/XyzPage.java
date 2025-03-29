package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class XyzPage extends BasePage {
    @FindBy(xpath = "//button[@ng-class='btnClass1']")
    WebElement addCustomerButton;

    @FindBy(xpath = "//button[@ng-class='btnClass3']")
    WebElement customersButton;

    String link = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";

    public XyzPage(WebDriver driver) {
        super(driver);
    }

    public AddCustomerPage clickAddCustomerButton() {
        addCustomerButton.click();
        return new AddCustomerPage(driver);
    }

    public CustomersListPage clickCustomersButton() {
        customersButton.click();
        return new CustomersListPage(driver);
    }

    public XyzPage openPage() {
        driver.get(link);
        return new XyzPage(driver);
    }
}
