package automation.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("framework.properties")
public class ConfigurationProperties {

    @Value("${standard_user}")
    private String standardUser;

    @Value("${locked_user}")
    private String lockedUser;

    @Value("${password}")
    private String password;

    @Value("${products_title}")
    private String productsTitle;

    @Value("${locked_error_message}")
    private String lockedErrorMessage;

    @Value("${total_products_on_cart}")
    private String totalProductsOnCart;

    @Value("${first_name}")
    private String firstName;

    @Value("${last_name}")
    private String lastName;

    @Value("${zipcode}")
    private String zipcode;

    @Value("${tax}")
    private String tax;

    @Value("${order_completed}")
    private String orderCompleted;


    public String getStandardUser() {
        return standardUser;
    }

    public String getLockedUser() {
        return lockedUser;
    }

    public String getPassword() {
        return password;
    }

    public String getProductsTitle() {
        return productsTitle;
    }

    public String getLockedErrorMessage() {
        return lockedErrorMessage;
    }

    public String getTotalProductsOnCart() {
        return totalProductsOnCart;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getTax() {
        return tax;
    }

    public String getOrderCompleted() {
        return orderCompleted;
    }
}
