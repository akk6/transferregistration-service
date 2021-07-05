package au.gov.nsw.revenue.transferregistrationservice.utils;

import au.gov.nsw.revenue.transferregistrationservice.entities.PersonEntity;
import au.gov.nsw.revenue.transferregistrationservice.entities.VehicleEntity;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.OwnerDetails;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.Person;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.Vehicle;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.VehicleDetails;

public class TransferRegistrationUtils {

    public static PersonEntity mapPersonEntityFromRest(Person person){
        if(person==null){
            return null;
        }
        return PersonEntity.builder().mobileNumber(person.getMobileNumber()).emailId(person.getEmailId()).password(person.getPassword()).build();
    }

    public static Person mapPersonFromEntity(PersonEntity personEntity){
        if(personEntity==null){
            return null;
        }
        Person person = new Person();
        person.setEmailId(personEntity.getEmailId());
        person.setMobileNumber(personEntity.getMobileNumber());
        return person;
    }

    public static VehicleEntity mapVehicleEntityFromRestVehicleDetails(VehicleDetails vehicleDetails){
        if(vehicleDetails==null){
            return null;
        }
        return VehicleEntity.builder().registrationNumber(vehicleDetails.getRegistrationNumber())
                .make(vehicleDetails.getMake()).vin(vehicleDetails.getVin()).model(vehicleDetails.getModel()).year(vehicleDetails.getYear())
                .build();
    }

    public static VehicleDetails mapVehicleDetailsFromEntity(VehicleEntity vehicleEntity){
        if(vehicleEntity==null){
            return null;
        }
        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setModel(vehicleEntity.getModel());
        vehicleDetails.setMake(vehicleEntity.getMake());
        vehicleDetails.setRegistrationNumber(vehicleEntity.getRegistrationNumber());
        vehicleDetails.setVin(vehicleEntity.getVin());
        vehicleDetails.year(vehicleEntity.getYear());
        return vehicleDetails;
    }

    public static Vehicle mapVehicleFromEntity(VehicleEntity vehicleEntity){
        if(vehicleEntity==null){
            return null;
        }
        Vehicle vehicle = new Vehicle();
        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setModel(vehicleEntity.getModel());
        vehicleDetails.setMake(vehicleEntity.getMake());
        vehicleDetails.setRegistrationNumber(vehicleEntity.getRegistrationNumber());
        vehicleDetails.setVin(vehicleEntity.getVin());
        vehicleDetails.year(vehicleEntity.getYear());

        vehicle.setVehicleDetails(vehicleDetails);
        OwnerDetails ownerDetails = new OwnerDetails();
        ownerDetails.setStreetName(vehicleEntity.getStreetName());
        ownerDetails.setPostCode(vehicleEntity.getPostCode());
        ownerDetails.setLastName(vehicleEntity.getLastName());
        ownerDetails.setDob(vehicleEntity.getDob());
        ownerDetails.setFirstName(vehicleEntity.getFirstName());
        ownerDetails.setEmailId(vehicleEntity.getEmailId());
        vehicle.setOwnerDetails(ownerDetails);
        return vehicle;
    }
}
