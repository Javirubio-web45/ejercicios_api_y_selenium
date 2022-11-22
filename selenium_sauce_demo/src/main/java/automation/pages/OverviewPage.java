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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OverviewPage {
    private WebDriver driver;

    public OverviewPage(){
        this.driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    private List<WebElement> allProductsPrices;

    @FindBy(css = "#checkout_summary_container > div > div.summary_info > div.summary_total_label")
    private WebElement totalSummary;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public String getSubtotalPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        float total = 0.0f;
        for(WebElement name: allProductsPrices){
            wait.until(ExpectedConditions.elementToBeClickable(name));
            String s = name.getText();
            Pattern pattern = Pattern.compile("(?<=\\$)([0-9]+[.][0-9]+)");
            Matcher matcher = pattern.matcher(s);
            if(matcher.find()){
                total += Float.parseFloat(matcher.group());
            }

        }
        String totalString = Float.toString(total);
        String result = "Item total: $"+totalString;
        return result;
    }

    public String getTotalToPay(String tax, String total){
        Float taxFloat = Float.parseFloat(tax);
        Pattern pattern = Pattern.compile("(?<=\\$)([0-9]+[.][0-9]+)");
        Matcher matcher = pattern.matcher(total);
        if(matcher.find()){
            Float totalFloat = Float.parseFloat(matcher.group());
            Float sumTotal = totalFloat+taxFloat;
            String sumTotalString = Float.toString(sumTotal);
            String result = "Total: $"+sumTotalString;
            return result;
        }
        return total;
    }

    public String getTotalSummaryField(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(totalSummary));
        return totalSummary.getText();
    }

    public void finishCheckout(){
        WebDriverWait wait = new WebDriverWait(driver, Constants.TIMEOUT);
        wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishButton.click();
    }
}
