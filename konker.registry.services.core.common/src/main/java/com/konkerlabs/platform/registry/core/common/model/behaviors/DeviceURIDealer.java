package com.konkerlabs.platform.registry.core.common.model.behaviors;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Optional;

public interface DeviceURIDealer extends URIDealer {

    String DEVICE_URI_SCHEME = "device";
    String DEVICE_ROUTE_URI_TEMPLATE = DEVICE_URI_SCHEME + "://{0}/{1}";

    default URI toDeviceRouteURI(String tenantName, String guid) {
        Optional.ofNullable(tenantName)
            .filter(s -> !s.isEmpty())
            .orElseThrow(() -> new IllegalArgumentException("Tenant domain cannot be null or empty"));

        Optional.ofNullable(guid)
                .filter(s -> !s.isEmpty())
                .orElseThrow(() -> new IllegalArgumentException("Device GUID cannot be null or empty"));

        return URI.create(
            MessageFormat.format(DEVICE_ROUTE_URI_TEMPLATE,tenantName,guid)
        );
    }
}
