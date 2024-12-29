package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    private final String faqAccordionItemId;
    private final boolean faqAccordionItemPanelHiddenExpected;
    private final String faqAccordionItemPanelTextExpected;

    public FaqBaseTest(
            String accordionItemId,
            boolean faqAccordionItemPanelHiddenExpected,
            String faqAccordionItemPanelText){
        this.faqAccordionItemId = accordionItemId;
        this.faqAccordionItemPanelHiddenExpected = faqAccordionItemPanelHiddenExpected;
        this.faqAccordionItemPanelTextExpected = faqAccordionItemPanelText;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][] {
                {"accordion__heading-0", false, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-5", false, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
        };
    }

    @Before
    public abstract void startUp();

    @Test
    public void checkExpandAccordion() {
        driver = createWebDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objectMainPage = new MainPage(driver, faqAccordionItemId);
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

    protected abstract WebDriver createWebDriver();
}
