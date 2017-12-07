package com.konkerlabs.platform.registry.core.common.business.api;


import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.*;

import java.util.List;

public interface DeviceConfigSetupService {

    enum Validations {
        DEVICE_CONFIG_NOT_FOUND("service.device_config.not_found"),
        DEVICE_INVALID_JSON("service.device_config.invalid_json")
        ;

        public String getCode() {
            return code;
        }

        private String code;

        Validations(String code) {
            this.code = code;
        }

    }
    enum Messages {
        DEVICE_CONFIG_REGISTERED_SUCCESSFULLY("service.device_config.registered_success"),
        DEVICE_CONFIG_REMOVED_SUCCESSFULLY("service.device_config.removed_succesfully")
        ;

        public String getCode() {
            return code;
        }

        private String code;

        Messages(String code) {
            this.code = code;
        }

    }

    ServiceResponse<List<DeviceConfig>> findAll(Tenant tenant, Application application);
    ServiceResponse<List<DeviceConfig>> findAllByDeviceModel(Tenant tenant, Application application, DeviceModel model);
    ServiceResponse<List<DeviceConfig>> findAllByLocation(Tenant tenant, Application application, Location location);

    ServiceResponse<String> findByModelAndLocation(Tenant tenant, Application application, DeviceModel model, Location location);

    ServiceResponse<DeviceConfig> save(Tenant tenant, Application application, DeviceModel deviceModel,
                                       Location location, String json);

    ServiceResponse<DeviceConfig> update(Tenant tenant, Application application, DeviceModel deviceModel,
                                         Location location, String json);

    ServiceResponse<DeviceConfigSetup> remove(Tenant tenant, Application application, DeviceModel deviceModel,
                                              Location location);

}
