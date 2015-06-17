package com.jsomiset;

import javax.json.stream.JsonGenerator;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import com.jsomiset.providersImpl.MyObjectMapperProvider;

@ApplicationPath("/")
public class TestApplication extends ResourceConfig {
	public TestApplication() {
		System.out.println("loading..");

		// Register Application Resources
		packages("com.jsomiset.resourcesImpl");
		// Register Filters

		// Register Exception Mappers

		// Register Features
		register(MyObjectMapperProvider.class);

		property(JsonGenerator.PRETTY_PRINTING, true);
		// Now you can expect validation errors to be sent to the client.
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		// @ValidateOnExecution annotations on subclasses won't cause errors.
		// property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
	}
}
