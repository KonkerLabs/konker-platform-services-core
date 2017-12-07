package com.konkerlabs.platform.registry.core.common.model.behaviors;

import org.springframework.util.StringUtils;

import java.net.URI;
import java.text.MessageFormat;

public interface URIDealer {

    final String URI_TEMPLATE = "{0}://{1}/{2}";

    String getUriScheme();

    String getContext();

    String getGuid();

    default String getRoutUriTemplate() throws IllegalArgumentException {
        if(StringUtils.isEmpty(getGuid())){
            throw new IllegalArgumentException("GUID cannot be null or empty");
        }
        if(StringUtils.isEmpty(getContext())){
            throw new IllegalArgumentException("CONTEXT cannot be null or empty");
        }
        return MessageFormat.format(URI_TEMPLATE, getUriScheme(), getContext(), getGuid());
    }

    default URI toURI() throws IllegalArgumentException {
        return URI.create(
                getRoutUriTemplate()
        );
    }

}
