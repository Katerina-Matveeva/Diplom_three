package testsUi;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import models.UserModel;
import org.junit.After;
import org.junit.Test;
import steps.UserStep;
import static data.TestData.*;
import static org.junit.Assert.*;

public class RegistrationTest extends BaseUiTest {
    private String userAccessToken;

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверяет успешную регистрацию с валидными данными")
    public void testSuccessfulRegistration() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();  // Проверки внутри метода
        registerPage.clickRegisterButton();
        registerPage.register(NAME, EMAIL, PASSWORD);  //регистрация внутри метода
        loginPage.login(EMAIL, PASSWORD);
        mainPage.clickPersonalAccountButton();
        mainPage.isPersonalCabinetLoaded();
        //assertTrue("Вход не удался: Текс Профиль не отображается", mainPage.isPersonalCabinetLoaded() );

        UserModel user = new UserModel(EMAIL, PASSWORD, NAME);
        Response loginResponse = UserStep.loginUser(user);
        if (loginResponse.getStatusCode() == 200) {
            userAccessToken = UserStep.getUserAccessToken(loginResponse);
        }
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Проверяет ошибку при пароле менее 6 символов")
    public void testInvalidPasswordError() {
        mainPage.open();
        mainPage.clickPersonalAccountButton();  // Проверки внутри метода
        registerPage.clickRegisterButton();
        registerPage.register(NAME, EMAIL, "12345");  // Проверки внутри метода
        assertEquals("Некорректный пароль", registerPage.getErrorMessage());  // Проверки внутри метода
    }

    @After
    public void tearDown() {
        if (userAccessToken != null) {
            UserStep.deleteUser(userAccessToken);
            userAccessToken = null;
        }
    }
}