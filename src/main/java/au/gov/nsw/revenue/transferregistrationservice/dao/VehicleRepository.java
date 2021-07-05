package au.gov.nsw.revenue.transferregistrationservice.dao;

import au.gov.nsw.revenue.transferregistrationservice.entities.VehicleEntity;
import au.gov.nsw.revenue.transferregistrationservice.exception.VehicleAlreadyExistsException;
import au.gov.nsw.revenue.transferregistrationservice.exception.VehicleNotFoundException;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetails;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetailsForUnlink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApplicationScope
@Component
@Slf4j
public class VehicleRepository {

    private Map<String, VehicleEntity> vehicles = Collections.synchronizedMap(new HashMap<>());

    public VehicleEntity add(VehicleEntity vehicleEntity) {
        vehicles.merge(vehicleEntity.getRegistrationNumber(), vehicleEntity, (v1, v2) -> {
            throw new VehicleAlreadyExistsException("Registration Already Exists for Registration Number '" + vehicleEntity.getRegistrationNumber() + "'.");
        });
        return vehicleEntity;
    }

    public VehicleEntity findByRegistrationNumber(String registrationNumber) {
        VehicleEntity vehicleEntity = this.vehicles.get(registrationNumber);
        if(vehicleEntity!=null){
            return vehicleEntity;
        }
        throw new VehicleNotFoundException("Vehicle Not found for registration Number {} "+registrationNumber);
    }

    public VehicleEntity linkPerson(String registrationNumber, OwnerDetails ownerDetails){
        VehicleEntity vehicleEntity = this.vehicles.get(registrationNumber);
        vehicleEntity.setDob(ownerDetails.getDob());
        vehicleEntity.setEmailId(ownerDetails.getEmailId());
        vehicleEntity.setFirstName(ownerDetails.getFirstName());
        vehicleEntity.setLastName(ownerDetails.getLastName());
        vehicleEntity.setPostCode(ownerDetails.getPostCode());
        vehicleEntity.setStreetName(ownerDetails.getStreetName());
        this.vehicles.replace(registrationNumber, vehicleEntity);
        return vehicleEntity;
    }

    public VehicleEntity unlinkPerson(String registrationNumber, OwnerDetailsForUnlink ownerDetails){
        VehicleEntity vehicleEntity = this.vehicles.get(registrationNumber);
        this.vehicles.replace(registrationNumber, VehicleEntity.builder().year(vehicleEntity.getYear()).model(vehicleEntity.getModel()).vin(vehicleEntity.getVin()).make(vehicleEntity.getMake()).build());
        return this.vehicles.get(registrationNumber);
    }
}
