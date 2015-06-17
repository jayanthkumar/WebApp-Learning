package com.jsomiset.resources;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsomiset.common.Server;
import com.jsomiset.resourcesImpl.BookResourceImpl;

public class BookResourceTest {

	private static HttpServer server;
	private WebTarget resourceTarget;

	public BookResourceTest() {
		System.out.println("constructor is called"); // TODO Initialize
	}

	@BeforeClass
	public static void beforeBookApiTestClass() {
		server = new Server().startServer();
		System.out.println("Server was started");
	}

	@Before
	public void beforeBookApiTest() throws Exception {
		Client client = ClientBuilder.newClient();// http://localhost:8081/TestApp/api
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic("admin", "admin");
		client.register(feature);
		WebTarget target = client.target("http://localhost:8081/TestApp");// /api/v1/books
		resourceTarget = target.path(BookResourceImpl.ITEMS_URL);
		ObjectMapper mapper = new ObjectMapper();
	}

	@Test
	public void v1ItemsShouldReturnStatus200() {
		assertThat(resourceTarget.request().head().getStatus(), is(200));
	}

	// @Test
	// public void testForGetAllBooks() {
	// Invocation.Builder invocationBuilder =
	// this.target.request(MediaType.APPLICATION_JSON);
	// Response response = invocationBuilder.get();
	// System.out.println("Status :" + response.getStatus());
	// System.out.println("Response:" + response.readEntity(List.class));
	// // List<Object> books = response.readEntity(List.class);
	// // for(Object book: books){
	// // System.out.println("Each book is : "+ (Book)book);
	// // }
	// }

	@AfterClass
	public static void afterUserResourceTestClass() {
		System.out.println("server is going to shut down");
		server.shutdown();
	}
}
