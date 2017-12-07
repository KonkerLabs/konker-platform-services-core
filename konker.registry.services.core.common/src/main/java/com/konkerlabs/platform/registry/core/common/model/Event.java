package com.konkerlabs.platform.registry.core.common.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.konkerlabs.platform.registry.core.common.model.behaviors.URIDealer;
import com.konkerlabs.platform.registry.core.common.serializers.EventJsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Data
@Builder
public class Event {

    private Long epochTime;

    private Instant creationTimestamp;
    
    private Instant ingestedTimestamp;

    @JsonView(EventJsonView.class)
    private EventActor incoming;

    @JsonView(EventJsonView.class)
    private EventActor outgoing;
    
    @JsonView(EventJsonView.class)
    private EventGeolocation geolocation;

    @JsonView(EventJsonView.class)
    private String payload;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventActor implements URIDealer {

        private String tenantDomain;
        private String applicationName;
        private String deviceGuid;

        @JsonView(EventJsonView.class)
        private String deviceId;

        @JsonView(EventJsonView.class)
        private String channel;

        public static final String URI_SCHEME = "eventactor";

        @Override
        public String getUriScheme() {
            return URI_SCHEME;
        }

        @Override
        public String getContext() {
            return tenantDomain;
        }

        @Override
        public String getGuid() {
            return deviceGuid;
        }
    }

    public ZonedDateTime getZonedTimestamp(String zoneId) {
        return creationTimestamp.atZone(ZoneId.of(zoneId));
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventDecorator {
        private String timestampFormated;
        private Long timestamp;
        private EventActor incoming;
        private String payload;
    }
    
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventGeolocation  {

        private Double lat;
        private Double lon;
        private Long hdop;
        private Double elev;

    }
}
