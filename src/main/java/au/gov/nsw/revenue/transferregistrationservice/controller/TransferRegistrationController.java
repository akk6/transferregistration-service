package au.gov.nsw.revenue.transferregistrationservice.controller;

import au.gov.nsw.revenue.transferregistrationservice.openapi.api.TransferRegistrationApi;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;
import au.gov.nsw.revenue.transferregistrationservice.service.TransferRegistrationService;
import au.gov.nsw.revenue.transferregistrationservice.service.TransferRegistrationServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "Transfer Registration API",description = "The API endpoints enables a client to link or unlink a Person to a Vehicle.")
public class TransferRegistrationController implements TransferRegistrationApi {

    @Autowired
    private TransferRegistrationService transferRegistrationService;

    @Override
    public ResponseEntity<Person> createPerson(Person person) {
        log.info("createPerson called ");
        Person personReceived = transferRegistrationService.createPerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(personReceived);
    }

    @Override
    public ResponseEntity<VehicleDetails> createVehicle(VehicleDetails vehicleDetails) {
        VehicleDetails vehicleResponse = transferRegistrationService.createVehicle(vehicleDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleResponse);
    }


    @Override
    public ResponseEntity<Person> retrievePerson(SearchPersonRequest searchPersonRequest) {
        Person personResponse = transferRegistrationService.retrievePerson(searchPersonRequest);
        return ResponseEntity.status(HttpStatus.OK).body(personResponse);
    }

    @Override
    public ResponseEntity<Vehicle> retrieveVehicle(String registrationNumber) {
        Vehicle vehicleResponse = transferRegistrationService.retrieveVehicle(registrationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }

    @Override
    public ResponseEntity<Vehicle> linkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        Vehicle vehicleResponse = transferRegistrationService.linkPerson(registrationNumber,ownerDetails);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }

    @Override
    public ResponseEntity<Vehicle> unlinkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        Vehicle vehicleResponse = transferRegistrationService.unlinkPerson(registrationNumber,ownerDetails);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }
}
