package com.konkerlabs.platform.registry.core.common.business.api;

import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.Token;
import com.konkerlabs.platform.registry.core.common.model.User;

import java.time.temporal.TemporalAmount;

public interface TokenService {
    enum Purpose {
        RESET_PASSWORD("service.token.purpose.resetPassword"),
    	
    	VALIDATE_EMAIL("service.token.purpose.validateEmail");

        private String name;

        Purpose(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }
    }
    enum Validations {
        EXPIRED_TOKEN("service.token.validation.uuid.expired"),
        INVALID_TOKEN("service.token.validation.uuid.invalid"),
        INVALID_EXPIRED_TOKEN("service.token.validation.uuid.invalid_expired");

        private String code;

        Validations(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
    ServiceResponse<Token> getToken(String token);
    ServiceResponse<String> generateToken(Purpose purpose, User user, TemporalAmount temporalAmount);
    ServiceResponse<Boolean> isValidToken(String token);
    ServiceResponse<Boolean> invalidateToken(String token);
}
