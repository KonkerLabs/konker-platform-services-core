package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.ApplicationDocumentStore;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ApplicationDocumentStoreRepository
        extends MongoRepository<ApplicationDocumentStore, String> {

    @Query("{ 'tenant.id' : ?0, 'application.name' : ?1, 'collection' : ?2, 'key' : ?3 }")
    ApplicationDocumentStore findUniqueByTenantIdApplicationName(String id, String name, String collection, String key);

}
