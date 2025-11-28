package testsUi;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

public class ConstructorTest extends BaseUiTest {

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверяет, что работает переход к разделу 'Булки'")
    public void testNavigateToBuns() {
        mainPage.open();
        // Проверка видимости и кликабельности вкладки "Булки"
        WebElement bunsTab = constructorPage.getBunsTabElement();
        assertTrue("Вкладка 'Булки' не видна", bunsTab.isDisplayed());
        assertTrue("Вкладка 'Булки' не кликабельна", bunsTab.isEnabled());

        constructorPage.clickBunsTab();
        assertEquals("Булки", constructorPage.getActiveTabText());
        assertTrue("Вкладка 'Булки' не активна", constructorPage.isBunsTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяет, что работает переход к разделу 'Соусы'")
    public void testNavigateToSauces() {
        mainPage.open();
        // Проверка видимости и кликабельности вкладки "Соусы"
        WebElement saucesTab = constructorPage.getSaucesTabElement();
        assertTrue("Вкладка 'Соусы' не видна", saucesTab.isDisplayed());
        assertTrue("Вкладка 'Соусы' не кликабельна", saucesTab.isEnabled());

        constructorPage.clickSaucesTab();
        assertEquals("Соусы", constructorPage.getActiveTabText());
        assertTrue("Вкладка 'Соусы' не активна", constructorPage.isSaucesTabActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяет, что работает переход к разделу 'Начинки'")
    public void testNavigateToFillings() {
        mainPage.open();
        // Проверка видимости и кликабельности вкладки "Начинки"
        WebElement fillingsTab = constructorPage.getFillingsTabElement();
        assertTrue("Вкладка 'Начинки' не видна", fillingsTab.isDisplayed());
        assertTrue("Вкладка 'Начинки' не кликабельна", fillingsTab.isEnabled());

        constructorPage.clickFillingsTab();
        assertEquals("Начинки", constructorPage.getActiveTabText());
        assertTrue("Вкладка 'Начинки' не активна", constructorPage.isFillingsTabActive());
    }
}
