package pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.AbstractPage;

public class FlightSearchPage extends AbstractPage  {

    @FindBy(id="passengers")
    private  WebElement passengersSelect;

    @FindBy(id="search-flights")
    private WebElement searchFlightButton;

    @FindBy(id="oneway")
    private  WebElement tripType;

    @FindBy(id="depart-from")
    private WebElement departureDropdown;

    @FindBy(id="arrive-in")
    private WebElement arrivalDropDown;

    @FindBy(xpath = "//label[@class='form-check-label' and contains(normalize-space(text()), 'First')]")
    private WebElement selectFlightType;
    // or use this locator //label[@class='form-check-label' and contains(text(), 'First')

    public FlightSearchPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.passengersSelect));
        return this.passengersSelect.isDisplayed();
    }

    // you can select by value or text , selenium provides a select class for drop down to do this
    public void selectPassengers(String noOfPassengers){

        Select passengers = new Select(this.passengersSelect);
        passengers.selectByValue(noOfPassengers);
        this.passengersSelect.click();
    }

    public void searchForFlight(){
        this.searchFlightButton.click();
    }

    // use select class wrapper if you see in HTML CODE select tag and options
    public void selectDepartureCity(String city){
        Select departure = new Select(this.departureDropdown);
        departure.selectByValue(city);
    }

    public void selectArrivalCity(String city){
        Select arrival = new Select(this.arrivalDropDown);
        arrival.selectByValue(city);
    }

    public void setSelectFlightType(){
        this.selectFlightType.click();
    }
}
