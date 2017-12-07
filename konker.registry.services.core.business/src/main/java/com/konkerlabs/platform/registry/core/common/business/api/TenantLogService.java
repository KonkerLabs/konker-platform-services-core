package com.konkerlabs.platform.registry.core.common.business.api;


import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.Tenant;
import com.konkerlabs.platform.registry.core.common.model.TenantLog;

import java.util.List;

public interface TenantLogService {

	ServiceResponse<List<TenantLog>> findByTenant(Tenant tenant, boolean ascendingOrder);

}
