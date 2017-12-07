package com.konkerlabs.platform.registry.core.common.business;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.api.ServiceResponseBuilder;
import com.konkerlabs.platform.registry.core.common.business.api.LoginAuditService;
import com.konkerlabs.platform.registry.core.common.business.api.UserService;
import com.konkerlabs.platform.registry.core.common.model.LoginAudit;
import com.konkerlabs.platform.registry.core.common.model.Tenant;
import com.konkerlabs.platform.registry.core.common.model.User;
import com.konkerlabs.platform.registry.core.common.model.validation.CommonValidations;
import com.konkerlabs.platform.registry.core.common.repositories.LoginAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class LoginAuditServiceImpl implements LoginAuditService {

	@Autowired
	private LoginAuditRepository loginAuditRepository;

	@Override
	public ServiceResponse<LoginAudit> register(Tenant tenant, User user, LoginAudit.LoginAuditEvent event) {

		if (!Optional.ofNullable(tenant).isPresent()) {
			return ServiceResponseBuilder.<LoginAudit>error()
					.withMessage(CommonValidations.TENANT_NULL.getCode())
					.build();
		}

        if (!Optional.ofNullable(user).isPresent()) {
            return ServiceResponseBuilder.<LoginAudit>error()
                    .withMessage(UserService.Validations.INVALID_USER_DETAILS.getCode())
                    .build();
        }

		try {
			LoginAudit loginAudit = LoginAudit.builder().time(new Date()).event(event.name()).user(user).tenant(tenant)
					.build();
			LoginAudit saved = loginAuditRepository.save(loginAudit);

			return ServiceResponseBuilder.<LoginAudit>ok().withResult(saved).build();
		} catch (Exception e) {
			return ServiceResponseBuilder.<LoginAudit>error().withMessage(e.getMessage()).build();
		}

	}

}
