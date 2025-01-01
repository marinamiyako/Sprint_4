package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    //локатор для куки
    private final By cookieConfirmButton = By.xpath(".//button[@class='App_CookieButton__3cvqF']");
    //верхняя кнопка заказа
    private final By orderButtonTop = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");
    //нижняя кнопка заказа
    private final By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");
    //вопросы о важном
    private By faqAccordionItemHeading;
    private By faqAccordionItemPanel;
    private By faqAccordionItemPanelText;

    //конструктор
    public MainPage(
            WebDriver driver,
            int buttonIndex) {
        this.driver = driver;

        this.faqAccordionItemHeading = By.xpath(".//div[@id='accordion__heading-"+ buttonIndex +"']");
        this.faqAccordionItemPanel = By.xpath(".//div[@aria-labelledby='accordion__heading-"+ buttonIndex +"']");
        this.faqAccordionItemPanelText = By.xpath(".//div[@aria-labelledby='accordion__heading-"+ buttonIndex +"']/p");
    }

    public MainPage(WebDriver driver) {

        this.driver = driver;
    }

    //куки
    public void clickCookieConfirmButton() {

        driver.findElement(cookieConfirmButton).click();
    }

    //метод, вопросы о важном
    public void clickFaqAccordionItemHeading() {
        WebElement element = driver.findElement(faqAccordionItemHeading);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(faqAccordionItemHeading));
        new Actions(driver).moveToElement(element).click().perform();
    }
    public WebElement getFaqAccordionItemPanel() {

        return driver.findElement(faqAccordionItemPanel);
    }

    public WebElement getFaqAccordionItemPanelText() {

        return driver.findElement(faqAccordionItemPanelText);
    }

    //переход к размещению заказа
    public void clickOrderButtonTop() {
        new WebDriverWait(driver, Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOfElementLocated(orderButtonTop));
        driver.findElement(orderButtonTop);
    }

    public void clickOrderButtonDown() {
        WebElement element = driver.findElement(orderButtonDown);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(orderButtonDown));
        driver.findElement(orderButtonDown).click();
    }

}
