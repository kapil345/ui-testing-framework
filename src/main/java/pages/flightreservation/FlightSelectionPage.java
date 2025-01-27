package pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.AbstractPage;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightSelectionPage extends AbstractPage {

    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightOptions;

    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightOptions;

    @FindBy(xpath="//button[text()=\"Confirm Flights\"]")
    private WebElement confirmedFlightButton;



    public FlightSelectionPage(WebDriver driver){
        super(driver);
    }

    // check page is displayed
    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmedFlightButton));
        return this.confirmedFlightButton.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0,departureFlightOptions.size());
        this.departureFlightOptions.get(random).click();
        this.arrivalFlightOptions.get(random).click();
    }

    public void confirmFlight(){
        this.confirmedFlightButton.click();
    }



}
