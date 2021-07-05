package au.gov.nsw.revenue.transferregistrationservice.service;

import au.gov.nsw.revenue.transferregistrationservice.dao.TransferRegistrationDatabaseService;
import au.gov.nsw.revenue.transferregistrationservice.entities.PersonEntity;
import au.gov.nsw.revenue.transferregistrationservice.entities.VehicleEntity;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;
import au.gov.nsw.revenue.transferregistrationservice.utils.TransferRegistrationUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class TransferRegistrationServiceTest {

    @Mock
    private TransferRegistrationDatabaseService transferRegistrationDatabaseService;

    @InjectMocks
    private TransferRegistrationServiceImpl transferRegistrationServiceImpl;

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

    Person person;
    Vehicle vehicle;
    VehicleDetails vehicleDetails;
    SearchPersonRequest searchPersonRequest;
    OwnerDetails ownerDetails;
    OwnerDetailsForUnlink ownerDetailsForUnlink;
    VehicleEntity vehicleEntity;

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
        ownerDetails = new OwnerDetails();
        ownerDetails.setDob(LocalDate.parse("2020-01-08"));
        ownerDetails.setFirstName(MOCK_FIRSTNAME);
        ownerDetails.setLastName(MOCK_LASTNAME);
        ownerDetails.setStreetName(MOCK_STREETNAME);
        ownerDetails.setEmailId(MOCK_EMAIL_ID);
        ownerDetails.setPostCode(BigDecimal.valueOf(2144));
        vehicle.setOwnerDetails(ownerDetails);

        ownerDetailsForUnlink = new OwnerDetailsForUnlink();
        ownerDetailsForUnlink.setDob(LocalDate.parse("2020-01-08"));
        ownerDetailsForUnlink.setEmailId(MOCK_EMAIL_ID);
        ownerDetailsForUnlink.setFirstName(MOCK_FIRSTNAME);
        ownerDetailsForUnlink.setLastName(MOCK_LASTNAME);

        searchPersonRequest= new SearchPersonRequest();
        searchPersonRequest.setEmailId(MOCK_EMAIL_ID);
        searchPersonRequest.setMobileNumber(MOCK_MOB);

        vehicleEntity = new VehicleEntity();
        vehicleEntity.setStreetName(MOCK_STREETNAME);
        vehicleEntity.setLastName(MOCK_LASTNAME);
        vehicleEntity.setEmailId(MOCK_EMAIL_ID);
        vehicleEntity.setFirstName(MOCK_FIRSTNAME);
        vehicleEntity.setDob(LocalDate.parse("2020-01-08"));
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void givenPersonDetails_whenPersistPersonData_thenReturnPersonEntity() {
        PersonEntity personEntity = TransferRegistrationUtils.mapPersonEntityFromRest(person);
        when(transferRegistrationDatabaseService.createPerson(personEntity)).thenReturn(personEntity);
        Person personReceived = transferRegistrationServiceImpl.createPerson(person);
        assertNotNull(personReceived);
    }

    @Test
    public void givenVehicleDetails_whenPersistVehicleData_thenReturnVehicleEntity() {
        VehicleEntity vehicleEntity = TransferRegistrationUtils.mapVehicleEntityFromRestVehicleDetails(vehicleDetails);
        when(transferRegistrationDatabaseService.createVehicle(vehicleEntity)).thenReturn(vehicleEntity);
        VehicleDetails vehicleReceived = transferRegistrationServiceImpl.createVehicle(vehicleDetails);
        assertNotNull(vehicleReceived);
    }

    @Test
    public void givenPersonDetails_whenSearchPerson_thenReturnPersonEntity() {
        PersonEntity personEntity = TransferRegistrationUtils.mapPersonEntityFromRest(person);
        when(transferRegistrationDatabaseService.retrievePerson(anyString(),anyString())).thenReturn(personEntity);
        Person personReceived = transferRegistrationServiceImpl.retrievePerson(searchPersonRequest);
        assertNotNull(personReceived);
    }

    @Test
    public void givenVehicleDetails_whenSearchVehicle_thenReturnVehicleEntity() {
        when(transferRegistrationDatabaseService.retrieveVehicle(anyString())).thenReturn(vehicleEntity);
        Vehicle vehicleReceived = transferRegistrationServiceImpl.retrieveVehicle(MOCK_REGISTRATION);
        assertNotNull(vehicleReceived);
    }


    @Test
    public void givenRegNumberOwnerDetails_whenLinkPerson_thenReturnVehicleEntity() {
        ownerDetails.setEmailId("testChange@test.com");
        vehicleEntity.setEmailId(null);
        when(transferRegistrationDatabaseService.retrieveVehicle(anyString())).thenReturn(vehicleEntity);
        when(transferRegistrationDatabaseService.linkPerson(anyString(),any())).thenReturn(vehicleEntity);
        Vehicle vehicleReceived = transferRegistrationServiceImpl.linkPerson(MOCK_REGISTRATION,ownerDetails);
        assertNotNull(vehicleReceived);
    }


    @Test
    public void givenRegNumberOwnerDetails_whenunlinkPerson_thenReturnVehicleEntity() {
        when(transferRegistrationDatabaseService.retrieveVehicle(anyString())).thenReturn(vehicleEntity);
        when(transferRegistrationDatabaseService.unlinkPerson(anyString(),any())).thenReturn(vehicleEntity);
        Vehicle vehicleReceived = transferRegistrationServiceImpl.unlinkPerson(MOCK_REGISTRATION,ownerDetailsForUnlink);
        assertNotNull(vehicleReceived);
    }
}
