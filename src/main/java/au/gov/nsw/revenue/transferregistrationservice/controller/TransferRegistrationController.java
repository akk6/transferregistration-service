package au.gov.nsw.revenue.transferregistrationservice.controller;

import au.gov.nsw.revenue.transferregistrationservice.openapi.api.TransferRegistrationApi;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.*;
import au.gov.nsw.revenue.transferregistrationservice.service.TransferRegistrationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        log.info("createPerson Initiated for email Id {} ",person.getEmailId());
        log.debug("createPerson Initiated for Mobile Number {} ",person.getMobileNumber());
        Person personReceived = transferRegistrationService.createPerson(person);
        log.info("createPerson completed for email Id {} ",person.getEmailId());
        return ResponseEntity.status(HttpStatus.CREATED).body(personReceived);
    }

    @Override
    public ResponseEntity<VehicleDetails> createVehicle(VehicleDetails vehicleDetails) {
        log.info("createVehicle Initiated for Registration Number {} ",vehicleDetails.getRegistrationNumber());
        log.debug("createVehicle Initiated for Make {} ",vehicleDetails.getMake());
        VehicleDetails vehicleResponse = transferRegistrationService.createVehicle(vehicleDetails);
        log.info("createVehicle completed for Registration Number {} ",vehicleDetails.getRegistrationNumber());
        return ResponseEntity.status(HttpStatus.CREATED).body(vehicleResponse);
    }


    @Override
    public ResponseEntity<Person> retrievePerson(SearchPersonRequest searchPersonRequest) {
        log.info("retrievePerson Initiated for Email Id {} ",searchPersonRequest.getEmailId());
        log.debug("retrievePerson Initiated for Mobile Number {} ",searchPersonRequest.getMobileNumber());
        Person personResponse = transferRegistrationService.retrievePerson(searchPersonRequest);
        log.info("retrievePerson Completed for Email Id {} ",searchPersonRequest.getEmailId());
        return ResponseEntity.status(HttpStatus.OK).body(personResponse);
    }

    @Override
    public ResponseEntity<Vehicle> retrieveVehicle(String registrationNumber) {
        log.info("retrieveVehicle Initiated for Registration Number {} ",registrationNumber);
        Vehicle vehicleResponse = transferRegistrationService.retrieveVehicle(registrationNumber);
        log.info("retrieveVehicle Completed for Registration Number {} ",registrationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }

    @Override
    public ResponseEntity<Vehicle> linkPerson(String registrationNumber, OwnerDetails ownerDetails) {
        log.info("linkPerson Initiated for Registration Number {} and Owner {} ",registrationNumber,ownerDetails.getEmailId());
        log.debug("linkPerson Initiated for Last Name {} ",ownerDetails.getLastName());
        Vehicle vehicleResponse = transferRegistrationService.linkPerson(registrationNumber,ownerDetails);
        log.info("linkPerson Completed for Registration Number {} and Owner {} ",registrationNumber,ownerDetails.getEmailId());
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }

    @Override
    public ResponseEntity<Vehicle> unlinkPerson(String registrationNumber, OwnerDetailsForUnlink ownerDetails) {
        log.info("unlinkPerson Initiated for Registration Number {} and Owner {} ",registrationNumber,ownerDetails.getEmailId());
        log.debug("unlinkPerson Initiated for Last Name {} ",ownerDetails.getLastName());
        Vehicle vehicleResponse = transferRegistrationService.unlinkPerson(registrationNumber,ownerDetails);
        log.info("unlinkPerson Completed for Registration Number {} and Owner {} ",registrationNumber,ownerDetails.getEmailId());
        return ResponseEntity.status(HttpStatus.OK).body(vehicleResponse);
    }
}
