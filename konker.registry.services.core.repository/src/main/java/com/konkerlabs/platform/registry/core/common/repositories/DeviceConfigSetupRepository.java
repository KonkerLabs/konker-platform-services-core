package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.DeviceConfigSetup;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeviceConfigSetupRepository extends MongoRepository<DeviceConfigSetup, String> {

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1 }")
    List<DeviceConfigSetup> findAllByTenantIdAndApplicationName(String tenantId, String applicationName);

}
