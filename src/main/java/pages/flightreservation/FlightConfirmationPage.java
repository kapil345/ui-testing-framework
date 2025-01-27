package pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AbstractPage;

public class FlightConfirmationPage extends AbstractPage {
  private  static  final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationElement;

    @FindBy(xpath = "//h2[text()='Flights Confirmation']")
    private WebElement flightConfirmationTitle;

    @FindBy(css = "#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement totalPriceElement;

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationElement));
        return  this.flightConfirmationElement.isDisplayed();
    }

    public FlightConfirmationPage(WebDriver driver)
    {
        super(driver);
    }

    public String getPrice(){
        String price = this.totalPriceElement.getText();
        String confirmation = this.flightConfirmationElement.getText();
        log.info("Flight confirmation# : {}", confirmation);
        log.info("Total price : {}",price);
        return this.totalPriceElement.getText();
    }

}
