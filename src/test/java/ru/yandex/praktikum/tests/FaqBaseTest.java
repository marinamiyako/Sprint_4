package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.praktikum.pageobject.Constants;
import ru.yandex.praktikum.pageobject.MainPage;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public abstract class FaqBaseTest {
    private WebDriver driver;
    private final int buttonIndex;
    private final boolean faqAccordionItemPanelHiddenExpected;
    private final String faqAccordionItemPanelTextExpected;

    public FaqBaseTest(
            int buttonIndex,
            boolean faqAccordionItemPanelHiddenExpected,
            String faqAccordionItemPanelText){
        this.buttonIndex = buttonIndex;
        this.faqAccordionItemPanelHiddenExpected = faqAccordionItemPanelHiddenExpected;
        this.faqAccordionItemPanelTextExpected = faqAccordionItemPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][] {
                {0, false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, false, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, false, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, false, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, false, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, false, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, false, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, false, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public abstract void startUp();

    @Test
    public void checkExpandAccordion() {
        driver = createWebDriver();
        driver.get(Constants.SCOOTER_URL);

        MainPage objectMainPage = new MainPage(driver, buttonIndex);
        //куки
        objectMainPage.clickCookieConfirmButton();
        //переход к вопросам и разворачивание их
        objectMainPage.clickFaqAccordionItemHeading();
        //проверка, что видно панель
        WebElement element = objectMainPage.getFaqAccordionItemPanel();
        boolean visibleActual = Boolean.parseBoolean(element.getAttribute("hidden"));
        assertEquals(visibleActual, faqAccordionItemPanelHiddenExpected);
//        //проверка текста
        String textActual = objectMainPage.getFaqAccordionItemPanelText().getText();
        MatcherAssert.assertThat(textActual, containsString(faqAccordionItemPanelTextExpected));
    }

    @After
    public void teardown() {

        driver.quit();
    }

    protected abstract WebDriver createWebDriver(); //для возможности создать драйвер любого типа в дочерних классах
}
