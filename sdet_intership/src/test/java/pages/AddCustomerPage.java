package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddCustomerPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder='First Name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@placeholder='Post Code']")
    WebElement postCode;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement buttonAdd;

    @FindBy(className = "ng-invalid-required")
    private WebElement errorMessage;

    public AddCustomerPage(WebDriver driver) { super(driver); }

    @Step("Добавление пользователся со значениями {first_name} {last_name} {post_code}")
    public AddCustomerPage addCustomer(String first_name, String last_name, String post_code) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        clearAllFields();
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        postCode.sendKeys(post_code);
        buttonAdd.click();
        return new AddCustomerPage(driver);
    }

    @Step("Вывод сообщения об ошибке")
    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public void clearAllFields() {
        firstName.clear();
        lastName.clear();
        postCode.clear();
    }
}
