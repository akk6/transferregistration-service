package au.gov.nsw.revenue.transferregistrationservice.service;

import au.gov.nsw.revenue.transferregistrationservice.dao.TransferRegistrationDatabaseService;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

public class TransferRegistrationServiceTest {

    @Mock
    private TransferRegistrationDatabaseService transferRegistrationDatabaseService;

    @MockBean
    private TransferRegistrationServiceImpl transferRegistrationServiceImpl;
}
