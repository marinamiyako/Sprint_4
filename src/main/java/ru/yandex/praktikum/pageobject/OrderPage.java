package ru.yandex.praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    private final WebDriver driver;
    //для контактных данных
    private final By orderAboutUserLabel = By.xpath(".//div[text() = 'Для кого самокат']"); //заголовок
    private final By orderUserNameField = By.xpath(".//input[@placeholder='* Имя']"); //поле Имя
    private final By orderUserSurnameField = By.xpath(".//input[@placeholder='* Фамилия']"); //поле Фамилия
    private final By orderAddressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); //поле Адрес
    private final By orderMetroStationField = By.xpath(".//input[@placeholder='* Станция метро']"); //поле выбора станции метро
    private final By orderUserPhoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); //поле для номера телефона
    private final By orderMoveToRentingInfoButton = By.xpath(".//button[text() = 'Далее']"); //кнопка далее
    //для данных аренды
    private final By orderAboutRentingLabel = By.xpath(".//div[text() = 'Про аренду']"); //заголовок
    private final By orderRentalStartDateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']"); //выбор даты
    private final By orderRentalPeriodField = By.className("Dropdown-placeholder"); //период аренды
    private final By orderScooterColorField = By.className("Checkbox_Input__14A2w"); //цвет самоката
    private final By orderCommentField = By.xpath(".//input[@placeholder='Комментарий для курьера']"); //комментарий
    private final By orderMoveToConfirmOrderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text() = 'Заказать']"); //кнопка заказать
    //локаторы пря подтверждения заказа
    private final By orderConfirmationLabel = By.xpath(".//div[text() = 'Хотите оформить заказ?']"); //заголовок
    private final By orderConfirmationButton = By.xpath(".//button[text() = 'Да']"); //кнопка Да

    //заказ подтвержден
    private final By orderOrderConfirmedLabel = By.xpath(".//div[text() = 'Заказ оформлен']"); //информация о заказе

    //конструктор
    public OrderPage(WebDriver driver){

        this.driver = driver;
    }

    //методы для данных юзера
    public By getOrderAboutUserLabel() {

        return orderAboutUserLabel;
    }
    public By getOrderRentalPeriodValue(String value) {

        return By.xpath(".//div[text() = '"+ value +"']");
    }
    public void setOrderUserName(String userName) {

        driver.findElement(orderUserNameField).sendKeys(userName);
    }
    public void setOrderUserSurname(String userSurname) {
        driver.findElement(orderUserSurnameField).sendKeys(userSurname);
    }
    public void setOrderAddress(String address) {

        driver.findElement(orderAddressField).sendKeys(address);
    }
    public void setOrderMetroStation(String metroStation) {
        driver.findElement(orderMetroStationField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderMetroStationField).sendKeys(metroStation);
        driver.findElement(orderMetroStationField).sendKeys(Keys.ENTER);
    }
    public void setOrderPhone(String phone) {

        driver.findElement(orderUserPhoneField).sendKeys(phone);
    }
    public void clickMoveToRentingInfoInOrderButton() {

        driver.findElement(orderMoveToRentingInfoButton).click();
    }

    //методы для данных для аренда
    public By getOrderAboutRentingLabel() {

        return orderAboutRentingLabel;
    }
    public void setOrderStartDate(String startDate) {
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(orderRentalStartDateField).sendKeys(startDate);
        driver.findElement(orderRentalStartDateField).sendKeys(Keys.ENTER);
    }
    public void setOrderRentalPeriod(String value) {
        driver.findElement(orderRentalPeriodField).click();
        driver.findElement(getOrderRentalPeriodValue(value)).click();
    }
    public void setOrderScooterColor(String scooterColor) {
        driver.findElement(orderScooterColorField).sendKeys(scooterColor);
    }
    public void setOrderComment(String comment) {

        driver.findElement(orderCommentField).sendKeys(comment);
    }
    public void clickMoveToConfirmationOrderButton() {

        driver.findElement(orderMoveToConfirmOrderButton).click();
    }

    //метод для подтверждения заказа
    public By getOrderConfirmationLabel() {

        return orderConfirmationLabel;
    }

    public By getOrderConfirmationButton() {

        return orderConfirmationButton;
    }

    public void clickOrderConfirmationButton() {
        driver.findElement(orderConfirmationButton).click();
    }

    //заказ подтвержден
    public By getOrderConfirmedLabel() {

        return orderOrderConfirmedLabel;
    }

    //
    public void addUserInfoInOrder(
            String userName,
            String userSurname,
            String address,
            String metroStation,
            String phone) {
        setOrderUserName(userName);
        setOrderUserSurname(userSurname);
        setOrderAddress(address);
        setOrderMetroStation(metroStation);
        setOrderPhone(phone);
        clickMoveToRentingInfoInOrderButton();
    }

    public void addRentingInfoInOrder(
            String startDate,
            String rentalPeriod,
            String scooterColor,
            String comment) {
        setOrderStartDate(startDate);
        setOrderRentalPeriod(rentalPeriod);
        setOrderScooterColor(scooterColor);
        setOrderComment(comment);
        clickMoveToConfirmationOrderButton();
    }
}
