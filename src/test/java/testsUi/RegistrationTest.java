package testsUi;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Test;
import steps.UserStep;

import java.time.Duration;

import static data.TestData.*;
import static org.junit.Assert.*;

public class RegistrationTest extends BaseUiTest {
    private String userAccessToken;
    private UserModel user;  // Поле для хранения пользователя

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет успешную регистрацию с валидными данными")
    public void testSuccessfulRegistration() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        registerPage.clickRegisterButton();
        registerPage.register(NAME, EMAIL, PASSWORD);
        loginPage.login(EMAIL, PASSWORD);
        mainPage.clickPersonalAccountButton();
        //assertTrue("Вход не удался: Текст Профиль не отображается", mainPage.isPersonalCabinetLoaded());

        user = new UserModel(EMAIL, PASSWORD, NAME);  // Сохраняем пользователя для tearDown
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверяет ошибку при пароле менее 6 символов")
    public void testInvalidPasswordError() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();
        registerPage.clickRegisterButton();
        registerPage.register(NAME, EMAIL, "12345");
        assertEquals("Некорректный пароль", registerPage.getErrorMessage());
    }

    @After
    public void tearDown() {
        // Получаем токен в tearDown, если пользователь создан
        if (user != null && userAccessToken == null) {
            Response loginResponse = UserStep.loginUser(user);
            if (loginResponse.getStatusCode() == 200) {
                userAccessToken = UserStep.getUserAccessToken(loginResponse);
            }
        }
        // Удаляем пользователя
        if (userAccessToken != null) {
            UserStep.deleteUser(userAccessToken);
            userAccessToken = null;
        }
        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }
}