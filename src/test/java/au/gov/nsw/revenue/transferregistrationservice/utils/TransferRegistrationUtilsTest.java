package au.gov.nsw.revenue.transferregistrationservice.utils;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransferRegistrationUtilsTest {

    @Test
    public void testMapPersonEntityFromRest() {
        Assert.assertNull(TransferRegistrationUtils.mapPersonEntityFromRest(null));
    }

    @Test
    public void testMapPersonFromEntity() {
        Assert.assertNull(TransferRegistrationUtils.mapPersonFromEntity(null));
    }

    @Test
    public void testMapVehicleDetailsFromEntity() {
        Assert.assertNull(TransferRegistrationUtils.mapVehicleDetailsFromEntity(null));
    }

    @Test
    public void testMapVehicleFromEntity() {
        Assert.assertNull(TransferRegistrationUtils.mapVehicleFromEntity(null));
    }

    @Test
    public void testMapVehicleEntityFromRestVehicleDetails() {
        Assert.assertNull(TransferRegistrationUtils.mapVehicleEntityFromRestVehicleDetails(null));
    }
}
