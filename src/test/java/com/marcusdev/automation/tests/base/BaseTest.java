package com.marcusdev.automation.tests.base;

import com.marcusdev.automation.config.TestConfig;
import com.marcusdev.automation.driver.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public abstract class BaseTest {
    private WebDriver driver;
    private TestConfig config;

    @BeforeEach
    void setUp() {
        config = new TestConfig();
        driver = DriverFactory.createDriver(config);
        driver.manage().timeouts().pageLoadTimeout(config.timeout());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected WebDriver driver() {
        return driver;
    }

    protected TestConfig config() {
        return config;
    }
}
