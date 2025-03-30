package tests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.XyzPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    XyzPage xyz_page;

    @BeforeMethod
    @Step("Инициализация драйвера и открытие страницы")
    public void init() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        xyz_page = new XyzPage(driver).openPage();
    }

    @AfterMethod
    @Step("Закрытие драйвера и завершение теста")
    public void teardown() {
        driver.quit();
    }
}
