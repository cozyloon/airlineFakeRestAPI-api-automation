package tests;

import io.restassured.response.Response;
import model.*;
import org.testng.annotations.Test;
import util.ResponseUtil;
import util.TestBase;
import static data.AssertionErrorMessages.*;
import static data.RequestComponents.*;

public class AirlineDetailsTest extends TestBase {
    String passengerID = "";

    @Test(description = "TC_1", alwaysRun = true)
    public void testAirlineDetailsAreReturnedByID() {

        Response response = restAssuredWrapper.get(AIRLINE_BASE_PATH + AIRLINE_ID);
        AirlineResponseDetails airlineResponseDetails = ResponseUtil.mapResponseToModel(response, AirlineResponseDetails.class);

        softAssert.assertEquals(airlineResponseDetails.getId(), 12, AIRLINE_ID_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getName(), "Sri Lankan Airways", AIRLINE_NAME_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getCountry(), "Sri Lanka", AIRLINE_COUNTRY_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getLogo(), "https://upload.wikimedia.org/wikipedia/en/thumb/9/9b/Qatar_Airways_Logo.svg/sri_lanka.png", AIRLINE_LOGO_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getSlogan(), "From Sri Lanka", AIRLINE_SLOGAN_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getHead_quaters(), "Katunayake, Sri Lanka", AIRLINE_HEAD_QUARTERS_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getWebsite(), "www.srilankaairways.com", AIRLINE_WEBSITE_NOT_MATCH);
        softAssert.assertEquals(airlineResponseDetails.getEstablished(), "1990", AIRLINE_ESTABLISHED_NOT_MATCH);
        softAssert.assertAll();
    }

    @Test(description = "TC_2", alwaysRun = true)
    public void testCreatePassenger() {

        PassengerRequestDetails requestDetails = new PassengerRequestDetails();
        requestDetails.setName("John Doe");
        requestDetails.setTrips(250);
        requestDetails.setAirline(12);

        restAssuredWrapper.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        restAssuredWrapper.setBody(requestDetails);

        Response response = restAssuredWrapper.post(PASSENGER_BASE_PATH);
        PassengerResponseDetails passengerResponseDetails = ResponseUtil.mapResponseToModel(response, PassengerResponseDetails.class);
        passengerID = passengerResponseDetails.get_id();
        softAssert.assertEquals(passengerResponseDetails.getName(), "John Doe", PASSENGER_NAME_NOT_MATCH);
        softAssert.assertEquals(passengerResponseDetails.getTrips(), 250, PASSENGER_TRIPS_NOT_MATCH);
        softAssert.assertEquals(passengerResponseDetails.getAirline().get(0).getId(), 12, PASSENGER_AIRLINE_NOT_MATCH);
        softAssert.assertAll();
    }

    @Test(description = "TC_3", alwaysRun = true, dependsOnMethods = "testCreatePassenger")
    public void testUpdatePassengerDetails() {

        PassengerRequestDetails requestDetails = new PassengerRequestDetails();
        requestDetails.setName("Chathumal Sangeeth");
        requestDetails.setTrips(10);
        requestDetails.setAirline(1);

        restAssuredWrapper.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        restAssuredWrapper.setBody(requestDetails);

        Response response = restAssuredWrapper.put(PASSENGER_BASE_PATH + passengerID);
        PassengerUpdateResponse updateResponse = ResponseUtil.mapResponseToModel(response, PassengerUpdateResponse.class);
        softAssert.assertEquals(updateResponse.getMessage(), PASSENGER_UPDATED_MESSAGE, PASSENGER_UPDATED_MESSAGE_NOT_MATCH);
        softAssert.assertAll();
    }

    @Test(description = "TC_4", alwaysRun = true, dependsOnMethods = "testUpdatePassengerDetails")
    public void testGetUpdatedPassengerDetailsByID() {

        Response response = restAssuredWrapper.get(PASSENGER_BASE_PATH + passengerID);
        PassengerResponseDetails responseDetails = ResponseUtil.mapResponseToModel(response, PassengerResponseDetails.class);
        softAssert.assertEquals(responseDetails.get_id(), passengerID, PASSENGER_ID_NOT_MATCH);
        softAssert.assertEquals(responseDetails.getName(), "Chathumal Sangeeth", PASSENGER_NAME_NOT_MATCH);
        softAssert.assertEquals(responseDetails.getTrips(), 10, PASSENGER_TRIPS_NOT_MATCH);
        softAssert.assertEquals(responseDetails.getAirline().get(0).getId(), 1, PASSENGER_AIRLINE_ID_NOT_MATCH);
        softAssert.assertAll();
    }

    @Test(description = "TC_5", alwaysRun = true, dependsOnMethods = "testGetUpdatedPassengerDetailsByID")
    public void testDeleteCreatedPassengerByID() {

        Response response = restAssuredWrapper.delete(PASSENGER_BASE_PATH + passengerID);
        PassengerDeleteResponse responseDetails = ResponseUtil.mapResponseToModel(response, PassengerDeleteResponse.class);
        softAssert.assertEquals(responseDetails.getMessage(), PASSENGER_DELETED_MESSAGE, PASSENGER_DELETED_MESSAGE_NOT_MATCH);
        softAssert.assertAll();
    }
}
