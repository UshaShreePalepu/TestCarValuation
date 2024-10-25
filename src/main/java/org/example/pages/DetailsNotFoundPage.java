package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsNotFoundPage {
    WebDriver driver;

    public DetailsNotFoundPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageHeadingText() {
        return driver.findElement(By.cssSelector("#wbac-app-container > div > div > vehicle-registration-check > section.primary-section.height-xs-full.center-content-sm.raised-section > div > div.container > div > div.col-12.col-lg-12.offset-lg-0.page-header > h1")).getText();
    }
}
