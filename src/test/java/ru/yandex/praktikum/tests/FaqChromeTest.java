package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FaqChromeTest extends FaqBaseTest {
    public FaqChromeTest(
            int buttonIndex,
            boolean faqAccordionItemPanelHiddenExpected,
            String faqAccordionItemPanelText) {
        super(
                buttonIndex,
                faqAccordionItemPanelHiddenExpected,
                faqAccordionItemPanelText);
    }

    @Override
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    protected WebDriver createWebDriver() {
        return new ChromeDriver();
    }
}
