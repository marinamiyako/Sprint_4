package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FaqChromeTest extends FaqBaseTest {
    public FaqChromeTest(
            String accordionItemId,
            boolean faqAccordionItemPanelHiddenExpected,
            String faqAccordionItemPanelText) {
        super(
                accordionItemId,
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
