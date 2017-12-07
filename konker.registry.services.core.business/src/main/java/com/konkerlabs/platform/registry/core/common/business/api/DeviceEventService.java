package com.konkerlabs.platform.registry.core.common.business.api;


import com.konkerlabs.platform.registry.core.common.api.ServiceResponse;
import com.konkerlabs.platform.registry.core.common.model.Application;
import com.konkerlabs.platform.registry.core.common.model.Event;
import com.konkerlabs.platform.registry.core.common.model.Tenant;

import java.time.Instant;
import java.util.List;

public interface DeviceEventService {

    enum Validations {
        DEVICE_NULL("service.device_events.device.not_null"),
        CHANNEL_NULL("service.device_events.channel.not_null"),
        EVENT_NULL("service.device_events.event.not_null"),
        EVENT_PAYLOAD_NULL("service.device_events.event_payload.not_null"),
        LIMIT_NULL("service.device_events.limit.not_null");

        public String getCode() {
            return code;
        }

        private String code;

        Validations(String code) {
            this.code = code;
        }
    }

    /**
     * Return all existing incoming device events by provided arguments
     *
     * @param tenant
     * @param application
     * @param deviceGuid
     * @param startingTimestamp
     * @param endTimestamp
     * @param limit
     * @return Found events
     */
    ServiceResponse<List<Event>> findIncomingBy(Tenant tenant,
                                                Application application,
                                                String deviceGuid,
                                                String channel,
                                                Instant startingTimestamp,
                                                Instant endTimestamp,
                                                boolean ascending,
                                                Integer limit);

    /**
     * Return all existing incoming device events by provided arguments
     *
     * @param tenant
     * @param application
     * @param deviceGuid
     * @param startingTimestamp
     * @param endTimestamp
     * @param limit
     * @return Found events
     */
    ServiceResponse<List<Event>> findOutgoingBy(Tenant tenant,
                                                Application application,
                                                String deviceGuid,
                                                String channel,
                                                Instant startingTimestamp,
                                                Instant endTimestamp,
                                                boolean ascending,
                                                Integer limit);
}
