package pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightSearchButton;


    // constructor to initialise web driver for this page
     public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
     }

     // for this page if it is loaded properly or not
    @Override
    public boolean isAt() {
         this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightSearchButton));
         return this.goToFlightSearchButton.isDisplayed();
    }

    // action for page
    public void goToFlightSearch(){
         this.goToFlightSearchButton.click();
    }

}
