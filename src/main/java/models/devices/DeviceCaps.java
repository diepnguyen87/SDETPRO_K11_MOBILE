package models.devices;

import driver.MobileCapabilityTypeEx;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceCaps {

    private String UDID;
    private String deviceName;
    private String platformVersion;

}
