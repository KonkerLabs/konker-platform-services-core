package com.konkerlabs.platform.registry.core.common.repositories;

import com.konkerlabs.platform.registry.core.common.model.LoginAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginAuditRepository extends MongoRepository<LoginAudit, String> {
}
