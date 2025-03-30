package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.XyzPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    XyzPage xyz_page;

    protected WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @BeforeMethod
    @Step("Инициализация драйвера и открытие страницы")
    public void init() {
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverThreadLocal.set(driver);
        xyz_page = new XyzPage(getDriver()).openPage();
    }

    @AfterMethod
    @Step("Закрытие драйвера и завершение теста")
    public void teardown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
