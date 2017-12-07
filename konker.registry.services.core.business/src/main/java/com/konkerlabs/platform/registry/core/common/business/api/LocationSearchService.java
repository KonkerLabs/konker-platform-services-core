package com.konkerlabs.platform.registry.core.common.business.api;


import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.*;

import java.util.List;

public interface LocationSearchService {

    ServiceResponse<Location> findRoot(Tenant tenant, Application application);
    ServiceResponse<Location> findDefault(Tenant tenant, Application application);
    ServiceResponse<Location> findByName(Tenant tenant, Application application, String locationName, boolean loadTree);
    ServiceResponse<Location> findByGuid(Tenant tenant, Application application, String locationName);
    ServiceResponse<List<Location>> findAll(Tenant tenant, Application application);
    ServiceResponse<List<Location>> findAll(Gateway gateway, Tenant tenant, Application application);
    ServiceResponse<List<Device>> listDevicesByLocationName(Tenant tenant, Application application, String locationName);


}