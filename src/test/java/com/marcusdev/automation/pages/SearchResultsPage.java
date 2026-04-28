package com.marcusdev.automation.pages;

import com.marcusdev.automation.config.TestConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By searchKeywordInput = By.id("q");
    private final By productItems = By.cssSelector(".product-grid .item-box");

    public SearchResultsPage(WebDriver driver, TestConfig config) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, config.timeout());
    }

    public SearchResultsPage waitUntilLoaded() {
        wait.until(ExpectedConditions.titleContains("Search"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchKeywordInput));
        return this;
    }

    public String searchTermValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(searchKeywordInput))
                .getAttribute("value");
    }

    public int productResultCount() {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(productItems, 0));
        return driver.findElements(productItems).size();
    }
}
