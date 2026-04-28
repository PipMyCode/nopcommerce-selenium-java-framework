package com.marcusdev.automation.config;

import java.time.Duration;

public final class TestConfig {
    private static final String DEFAULT_BASE_URL = "https://demo.nopcommerce.com/";
    private static final int DEFAULT_TIMEOUT_SECONDS = 10;

    public String browser() {
        return System.getProperty("browser", "chrome").trim().toLowerCase();
    }

    public boolean headless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    public String baseUrl() {
        String baseUrl = System.getProperty("baseUrl", DEFAULT_BASE_URL).trim();
        return baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
    }

    public Duration timeout() {
        String timeoutSeconds = System.getProperty("timeoutSeconds", String.valueOf(DEFAULT_TIMEOUT_SECONDS));
        return Duration.ofSeconds(Integer.parseInt(timeoutSeconds));
    }
}
