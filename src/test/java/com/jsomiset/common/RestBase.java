package com.jsomiset.common;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.After;

public class RestBase {
	public static String BASE_URI;
	public static String USER_NAME;
	public static String PASSWORD;
	public Client client;
	public WebTarget target;
	private HttpServer server;

	static {
		BASE_URI = "http://localhost:8081/TestApp";
	}

	public RestBase() {
		this.server = Server.startServer();
		this.client = ClientBuilder.newClient();// Pass config object TODO
		// authenticate(this.client);
	}

	/**
	 * Targeting a web resource
	 * 
	 * @param path
	 */
	public void setResourcePath(String path) {
		this.target = this.client.target(BASE_URI).path(path);
		// ADD common Headers
	}

	public void authenticate(Client client) {
		// Build the target
		Form form = new Form();
		form.param("x", "foo");
		form.param("y", "bar");

		target.request(MediaType.APPLICATION_JSON_TYPE)
				.post(Entity.entity(form,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE));
	}

	/**
	 * Common Configurations
	 * 
	 * @return
	 */
	public ClientConfig configure() {
		ClientConfig clientConfig = new ClientConfig();
		// clientConfig.register(MyClientResponseFilter.class);
		// clientConfig.register(new AnotherClientFilter());
		return clientConfig;
	}

	@After
	public void close() {
		this.client.close();
		this.server.stop();
	}

}
