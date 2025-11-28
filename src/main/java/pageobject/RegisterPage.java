package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;

     final By nameField = By.xpath("//input[@name='name']");
     final By emailField = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
     final By passwordField = By.xpath("//input[@name='Пароль']");
     final By registerButton = By.xpath("//*[@id=\"root\"]/div/main/div/div/p[1]/a");
     final By loginLink = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a");
     final By errorMessage = By.xpath("//p[contains(@class, 'input__error')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввод имени")
    public void enterName(String name) {
        WebElement field = waitForElement(nameField);
        if (field.isDisplayed() && field.isEnabled()) {
            field.sendKeys(name);
        } else {
            throw new IllegalStateException("Поле имени не готово для ввода");
        }
    }

    @Step("Ввод email")
    public void enterEmail(String email) {
        WebElement field = waitForElement(emailField);
        if (field.isDisplayed() && field.isEnabled()) {
            field.sendKeys(email);
        } else {
            throw new IllegalStateException("Поле email не готово для ввода");
        }
    }

    @Step("Ввод пароля")
    public void enterPassword(String password) {
        WebElement field = waitForElement(passwordField);
        if (field.isDisplayed() && field.isEnabled()) {
            field.sendKeys(password);
        } else {
            throw new IllegalStateException("Поле пароля не готово для ввода");
        }
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        WebElement button = waitForElement(registerButton);
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
        } else {
            throw new IllegalStateException("Кнопка 'Зарегистрироваться' не готова для клика");
        }
    }

    @Step("Регистрация с именем, email и паролем")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }

    @Step("Клик по ссылке входа")
    public void clickLoginLink() {
        WebElement link = waitForElement(loginLink);
        if (link.isDisplayed() && link.isEnabled()) {
            link.click();
        } else {
            throw new IllegalStateException("Ссылка входа не готова для клика");
        }
    }

    @Step("Получение текста сообщения об ошибке")
    public String getErrorMessage() {
        WebElement message = waitForElement(errorMessage);
        if (message.isDisplayed()) {
            return message.getText();
        } else {
            throw new IllegalStateException("Сообщение об ошибке не видно");
        }
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad() {
        waitForElement(registerButton);
    }

    private WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}