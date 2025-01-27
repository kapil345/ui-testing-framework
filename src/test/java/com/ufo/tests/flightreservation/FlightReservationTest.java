package com.ufo.tests.flightreservation;

import com.ufo.tests.AbstractTest;
import com.ufo.tests.flightreservation.model.FlightReservationTestData;
import com.ufo.util.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.flightreservation.*;

public class FlightReservationTest extends AbstractTest {


    private FlightReservationTestData testData;

     @BeforeTest
     @Parameters({"testDataPath"})
     public void setParameters(String testDataPath ){
        this.testData = JsonUtil.getTestData(testDataPath,FlightReservationTestData.class);
     }

     @Test
      public  void userRegistrationTest(){
         RegistrationPage registrationPage = new RegistrationPage(driver);
         registrationPage.goTO("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
         Assert.assertTrue(registrationPage.isAt());
         registrationPage.enterUserDetails(testData.firstName(),testData.lastName());
         registrationPage.enterUserCredentials(testData.email(),testData.password());
         registrationPage.enterAddress(testData.street(),testData.city(),testData.zip());
         registrationPage.register();
      }

      @Test(dependsOnMethods = {"userRegistrationTest"})
    public  void registrationConfirmationTest(){

          RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
          Assert.assertTrue( registrationConfirmationPage.isAt());
          registrationConfirmationPage.goToFlightSearch();
      }

      @Test(dependsOnMethods = {"registrationConfirmationTest"})
    public  void flightSearchTest(){
          FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
          Assert.assertTrue(flightSearchPage.isAt());
          flightSearchPage.selectPassengers(testData.passengersCount());
          flightSearchPage.selectDepartureCity("London");
          flightSearchPage.selectArrivalCity("Paris");
          flightSearchPage.setSelectFlightType();
          flightSearchPage.searchForFlight();
      }

      @Test(dependsOnMethods = "flightSearchTest")
    public  void flightSelectionTest(){
          FlightSelectionPage flightSelectionPage = new FlightSelectionPage(driver);
          Assert.assertTrue(flightSelectionPage.isAt());
          flightSelectionPage.selectFlights();
          flightSelectionPage.confirmFlight();
      }

      @Test(dependsOnMethods = "flightSelectionTest")
    public  void flightReservationConfirmationTest(){
          FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
          Assert.assertTrue(flightConfirmationPage.isAt());
          Assert.assertEquals(flightConfirmationPage.getPrice(),testData.expectedPrice());

      }
}

