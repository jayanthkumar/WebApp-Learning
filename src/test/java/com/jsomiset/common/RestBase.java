package com.jsomiset.common;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.After;

public class RestBase {
	public static String BASE_URI;
	public static String USER_NAME;
	public static String PASSWORD;
	public static String GRIZZLY_PORT;
	public Client client;
	public WebTarget target;
	private HttpServer server;

	static {
		// TODO Should be loaded from config properties
		GRIZZLY_PORT = "8081";
		BASE_URI = String.format("http://localhost:%s/TestApp", GRIZZLY_PORT);
	}

	public RestBase() {
		this.server = Server.startServer();
		this.client = ClientBuilder.newClient();// Pass config object TODO
		authenticate(this.client);
	}

	/**
	 * Targeting a web resource
	 * 
	 * @param path
	 */
	public void setResourcePath(String path) {
		this.target = this.client.target(BASE_URI).path(path);
		// TODO ADD common Headers
	}

	public void authenticate(Client client) {
		// Build the target
//		Form form = new Form();
//		form.param("x", "foo");
//		form.param("y", "bar");
//
//		target.request(MediaType.APPLICATION_JSON_TYPE)
//				.post(Entity.entity(form,
//						MediaType.APPLICATION_FORM_URLENCODED_TYPE));
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
		client.register(feature);
	}

	/**
	 * Common Configurations
	 * 
	 * @return ClientConfig
	 */
	public ClientConfig configure() {
		ClientConfig clientConfig = new ClientConfig();
		// Register Filters & etc..
		// clientConfig.register(MyClientResponseFilter.class);
		// clientConfig.register(new AnotherClientFilter());
		return clientConfig;
	}

	@After
	public void close() {
		this.client.close();
		this.server.shutdown();
	}

}
