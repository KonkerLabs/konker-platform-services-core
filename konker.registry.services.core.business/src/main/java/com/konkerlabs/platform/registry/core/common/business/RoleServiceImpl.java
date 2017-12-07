package com.konkerlabs.platform.registry.core.common.business;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.api.ServiceResponseBuilder;
import com.konkerlabs.platform.registry.core.common.business.api.RoleService;
import com.konkerlabs.platform.registry.core.common.model.Role;
import com.konkerlabs.platform.registry.core.common.repositories.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
	
    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceImpl() {
    }

	@Override
	public ServiceResponse<Role> findByName(String name) {
		Role role = roleRepository.findByName(name);
		
		if (!Optional.ofNullable(role).isPresent()) {
			return ServiceResponseBuilder.<Role> error()
					.withMessage(RoleService.Validations.ROLE_NOT_EXIST.getCode())
					.build();
		}
		
		return ServiceResponseBuilder.<Role> ok()
				.withResult(role)
				.build();
	}

}
