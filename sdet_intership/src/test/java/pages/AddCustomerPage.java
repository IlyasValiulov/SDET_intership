package pages;

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

    public AddCustomerPage addCustomer(String first_name, String last_name, String post_code) {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        clearAllFields();
        firstName.sendKeys(first_name);
        lastName.sendKeys(last_name);
        postCode.sendKeys(post_code);
        buttonAdd.click();
        return new AddCustomerPage(driver);
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isDisplayed();
    }

    public static String generatePostCode() {
        StringBuilder code = new StringBuilder();
        int len = 10;
        Random rnd = new Random();
        for (int i = 0; i < len; i++) {
            code.append(rnd.nextInt(0, 10));
        }
        return code.toString();
    }

    public static String generateName(String postCode) {
        StringBuilder name = new StringBuilder();
        for (int i = 0; i < postCode.length(); i += 2) {
            int num = Integer.parseInt(String.valueOf(postCode.charAt(i)) + String.valueOf(postCode.charAt(i+1)));
            name.append((char)(num % 26 + 'a'));
        }
        return name.toString();
    }

    public void clearAllFields() {
        firstName.clear();
        lastName.clear();
        postCode.clear();
    }
}
