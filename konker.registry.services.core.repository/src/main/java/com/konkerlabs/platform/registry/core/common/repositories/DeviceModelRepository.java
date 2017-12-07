package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.DeviceModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeviceModelRepository extends MongoRepository<DeviceModel, String> {

	@Query("{ 'tenant.id' : ?0, 'application.name' : ?1 }")
    List<DeviceModel> findAllByTenantIdAndApplicationName(String tenantId, String applicationName);

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'name' : ?2 }")
    DeviceModel findByTenantIdApplicationNameAndName(String tenantId, String applicationName, String name);

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'guid' : ?2 }")
    DeviceModel findByTenantIdApplicationNameAndGuid(String tenantId, String applicationName, String guid);

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'defaultModel' : ?2 }")
    DeviceModel findDefault(String tenantId, String applicationName, boolean defaultModel);

}
