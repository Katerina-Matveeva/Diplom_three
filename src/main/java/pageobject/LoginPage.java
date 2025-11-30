package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    // Локаторы
    final By emailField = By.xpath("//input[@name='name']"); // Поле email
    final By passwordField = By.xpath("//input[@name='Пароль']"); // Поле пароль
    final By loginButton = By.xpath("//button[text()='Войти']"); // Кнопка "Войти"
    final By forgotPasswordLink = By.xpath("//a[@href='/forgot-password']"); // Ссылка на восстановление пароля
    final By registerLink = By.cssSelector("a.Auth_link__1fOlj");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Клик по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Вход с email и паролем")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    @Step("Клик по ссылке восстановления пароля")
    public void clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
    }

    @Step("Клик по ссылке регистрации")
    public void clickRegisterLink() {
        driver.findElement(registerLink).click();
    }


    @Step("Ожидание загрузки страницы входа")
    public void waitForPageLoad() {
        waitForVisibility(loginButton);
    }


    private void waitForVisibility(By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
}
