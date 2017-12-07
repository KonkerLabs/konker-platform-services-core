package com.konkerlabs.platform.registry.core.common.business;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.api.ServiceResponseBuilder;
import com.konkerlabs.platform.registry.core.common.business.api.TenantLogService;
import com.konkerlabs.platform.registry.core.common.core.audit.repositories.TenantLogRepository;
import com.konkerlabs.platform.registry.core.common.model.Device;
import com.konkerlabs.platform.registry.core.common.model.Tenant;
import com.konkerlabs.platform.registry.core.common.model.TenantLog;
import com.konkerlabs.platform.registry.core.common.model.validation.CommonValidations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TenantLogServiceImpl implements TenantLogService {

	private Logger LOGGER = LoggerFactory.getLogger(TenantLogServiceImpl.class);

    @Autowired
	private TenantLogRepository tenantLogRepository;

	@Override
	public ServiceResponse<List<TenantLog>> findByTenant(Tenant tenant, boolean ascending) {

		if (!Optional.ofNullable(tenant).isPresent()) {
			Device device = Device.builder().guid("NULL")
					.tenant(Tenant.builder().domainName("unknow_domain").build()).build();
			
			LOGGER.debug(CommonValidations.TENANT_NULL.getCode(), device.toURI(),
					device.getLogLevel());
			return ServiceResponseBuilder.<List<TenantLog>>error()
					.withMessage(CommonValidations.TENANT_NULL.getCode(), null).build();
		}

		try {
			List<TenantLog> all = tenantLogRepository.findAll(tenant);

			if (ascending) {
				Collections.sort(all, (p1, p2) -> p1.getTime().compareTo(p2.getTime()));
			} else {
				Collections.sort(all, (p1, p2) -> -p1.getTime().compareTo(p2.getTime()));
			}

			return ServiceResponseBuilder.<List<TenantLog>>ok().withResult(all).build();
		} catch (Exception e) {
			return ServiceResponseBuilder.<List<TenantLog>>error().withMessage(e.getMessage()).build();
		}

	}

}
