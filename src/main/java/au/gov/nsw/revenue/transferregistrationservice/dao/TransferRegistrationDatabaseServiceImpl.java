package au.gov.nsw.revenue.transferregistrationservice.dao;

import au.gov.nsw.revenue.transferregistrationservice.entities.PersonEntity;
import au.gov.nsw.revenue.transferregistrationservice.entities.VehicleEntity;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetails;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetailsForUnlink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferRegistrationDatabaseServiceImpl implements TransferRegistrationDatabaseService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public PersonEntity createPerson(PersonEntity person) {
        return personRepository.add(person);
    }

    @Override
    public VehicleEntity createVehicle(VehicleEntity vehicle) {
       return vehicleRepository.add(vehicle);
    }

    @Override
    public PersonEntity retrievePerson(String emailId,String mobileNumber) {
        return personRepository.findByEmailIdAmdMobileNumber(emailId,mobileNumber);
    }

    @Override
    public VehicleEntity retrieveVehicle(String registrationNumber) {
        return vehicleRepository.findByRegistrationNumber(registrationNumber);
    }

    @Override
    public VehicleEntity linkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        return vehicleRepository.linkPerson(registrationNumber,ownerDetails);
    }

    @Override
    public VehicleEntity unlinkPerson(String registrationNumber, OwnerDetailsForUnlink ownerDetails) {
        return vehicleRepository.unlinkPerson(registrationNumber,ownerDetails);
    }
}
