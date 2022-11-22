package automation.pages;

import automation.drivers.DriverSingleton;
import automation.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailsPage {
    private WebDriver driver;

    public ProductDetailsPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_details']")
    private WebElement detailsContainer;

    @FindBy(css = "#inventory_item_container > div > div > div.inventory_details_desc_container > div.inventory_details_desc.large_size")
    private WebElement details;

    @FindBy(xpath = "//div[@class='inventory_details']//img")
    private WebElement productImage;

    public boolean validateDetailsContainerIsPresent(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(detailsContainer));
        if(details.isDisplayed() && productImage.isDisplayed()){
            return true;
        }
        return false;
    }
}
