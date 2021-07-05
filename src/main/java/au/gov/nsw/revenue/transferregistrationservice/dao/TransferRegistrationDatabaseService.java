package au.gov.nsw.revenue.transferregistrationservice.dao;

import au.gov.nsw.revenue.transferregistrationservice.entities.PersonEntity;
import au.gov.nsw.revenue.transferregistrationservice.entities.VehicleEntity;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetails;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.SearchPersonRequest;
import org.springframework.stereotype.Service;

public interface TransferRegistrationDatabaseService {
    public PersonEntity createPerson(PersonEntity person);
    public VehicleEntity createVehicle(VehicleEntity vehicle);
    public PersonEntity retrievePerson(String emailId,String mobileNumber);
    public VehicleEntity retrieveVehicle(String registrationNumber);
    public VehicleEntity unlinkPerson(String registrationNumber, OwnerDetails ownerDetails);
    public VehicleEntity linkPerson(String registrationNumber, OwnerDetails ownerDetails);
}
