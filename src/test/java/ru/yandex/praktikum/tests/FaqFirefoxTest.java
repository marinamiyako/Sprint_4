package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FaqFirefoxTest extends FaqBaseTest {
    public FaqFirefoxTest(
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
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    protected WebDriver createWebDriver() {
        return new FirefoxDriver();
    }
}
