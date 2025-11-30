package pageobject;

import data.TestData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    final By personalAccountButton = By.xpath("//a[@href='/account']");
    final By profileText = By.xpath("//*[contains(text(),'Профиль')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public void open() {
        driver.get(TestData.BASE_URL);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {

        WebElement button = waitForElement(loginButton);
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
        } else {
            throw new IllegalStateException("Кнопка 'Войти в аккаунт' не готова для клика");
        }
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        WebElement button = waitForElement(personalAccountButton);
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
        } else {
            throw new IllegalStateException("Кнопка 'Личный кабинет' не готова для клика");
        }
    }

    @Step("Проверка загрузки личного кабинета")
    public boolean isPersonalCabinetLoaded() {
        try {
            waitForElement(profileText);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}