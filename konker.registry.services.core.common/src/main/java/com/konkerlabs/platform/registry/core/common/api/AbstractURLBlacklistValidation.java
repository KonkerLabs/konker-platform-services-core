package com.konkerlabs.platform.registry.core.common.api;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractURLBlacklistValidation {
    public Optional<Map<String,Object[]>> verifyIfUrlMatchesBlacklist(String str) {
        // TODO: implement
        return Optional.empty();
    }
}
