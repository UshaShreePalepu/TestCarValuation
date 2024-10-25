package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage {
    WebDriver driver;

    public DetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

    public String getRegistrationNumber(String regNum) {
        return driver.findElement(By.xpath("//div[@class='d-none d-sm-block mb-4 ng-tns-c2261452767-2 ng-star-inserted']//div[@class='details-vrm ng-star-inserted'][normalize-space()='" + regNum + "']")).getText();
    }

    public String getMake() {
        return driver.findElement(By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[1]/div[2]")).getText();
    }

    public String getModelDetails() {
        return driver.findElement(By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[2]/div[2]")).getText();
    }

    public String getMakeYear() {
        return driver.findElement(By.xpath("//*[@id=\"wbac-app-container\"]/div/div/vehicle-questions/div/section[1]/div/div[1]/div/div[3]/div/vehicle-details/div[3]/div[2]/div[3]/div[2]")).getText();
    }
}
