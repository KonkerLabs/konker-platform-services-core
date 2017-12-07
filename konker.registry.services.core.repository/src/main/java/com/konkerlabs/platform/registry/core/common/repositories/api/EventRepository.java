package com.konkerlabs.platform.registry.core.common.repositories.api;

import com.konkerlabs.platform.registry.core.common.exceptions.BusinessException;
import com.konkerlabs.platform.registry.core.common.model.Application;
import com.konkerlabs.platform.registry.core.common.model.Device;
import com.konkerlabs.platform.registry.core.common.model.Event;
import com.konkerlabs.platform.registry.core.common.model.Tenant;

import java.time.Instant;
import java.util.List;

public interface EventRepository {

    String EVENTS_INCOMING_COLLECTION_NAME = "incomingEvents";
    String EVENTS_OUTGOING_COLLECTION_NAME = "outgoingEvents";

    enum Validations {
        INCOMING_DEVICE_ID_DOES_NOT_EXIST("repositories.events.incoming_device.not_found"),
        OUTGOING_DEVICE_ID_DOES_NOT_EXIST("repositories.events.outgoing_device.not_found"),
        INCOMING_DEVICE_GUID_NULL("repositories.events.incoming_device.guid.not_null"),
        OUTGOING_DEVICE_GUID_NULL("repositories.events.outgoing_device.guid.not_null"),
        EVENT_INCOMING_NULL("repository.events.incoming.not_null"),
        EVENT_INCOMING_CHANNEL_NULL("repository.events.incoming_channel.not_null"),
        EVENT_OUTGOING_NULL("repositories.events.outgoing.not_null"),
        EVENT_OUTGOING_CHANNEL_NULL("repositories.events.outgoing_channel.not_null"),
        EVENT_TIMESTAMP_NULL("repository.events.timestamp.not_null");

        private String code;

        public String getCode() {
            return code;
        }

        Validations(String code) {
            this.code = code;
        }

    }

    Event saveIncoming(Tenant tenant, Application application, Event event) throws BusinessException;

    Event saveOutgoing(Tenant tenant, Application application, Event event) throws BusinessException;

    List<Event> findIncomingBy(Tenant tenant,
                               Application application,
                               String deviceGuid,
                               String channel,
                               Instant startInstant,
                               Instant endInstant,
                               boolean ascending,
                               Integer limit) throws BusinessException;

    List<Event> findOutgoingBy(Tenant tenant,
                               Application application,
                               String deviceGuid,
                               String channel,
                               Instant startInstant,
                               Instant endInstant,
                               boolean ascending,
                               Integer limit) throws BusinessException;

    void removeBy(Tenant tenant, Application application, String deviceGuid) throws BusinessException;

    void copy(Tenant tenant, Device originDevice, Device destDevice) throws BusinessException;

}
