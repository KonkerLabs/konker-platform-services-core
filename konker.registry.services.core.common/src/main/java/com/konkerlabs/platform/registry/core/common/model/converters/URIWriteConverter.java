package com.konkerlabs.platform.registry.core.common.model.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.net.URI;

@WritingConverter
public class URIWriteConverter implements Converter<URI,String> {
    @Override
    public String convert(URI source) {
        return source.toString();
    }
}
