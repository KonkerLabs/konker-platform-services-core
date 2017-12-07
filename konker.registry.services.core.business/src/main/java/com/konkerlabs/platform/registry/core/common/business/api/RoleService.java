package com.konkerlabs.platform.registry.core.common.business.api;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.Role;

public interface RoleService {

    String ROLE_IOT_USER = "ROLE_IOT_USER";
    String ROLE_IOT_GATEWAY = "ROLE_IOT_GATEWAY";

    enum Validations {
        ROLE_NOT_EXIST("service.role.validation.not.exist");

        private String code;

        Validations(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    
    ServiceResponse<Role> findByName(String name);

}
