package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.XyzPage;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;

    XyzPage xyz_page;

    @BeforeMethod
    public void init() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        xyz_page = new XyzPage(driver).openPage();
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
