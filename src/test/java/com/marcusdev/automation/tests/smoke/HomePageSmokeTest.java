package com.marcusdev.automation.tests.smoke;

import com.marcusdev.automation.pages.HomePage;
import com.marcusdev.automation.pages.SearchResultsPage;
import com.marcusdev.automation.tests.base.BaseTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class HomePageSmokeTest extends BaseTest {

    @Test
    @Tag("smoke")
    void homePageLoadsWithSearchAndCart() {
        HomePage homePage = new HomePage(driver(), config()).open(config().baseUrl());

        assertThat(homePage.pageTitle()).contains("nopCommerce");
        assertThat(homePage.isSearchAvailable()).isTrue();
        assertThat(homePage.cartText()).contains("Shopping cart");
    }

    @Test
    @Tag("smoke")
    void userCanSearchForAProduct() {
        String searchTerm = "computer";

        SearchResultsPage resultsPage = new HomePage(driver(), config())
                .open(config().baseUrl())
                .searchFor(searchTerm);

        assertThat(resultsPage.searchTermValue()).isEqualTo(searchTerm);
        assertThat(resultsPage.productResultCount()).isGreaterThan(0);
    }
}
