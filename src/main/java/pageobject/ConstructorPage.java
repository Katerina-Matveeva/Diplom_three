package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class ConstructorPage {
    private final WebDriver driver;

    // Локаторы
    private final By bunsTab = By.xpath(".//div[./span[text()='Булки']]");
    private final By saucesTab = By.xpath(".//div[./span[text()='Соусы']]");
    private final By fillingsTab = By.xpath(".//div[./span[text()='Начинки']]");
    private final By activeTab = By.xpath("//div[contains(@class, 'tab_tab_type_current')]");

    public ConstructorPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Получить элемент вкладки 'Булки'")
    public WebElement getBunsTabElement() {
        return waitForElement(bunsTab);
    }

    @Step("Получить элемент вкладки 'Соусы'")
    public WebElement getSaucesTabElement() {
        return waitForElement(saucesTab);
    }

    @Step("Получить элемент вкладки 'Начинки'")
    public WebElement getFillingsTabElement() {
        return waitForElement(fillingsTab);
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        clickTab(bunsTab);
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        clickTab(saucesTab);
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        clickTab(fillingsTab);
    }

    @Step("Проверка активной вкладки")
    public String getActiveTabText() {
        waitForElement(activeTab);
        return driver.findElement(activeTab).getText();
    }

    @Step("Проверка активности вкладки 'Булки'")
    public boolean isBunsTabActive() {
        return getActiveTabText().contains("Булки");
    }

    @Step("Проверка активности вкладки 'Соусы'")
    public boolean isSaucesTabActive() {
        return getActiveTabText().contains("Соусы");
    }

    @Step("Проверка активности вкладки 'Начинки'")
    public boolean isFillingsTabActive() {
        return getActiveTabText().contains("Начинки");
    }

    private void clickTab(By tabLocator) {
        WebElement tab = waitForElement(tabLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", tab);
        try {
            tab.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", tab);
        }
    }

    private WebElement waitForElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
}
