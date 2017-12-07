package com.konkerlabs.platform.registry.core.common.serializers;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.konkerlabs.platform.registry.core.common.model.Event;
import lombok.Builder;
import lombok.Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class EventVO {

    @JsonView(EventJsonView.class)
    private EventMeta meta;

    @JsonView(EventJsonView.class)
    private Object data;

    @Data
    @Builder
    public static class EventMeta {
        @JsonView(EventJsonView.class)
        private Long timestamp;

        @JsonView(EventJsonView.class)
        private Event.EventActor incoming;

        @JsonView(EventJsonView.class)
        private Event.EventActor outgoing;
    }

    /**
     * Populate a List<EventVo> based on List<Event> events
     * @param events
     * @return List<EventVO>
     */
    public static List<EventVO> from(List<Event> events) {
        ObjectMapper mapper = new ObjectMapper();
        return events.stream()
                .filter(item -> Optional.ofNullable(item).isPresent())
                .map(item -> {
                    try {
                        return EventVO.builder()
                                .meta(EventMeta.builder()
                                        .incoming(item.getIncoming())
                                        .outgoing(item.getOutgoing())
                                        .timestamp(Optional.ofNullable(item.getCreationTimestamp()).isPresent() ? item.getCreationTimestamp().toEpochMilli() : null)
                                        .build()
                                )
                                .data(mapper.readValue(item.getPayload(), HashMap.class))
                                .build();
                    } catch (IOException e) {
                        return null;
                    }
                }).collect(Collectors.toList());
    }


}

