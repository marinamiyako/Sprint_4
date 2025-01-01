package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//
public class OrderChromeTest extends OrderBaseTest {
    public OrderChromeTest(
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
        super(
                orderButtonPlace,
                userName, userSurname,
                address, metroStation,
                userPhone, rentalStartDate,
                rentalPeriod,
                scooterColor,
                comment);
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
