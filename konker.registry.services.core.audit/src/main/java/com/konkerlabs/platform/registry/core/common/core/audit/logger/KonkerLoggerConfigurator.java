package com.konkerlabs.platform.registry.core.common.core.audit.logger;

import ch.qos.logback.core.spi.ContextAware;

public interface KonkerLoggerConfigurator  extends ContextAware {

    void configure(KonkerLoggerContext context);

}
