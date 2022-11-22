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

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span")
    private WebElement pageTitle;

    @FindBy(xpath = "//div[@id='shopping_cart_container']/a")
    private WebElement cartButton;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackProductButton;

    @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    private WebElement addJacketProductButton;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement addBikeLightProductButton;

    @FindBy(id = "remove-sauce-labs-bike-light")
    private WebElement removeBikeLightProductButton;

    public String getPageTitle(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(pageTitle));
        return pageTitle.getText();
    }

    public void addBackpackToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(addBackpackProductButton));
        addBackpackProductButton.click();
    }

    public void addJacketToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(addJacketProductButton));
        addJacketProductButton.click();
    }


    public void addBikeLightToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(addBikeLightProductButton));
        addBikeLightProductButton.click();
    }

    public void removeBikeLightFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(removeBikeLightProductButton));
        removeBikeLightProductButton.click();
    }

    public void goToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartButton.click();
    }

}
