package com.konkerlabs.platform.registry.core.common.business.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class HealthAlertsConfig {

    private int silenceMinimumMinutes;

    public HealthAlertsConfig() {
        Map<String, Object> defaultMap = new HashMap<>();
        defaultMap.put("healthAlerts.silence.minimumMinutes", 10);

        Config defaultConf = ConfigFactory.parseMap(defaultMap);
        Config config = ConfigFactory.load().withFallback(defaultConf);

        setSilenceMinimumMinutes(config.getInt("healthAlerts.silence.minimumMinutes"));
    }

}
