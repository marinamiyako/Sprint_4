package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.OrderPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public abstract class OrderBaseTest {
    private WebDriver driver;
    private final int orderButtonPlace;
    private final String userName;
    private final String userSurname;
    private final String address;
    private final String metroStation;
    private final String userPhone;
    private final String rentalStartDate;
    private final String rentalPeriod;
    private final String scooterColor;
    private final String comment;

    public OrderBaseTest(
            int orderButtonPlace,
            String userName,
            String userSurname,
            String address,
            String metroStation,
            String userPhone,
            String rentalStartDate,
            String rentalPeriod,
            String scooterColor,
            String comment) {
        this.orderButtonPlace = orderButtonPlace;
        this.userName = userName;
        this.userSurname = userSurname;
        this.address = address;
        this.metroStation = metroStation;
        this.userPhone = userPhone;
        this.rentalStartDate = rentalStartDate;
        this.rentalPeriod = rentalPeriod;
        this.scooterColor = scooterColor;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getInputData() {
        return new Object[][] {
                {1, "Маша", "Пушкина", "Москва, Пушкина, 1", "Чистые пруды", "+77771234567", "12.01.2025", "сутки", "серая безысходность", "тест"},
                {2, "Паша", "Пупкин", "Громова, 5", "Сокольники", "+77771234567", "31.12.2024", "шестеро суток", "чёрный жемчуг", "тест"}
        };
    }

    @Before
    public abstract void startUp();

    @Test
    public void checkOrder() {
        driver = createWebDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        MainPage objectMainPage = new MainPage(driver);
        OrderPage objectOrderPage = new OrderPage(driver);

        //для клика по кнопке заказа
        switch (orderButtonPlace) {
            case 1: objectMainPage.clickOrderButtonTop();
                break;
            case 2: objectMainPage.clickOrderButtonDown();
                break;
        }

        objectMainPage.clickCookieConfirmButton();
        //данные юзера
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objectOrderPage.getOrderAboutUserLabel()));
        objectOrderPage.addUserInfoInOrder(userName, userSurname, address, metroStation, userPhone);
        //данные аренды
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objectOrderPage.getOrderAboutRentingLabel()));
        objectOrderPage.addRentingInfoInOrder(rentalStartDate, rentalPeriod, scooterColor, comment);
        //подтверждение заказа
        new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objectOrderPage.getOrderConfirmationLabel()));
        objectOrderPage.clickOrderConfirmationButton();
        //заказ подтвержден
        new WebDriverWait(driver,Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(objectOrderPage.getOrderConfirmedLabel()));
    }

    @After
    public void teardown() {

        driver.quit();
    }

    protected abstract WebDriver createWebDriver(); //для возможности создать драйвер любого типа в дочерних классах
}
