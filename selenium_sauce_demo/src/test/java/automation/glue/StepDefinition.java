package automation.glue;

import automation.config.AutomationFrameworkConfiguration;
import automation.drivers.DriverSingleton;
import automation.pages.*;
import automation.utils.ConfigurationProperties;
import automation.utils.Constants;
import cucumber.api.java.After;
import cucumber.api.java.en.*;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(classes = AutomationFrameworkConfiguration.class)
public class StepDefinition {
    private WebDriver driver;
    private LogInPage logInPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private OverviewPage overviewPage;
    private ProductDetailsPage productDetailsPage;
    private CheckoutCompletePage checkoutCompletePage;

    @Autowired
    ConfigurationProperties configurationProperties;

    @Before
    public void initializeObjects(){
        DriverSingleton.getInstance(Constants.CHROME);
        //driver = DriverSingleton.getDriver();
        logInPage = new LogInPage();
        productsPage =  new ProductsPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        overviewPage = new OverviewPage();
        productDetailsPage = new ProductDetailsPage();
        checkoutCompletePage = new CheckoutCompletePage();
    }

    @Given("^I go to the Website")
    public void i_go_to_the_website(){
        driver = DriverSingleton.getDriver();
        driver.get(Constants.URL);
    }

    @When("^I specify my credentials and click on login")
    public void i_specify_my_credentials_and_click_on_login(){
        logInPage.logIn(configurationProperties.getStandardUser(), configurationProperties.getPassword());
    }

    @When("^I specify my credentials with locked user and click on login")
    public void i_specify_my_credentials_with_locked_user_and_click_on_login(){
        logInPage.logIn(configurationProperties.getLockedUser(), configurationProperties.getPassword());
    }

    @And("^I add three products to cart")
    public void i_add_three_products_to_cart(){
        productsPage.addBackpackToCart();
        productsPage.addJacketToCart();
        productsPage.addBikeLightToCart();
    }

    @And("^I remove one product")
    public void i_remove_one_product(){
        productsPage.removeBikeLightFromCart();
    }

    @And("^I go to cart page")
    public void i_go_to_cart_page(){
        productsPage.goToCart();
    }

    @And("^I add one product to cart")
    public void i_add_one_product_to_cart(){
        productsPage.addBackpackToCart();
    }

    @And("^I click on product name")
    public void i_click_on_product_name(){
        cartPage.goToFirstProductDetails();
    }

    @And("^I click on checkout button")
    public void i_click_on_checkout_button(){
        cartPage.checkoutProducts();
    }

    @And("^I fill all the fields")
    public void i_fill_all_the_fields(){
        checkoutPage.setPersonalInformation(configurationProperties.getFirstName(), configurationProperties.getLastName(), configurationProperties.getZipcode());
    }

    @And("^I click on continue button")
    public void i_click_on_continue_button(){
        checkoutPage.goToNextStep();
    }

    @And("^I click on finish button")
    public void i_click_on_finish_button(){
        overviewPage.finishCheckout();
    }

    @Then("^I can log into website")
    public void i_can_log_into_website(){
        assertEquals(configurationProperties.getProductsTitle(), productsPage.getPageTitle());
    }

    @Then("^I can see an error message")
    public void i_can_see_an_error_message(){
        assertEquals(configurationProperties.getLockedErrorMessage(), logInPage.getErrorMessage());
    }

    @Then("^I can see two products present")
    public void i_can_see_two_products_present(){
        assertEquals(configurationProperties.getTotalProductsOnCart(), cartPage.getTotalProducts());
    }

    @Then("^I can see product details")
    public void i_can_see_product_details(){
        assertTrue(productDetailsPage.validateDetailsContainerIsPresent());
    }

    @Then("^I can see the thanks to order message")
    public void i_can_the_thanks_to_order_message(){
        assertEquals(configurationProperties.getOrderCompleted(), checkoutCompletePage.getThanksTitle());
    }

    @Then("^Total price to pay is correct")
    public void total_price_to_pay_is_correct(){
        assertEquals(overviewPage.getTotalSummaryField(), overviewPage.getTotalToPay(configurationProperties.getTax(), overviewPage.getSubtotalPrice()));
    }

    @After
    public void closeObjects(){
        DriverSingleton.closeObjectInstance();
    }
}
