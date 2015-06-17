package com.jsomiset.providersImpl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.jsomiset.service.AuthenticationService;
import com.jsomiset.service.AuthorizationService;

@Provider
public class BasicAuthenticationFilter implements ContainerRequestFilter {
	public static final String AUTHENTICATION_HEADER = "Authorization";
	// When authentication succeeded but authenticated user doesn't have access to the resource
	private static final Response ACCESS_FORBIDDEN = Response.serverError()
			.status(403).build();
	// When no or invalid authentication details are provided
	private static final Response UNAUTHORIZED = Response.serverError()
			.status(401).build();

	@Context
	private ResourceInfo resourceInfo;
	// better injected
	AuthenticationService authenticationService = new AuthenticationService();
	AuthorizationService authorizationService = new AuthorizationService();

	@Override
	public void filter(ContainerRequestContext requestContext)
			throws IOException {
		Method method = resourceInfo.getResourceMethod();
		if (!method.isAnnotationPresent(PermitAll.class)) {
			// Access denied for all
			if (method.isAnnotationPresent(DenyAll.class)) {
				requestContext.abortWith(UNAUTHORIZED);
				return;
			}

			// Get request headers
			final MultivaluedMap<String, String> headers = requestContext
					.getHeaders();
			// Fetch authorization header
			final List<String> authorization = headers
					.get(AUTHENTICATION_HEADER);
			// If no authorization information present; block access
			if (authorization == null || authorization.isEmpty()) {
				requestContext.abortWith(UNAUTHORIZED);
				return;
			}
			if (method.isAnnotationPresent(RolesAllowed.class)) {
				RolesAllowed rolesAnnotation = method
						.getAnnotation(RolesAllowed.class);
				Set<String> rolesSet = new HashSet<String>(
						Arrays.asList(rolesAnnotation.value()));
				boolean authenticationStatus = authenticationService
						.authenticate(authorization.get(0));
				String userRole = "ADMINu"; // TODO
				if (!authorizationService.authorize(userRole, rolesSet)) {
					requestContext.abortWith(ACCESS_FORBIDDEN);
					return;
				}
			}
		}

	}

}
