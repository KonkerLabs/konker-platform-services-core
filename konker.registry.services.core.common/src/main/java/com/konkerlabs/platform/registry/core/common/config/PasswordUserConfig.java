package com.konkerlabs.platform.registry.core.common.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PasswordUserConfig {

	private Integer iterations;

	public PasswordUserConfig() {
		Map<String, Object> defaultMap = new HashMap<>();
		defaultMap.put("password.user.iterations", 10000);
		Config defaultConf = ConfigFactory.parseMap(defaultMap);

		Config config = ConfigFactory.load().withFallback(defaultConf);
		setIterations(config.getInt("password.user.iterations"));
	}

}
