package com.marcusdev.automation.driver;

import com.marcusdev.automation.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {
    private DriverFactory() {
    }

    public static WebDriver createDriver(TestConfig config) {
        return switch (config.browser()) {
            case "firefox" -> new FirefoxDriver(firefoxOptions(config));
            case "edge" -> new EdgeDriver(edgeOptions(config));
            case "chrome" -> new ChromeDriver(chromeOptions(config));
            default -> throw new IllegalArgumentException("Unsupported browser: " + config.browser());
        };
    }

    private static ChromeOptions chromeOptions(TestConfig config) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--window-size=1440,1000");

        if (config.headless()) {
            options.addArguments("--headless=new");
        }

        return options;
    }

    private static FirefoxOptions firefoxOptions(TestConfig config) {
        FirefoxOptions options = new FirefoxOptions();

        if (config.headless()) {
            options.addArguments("-headless");
        }

        return options;
    }

    private static EdgeOptions edgeOptions(TestConfig config) {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--window-size=1440,1000");

        if (config.headless()) {
            options.addArguments("--headless=new");
        }

        return options;
    }
}
