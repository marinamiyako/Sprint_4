package ru.yandex.praktikum.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@RunWith(Parameterized.class)
public class OrderFirefoxTest extends OrderBaseTest {
    public OrderFirefoxTest(
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
                userName,
                userSurname,
                address,
                metroStation,
                userPhone,
                rentalStartDate,
                rentalPeriod,
                scooterColor,
                comment);
    }

    @Override
    public void startUp(){
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    protected WebDriver createWebDriver() {
        return new FirefoxDriver();
    }
}
