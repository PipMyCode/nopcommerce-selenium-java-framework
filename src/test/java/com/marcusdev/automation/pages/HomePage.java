package com.marcusdev.automation.pages;

import com.marcusdev.automation.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final TestConfig config;

    private final By searchBox = By.id("small-searchterms");
    private final By searchButton = By.cssSelector("button.search-box-button");
    private final By cartLink = By.cssSelector("a.ico-cart");

    public HomePage(WebDriver driver, TestConfig config) {
        this.driver = driver;
        this.config = config;
        this.wait = new WebDriverWait(driver, config.timeout());
    }

    public HomePage open(String baseUrl) {
        driver.get(baseUrl);
        return waitUntilLoaded();
    }

    public HomePage waitUntilLoaded() {
        wait.until(ExpectedConditions.titleContains("nopCommerce"));
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        return this;
    }

    public String pageTitle() {
        return driver.getTitle();
    }

    public boolean isSearchAvailable() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).isDisplayed();
    }

    public SearchResultsPage searchFor(String searchText) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox)).sendKeys(searchText);
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();

        return new SearchResultsPage(driver, config).waitUntilLoaded();
    }

    public String cartText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartLink)).getText();
    }
}
