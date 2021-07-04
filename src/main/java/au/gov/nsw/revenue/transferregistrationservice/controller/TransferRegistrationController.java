package au.gov.nsw.revenue.transferregistrationservice.controller;

import au.gov.nsw.revenue.transferregistrationservice.openapi.api.TransferRegistrationApi;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.Person;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.SearchPersonRequest;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.Vehicle;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "Transfer Registration API",description = "The API endpoints enables a client to link or unlink a Person to a Vehicle.")
public class TransferRegistrationController implements TransferRegistrationApi {

    @Override
    public ResponseEntity<Person> createPerson(Person person) {
        log.info("createPerson called ");
        return ResponseEntity.status(HttpStatus.CREATED).body(person);
    }

    @Override
    public ResponseEntity<Vehicle> createVehicle(Vehicle vehicle) {
        return null;
    }

    @Override
    public ResponseEntity<Person> retrievePerson(SearchPersonRequest searchPersonRequest) {
        return null;
    }

    @Override
    public ResponseEntity<Vehicle> retrieveVehicle(String registrationNumber) {
        return null;
    }
}
