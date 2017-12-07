package com.konkerlabs.platform.registry.core.common.core.audit.logger;


import org.slf4j.ILoggerFactory;

public interface IKonkerLoggerFactory extends ILoggerFactory {

    KonkerLogger getLogger(String val);
}
