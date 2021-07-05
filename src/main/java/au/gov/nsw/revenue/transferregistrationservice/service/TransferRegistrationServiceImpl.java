package au.gov.nsw.revenue.transferregistrationservice.service;

import au.gov.nsw.revenue.transferregistrationservice.dao.TransferRegistrationDatabaseService;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;
import au.gov.nsw.revenue.transferregistrationservice.utils.TransferRegistrationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferRegistrationServiceImpl implements TransferRegistrationService{

    @Autowired
    private TransferRegistrationDatabaseService transferRegistrationDatabaseService;

    @Override
    public Person createPerson(Person person) {
        return TransferRegistrationUtils.mapPersonFromEntity(transferRegistrationDatabaseService.createPerson(TransferRegistrationUtils.mapPersonEntityFromRest(person)));
    }

    @Override
    public VehicleDetails createVehicle(VehicleDetails vehicleDetails) {
        return TransferRegistrationUtils.mapVehicleDetailsFromEntity(transferRegistrationDatabaseService.createVehicle(TransferRegistrationUtils.mapVehicleEntityFromRestVehicleDetails(vehicleDetails)));
    }

    @Override
    public Person retrievePerson(SearchPersonRequest searchPersonRequest) {
        return TransferRegistrationUtils.mapPersonFromEntity(transferRegistrationDatabaseService.retrievePerson(searchPersonRequest.getEmailId(),searchPersonRequest.getMobileNumber()));
    }

    @Override
    public Vehicle retrieveVehicle(String registrationNumber) {
        return TransferRegistrationUtils.mapVehicleFromEntity(transferRegistrationDatabaseService.retrieveVehicle(registrationNumber));
    }

    @Override
    public Vehicle unlinkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        return TransferRegistrationUtils.mapVehicleFromEntity(transferRegistrationDatabaseService.unlinkPerson(registrationNumber,ownerDetails));
    }

    @Override
    public Vehicle linkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        return TransferRegistrationUtils.mapVehicleFromEntity(transferRegistrationDatabaseService.linkPerson(registrationNumber,ownerDetails));
    }
}
