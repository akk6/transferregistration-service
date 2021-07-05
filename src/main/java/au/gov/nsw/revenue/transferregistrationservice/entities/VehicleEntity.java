package au.gov.nsw.revenue.transferregistrationservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleEntity {
    private String registrationNumber;
    private String model;
    private String make;
    private Integer year;
    private String vin;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String streetName;
    private BigDecimal postCode;
    private String emailId;
}
