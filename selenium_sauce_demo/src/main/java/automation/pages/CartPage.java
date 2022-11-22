package automation.pages;

import automation.drivers.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import automation.utils.Constants;

import java.util.List;

public class CartPage {
    private WebDriver driver;

    public CartPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_name']")
    private List<WebElement> allProductsNames;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;


    public String getTotalProducts(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        List<WebElement> products = allProductsNames;
        wait.until(ExpectedConditions.visibilityOfAllElements(products));
        String total = String.valueOf(products.size());
        return total;
    }

    public void goToFirstProductDetails(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        for(WebElement name: allProductsNames){
            wait.until(ExpectedConditions.elementToBeClickable(name));
            name.click();
        }
    }

    public void checkoutProducts(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkoutButton.click();
    }
}
