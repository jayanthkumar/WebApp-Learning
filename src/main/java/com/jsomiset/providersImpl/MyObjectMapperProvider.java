package com.jsomiset.providersImpl;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

	private static final ObjectMapper defaultObjectMapper = new ObjectMapper();

	static {
		defaultObjectMapper.setSerializationInclusion(Include.NON_EMPTY);
		defaultObjectMapper.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		defaultObjectMapper
				.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
	}

	public MyObjectMapperProvider() {
		System.out.println("Provider was initialized");
	}

	@Override
	public ObjectMapper getContext(Class<?> arg0) {
		return defaultObjectMapper;
	}
}
