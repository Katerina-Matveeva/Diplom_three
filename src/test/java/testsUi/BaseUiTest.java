package testsUi;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.*;

public class BaseUiTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegisterPage registerPage;
    protected ConstructorPage constructorPage;

    @Before
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        if ("yandex".equals(browser)) {
            // Для Yandex Browser
            System.setProperty("webdriver.chrome.driver", "/Users/ekaterinameskova/Drivers/yandexdriver");
            driver = new ChromeDriver();
        } else {
            // Для Chrome
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        constructorPage = new ConstructorPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}