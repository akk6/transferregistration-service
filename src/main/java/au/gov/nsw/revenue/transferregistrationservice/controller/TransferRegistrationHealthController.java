package au.gov.nsw.revenue.transferregistrationservice.controller;

import au.gov.nsw.revenue.transferregistrationservice.openapi.api.TransferRegistrationMonitoringApi;
import au.gov.nsw.revenue.transferregistrationservice.openapi.model.ServiceStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Tag(name = "Transfer Registration Health Check",description = "The API enables a client to check if the Transfer Registration service is healthy.")
public class TransferRegistrationHealthController implements TransferRegistrationMonitoringApi {
    @Override
    public ResponseEntity<ServiceStatus> healthUsingGET() {
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatus("OK");
        return ResponseEntity.status(HttpStatus.OK).body(serviceStatus);
    }
}
