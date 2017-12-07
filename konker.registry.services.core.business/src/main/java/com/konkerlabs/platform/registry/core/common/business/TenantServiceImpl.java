package com.konkerlabs.platform.registry.core.common.business;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.api.ServiceResponseBuilder;
import com.konkerlabs.platform.registry.core.common.business.api.ApplicationService;
import com.konkerlabs.platform.registry.core.common.business.api.TenantService;
import com.konkerlabs.platform.registry.core.common.model.Application;
import com.konkerlabs.platform.registry.core.common.model.Device;
import com.konkerlabs.platform.registry.core.common.model.Tenant;
import com.konkerlabs.platform.registry.core.common.model.TenantDailyUsage;
import com.konkerlabs.platform.registry.core.common.model.enumerations.LogLevel;
import com.konkerlabs.platform.registry.core.common.repositories.DeviceRepository;
import com.konkerlabs.platform.registry.core.common.repositories.TenantDailyUsageRepository;
import com.konkerlabs.platform.registry.core.common.repositories.TenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class TenantServiceImpl implements TenantService {

	private Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private DeviceRepository deviceRepository;
	
	@Autowired
	private TenantDailyUsageRepository tenantDailyUsageRepository;
	
	@Autowired
	private ApplicationService applicationService;

	@Override
	public ServiceResponse<Tenant> updateLogLevel(Tenant tenant, LogLevel newLogLevel) {

        if (!Optional.ofNullable(tenant).isPresent()) {
            return ServiceResponseBuilder.<Tenant>error()
                    .withMessage(Validations.TENANT_NULL.getCode())
                    .build();
        }

		Tenant fromStorage = tenantRepository.findOne(tenant.getId());

        if (!Optional.ofNullable(fromStorage).isPresent()) {
            return ServiceResponseBuilder.<Tenant>error()
                    .withMessage(Validations.NO_EXIST_TENANT.getCode())
                    .build();
        }

		try {

			if (!fromStorage.getLogLevel().equals(newLogLevel)) {
				LOG.info("Changed organization log level: {} -> {}", fromStorage.getLogLevel(),
						newLogLevel);

				fromStorage.setLogLevel(newLogLevel);

				tenantRepository.save(fromStorage);

				// change all tenant devices log levels
				List<Device> devices = deviceRepository.findAllByTenant(tenant.getId());

				for (Device device : devices) {
					device.setLogLevel(newLogLevel);
					deviceRepository.save(device);
				}
			}

			return ServiceResponseBuilder.<Tenant>ok().withResult(fromStorage).build();

		} catch (Exception e) {
			return ServiceResponseBuilder.<Tenant>error().withMessage(Errors.ERROR_SAVE_TENANT.getCode()).build();
		}

	}

	@Override
	public ServiceResponse<List<TenantDailyUsage>> findTenantDailyUsage(Tenant tenant) {
		 List<TenantDailyUsage> usages = tenantDailyUsageRepository.findAllByTenantDomain(tenant.getDomainName());
		
		 return ServiceResponseBuilder.<List<TenantDailyUsage>> ok().withResult(usages).build();
	}

	@Override
	public ServiceResponse<Tenant> save(Tenant tenant) {
		if (!Optional.ofNullable(tenant).isPresent()) {
			return ServiceResponseBuilder.<Tenant>error()
					.withMessage(Validations.TENANT_NULL.getCode())
					.build();
		}
		
		if (!Optional.ofNullable(tenant.getName()).isPresent() ||
				tenant.getName().isEmpty()) {
			return ServiceResponseBuilder.<Tenant>error()
					.withMessage(Validations.TENANT_NAME_NULL.getCode())
					.build();
		}

		tenant.setDevicesLimit(new Long(5l));
		tenant.setDomainName(generateDomainName());

		Tenant fromStorage = tenantRepository.save(tenant);
		
		applicationService.register(fromStorage, 
				Application.builder()
					.name(fromStorage.getDomainName())
					.friendlyName(fromStorage.getDomainName())
					.build());
		
		return ServiceResponseBuilder.<Tenant>ok()
				.withResult(fromStorage)
				.build();
	}

	@Override
	public ServiceResponse<Tenant> findByDomainName(String name) {
		if(!Optional.of(name).isPresent()){
			return ServiceResponseBuilder.<Tenant>error()
					.withMessage(Validations.TENANT_NAME_NULL.getCode())
					.build();
		}
		Tenant tenant = tenantRepository.findByDomainName(name);
		if(!Optional.ofNullable(tenant).isPresent()){
			return ServiceResponseBuilder.<Tenant>error()
					.withMessage(Validations.NO_EXIST_TENANT.getCode())
					.build();
		}
		return ServiceResponseBuilder.<Tenant> ok()
				.withResult(tenant)
				.build();
	}

	private String generateDomainName() {
		String letters = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "1234567890";
		String alphaNumeric = letters.concat(numbers);
		Random random = new Random();
		StringBuilder domainName = new StringBuilder();
		
		int index = (int) (random.nextFloat() * letters.length());
		domainName.append(letters.charAt(index));
		
		while (domainName.length() < 8) {
			index = (int) (random.nextFloat() * alphaNumeric.length());
			domainName.append(alphaNumeric.charAt(index));
		}
		
		Tenant fromStorage = tenantRepository.findByDomainName(domainName.toString());
		
		if (Optional.ofNullable(fromStorage).isPresent()) {
			return generateDomainName();
		}
		
		return domainName.toString();
	}

}
