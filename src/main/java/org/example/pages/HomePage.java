package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterVehicleRegistrationNumber(String number) {
        driver.findElement(By.id("vehicleReg")).sendKeys(number);
    }

    public void enterMileage(String mileage) {
        driver.findElement(By.id("Mileage")).sendKeys(mileage);
    }

    public void clickGetMyCarValuationButton() {
        driver.findElement(By.cssSelector("#btn-go")).click();
    }


}
