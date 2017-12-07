package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.Application;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ApplicationRepository extends MongoRepository<Application,String> {
    @Query("{ 'tenant.id' : ?0 }")
    List<Application> findAllByTenant(String tenantId);
    
    @Query("{ 'tenant.id' : ?0, '_id' : ?1  }")
    Application findByTenantAndName(String tenantId, String name);

}
