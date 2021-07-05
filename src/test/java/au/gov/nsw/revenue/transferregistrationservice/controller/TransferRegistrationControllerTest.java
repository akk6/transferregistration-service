package au.gov.nsw.revenue.transferregistrationservice.controller;

import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;
import au.gov.nsw.revenue.transferregistrationservice.service.TransferRegistrationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TransferRegistrationController.class)
public class TransferRegistrationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransferRegistrationService transferRegistrationService;

    @Autowired
    private ObjectMapper mapper;

    Person person;
    Vehicle vehicle;
    VehicleDetails vehicleDetails;
    SearchPersonRequest searchPersonRequest;
    private static final String MOCK_EMAIL_ID = "test@test.com";
    private static final String MOCK_MOB = "0452987654";
    private static final String MOCK_PWD = "MOCK_PWD";
    private static final String MOCK_MAKE = "Ford";
    private static final String MOCK_MODEL = "Focus";
    private static final String MOCK_VIN = "A234567GDF1";
    private static final String MOCK_REGISTRATION = "DBG67A";
    private static final String MOCK_FIRSTNAME = "TestFN";
    private static final String MOCK_LASTNAME = "TestLN";
    private static final String MOCK_STREETNAME = "StreetName";

    @BeforeEach
    public void setUp() {
        person = new Person();
        person.setEmailId(MOCK_EMAIL_ID);
        person.setMobileNumber(MOCK_MOB);
        person.setPassword(MOCK_PWD);

        vehicle = new Vehicle();
        vehicleDetails = new VehicleDetails();
        vehicleDetails.setMake(MOCK_MAKE);
        vehicleDetails.setModel(MOCK_MODEL);
        vehicleDetails.setVin(MOCK_VIN);
        vehicleDetails.setRegistrationNumber(MOCK_REGISTRATION);
        vehicleDetails.setYear(2010);
        vehicle.setVehicleDetails(vehicleDetails);
        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setDob(LocalDate.parse("2020-01-08"));
        ownerDetails.setFirstName(MOCK_FIRSTNAME);
        ownerDetails.setLastName(MOCK_LASTNAME);
        ownerDetails.setStreetName(MOCK_STREETNAME);
        ownerDetails.setEmailId(MOCK_EMAIL_ID);
        vehicle.setOwnerDetails(ownerDetails);

        searchPersonRequest= new SearchPersonRequest();
        searchPersonRequest.setEmailId(MOCK_EMAIL_ID);
        searchPersonRequest.setMobileNumber(MOCK_MOB);

    }

    @Test
    public void givenPersonDetails_whenPostPersonNonExistent_thenReturnPerson() throws Exception {
        when(transferRegistrationService.createPerson(person)).thenReturn(person);
        String json = mapper.writeValueAsString(person);
        mvc.perform(MockMvcRequestBuilders.post("/transfer-registration/v1/person").content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenVehicleDetails_whenPostVehicleNonExistent_thenReturnVehicle() throws Exception {
        when(transferRegistrationService.createVehicle(vehicleDetails)).thenReturn(vehicleDetails);
        String json = mapper.writeValueAsString(vehicleDetails);
        mvc.perform(MockMvcRequestBuilders.post("/transfer-registration/v1/vehicle").content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenPerson_whenPostVehicle_thenReturnPerson() throws Exception {
        when(transferRegistrationService.retrievePerson(searchPersonRequest)).thenReturn(person);
        String json = mapper.writeValueAsString(searchPersonRequest);
        mvc.perform(MockMvcRequestBuilders.post("/transfer-registration/v1/searchPerson").content(json)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenVehicle_whenGetVehicle_thenReturnVehicle() throws Exception {
        when(transferRegistrationService.retrieveVehicle(MOCK_REGISTRATION)).thenReturn(vehicle);
        mvc.perform(MockMvcRequestBuilders.get("/transfer-registration/v1/searchVehicle?registrationNumber="+MOCK_REGISTRATION)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
