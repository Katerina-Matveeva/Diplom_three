package testsUi;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import steps.UserStep;
import java.time.Duration;
import static data.TestData.*;
import static org.junit.Assert.*;

public class LoginUiTest extends BaseUiTest {
    private String userAccessToken;
    private UserModel user;  // Поле для хранения пользователя

    @Before
    public void createUser() {
        // Создаём пользователя один раз перед всеми тестами
        user = new UserModel(EMAIL, PASSWORD, NAME);
        Response createResponse = UserStep.createUser(user);
        userAccessToken = UserStep.getUserAccessToken(createResponse);
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Description("Проверяет вход через кнопку на главной странице")
    public void testLoginFromMainButton() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        loginPage.login(EMAIL, PASSWORD);  // Используем EMAIL, PASSWORD из TestData
        mainPage.clickPersonalAccountButton();
        assertTrue("Вход не удался: Текст Профиль не отображается", mainPage.isPersonalCabinetLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверяет вход через личный кабинет")
    public void testLoginFromPersonalCabinet() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        loginPage.waitForPageLoad();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.clickPersonalAccountButton();
        assertTrue("Вход не удался: Текст Профиль не отображается", mainPage.isPersonalCabinetLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверяет вход через ссылку в форме регистрации")
    public void testLoginFromRegisterForm() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        mainPage.open();
        mainPage.clickPersonalAccountButton();  // нажали личный кабинет
        registerPage.waitForPageLoad();
        loginPage.clickRegisterLink(); // перешли в регистрацию
        registerPage.clickLoginLink();
        loginPage.login(EMAIL, PASSWORD);
        //Проверка успешного входа в личный кабинет
        mainPage.clickPersonalAccountButton();
        mainPage.isPersonalCabinetLoaded();
        assertTrue("Вход не удался: Текс Профиль не отображается", mainPage.isPersonalCabinetLoaded() );
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверяет вход через форму восстановления пароля")
    public void testLoginFromPasswordRecovery() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        mainPage.open();
        mainPage.clickLoginButton();
        loginPage.waitForPageLoad();
        loginPage.clickForgotPasswordLink();
        registerPage.clickLoginLink();  // Предполагаю, что это ссылка на логин
        loginPage.waitForPageLoad();
        loginPage.login(EMAIL, PASSWORD);
        mainPage.clickPersonalAccountButton();
        assertTrue("Вход не удался: Текст Профиль не отображается", mainPage.isPersonalCabinetLoaded());
    }

    @After
    public void tearDown() {
        // Удаляем пользователя после всех тестов
        if (userAccessToken != null) {
            UserStep.deleteUser(userAccessToken);
            userAccessToken = null;
        }
        // Закрытие браузера (дополнительно)
        if (driver != null) {
            driver.quit();
       }
    }
}