package com.konkerlabs.platform.registry.core.common.business.api;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.LoginAudit;
import com.konkerlabs.platform.registry.core.common.model.Tenant;
import com.konkerlabs.platform.registry.core.common.model.User;

public interface LoginAuditService {

	ServiceResponse<LoginAudit> register(Tenant tenant, User user, LoginAudit.LoginAuditEvent event);

}
