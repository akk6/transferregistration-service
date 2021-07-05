package au.gov.nsw.revenue.transferregistrationservice.service;

import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;

public interface TransferRegistrationService {
    public Person createPerson(Person person);
    public VehicleDetails createVehicle(VehicleDetails vehicle);
    public Person retrievePerson(SearchPersonRequest searchPersonRequest);
    public Vehicle retrieveVehicle(String registrationNumber);
    public Vehicle unlinkPerson(String registrationNumber, OwnerDetails ownerDetails);
    public Vehicle linkPerson(String registrationNumber, OwnerDetails ownerDetails);
}
