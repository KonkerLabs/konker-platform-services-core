package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.DeviceFirmware;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface DeviceFirmwareRepository extends MongoRepository<DeviceFirmware, String> {

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'deviceModel.id' : ?2, 'version' : ?3 }")
	DeviceFirmware findUnique(String tenantId, String applicationName, String deviceModelId, String version);

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'deviceModel.id' : ?2 }")
    List<DeviceFirmware> listByDeviceModel(String tenantId, String applicationName, String deviceModelId);

}