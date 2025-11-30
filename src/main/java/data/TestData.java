package data;

import com.github.javafaker.Faker;


public class TestData {
    // Config для BASE_URL
    private static class Config {
        public static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    }
    public static final String BASE_URI = Config.BASE_URL;  // Для API
    public static final String BASE_URL = Config.BASE_URL;


    static Faker userModel = new Faker();

    // Для пользователя
    public static final String EMAIL = userModel.name().lastName().toLowerCase() + userModel.regexify("[0-9]{4}") + "@yandex.ru";
    public static final String PASSWORD = userModel.regexify("[0-9]{6}");  //  минимум 6 символов
    public static final String NAME = userModel.name().firstName();

}
