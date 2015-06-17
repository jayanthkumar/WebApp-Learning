package com.jsomiset.service;

import java.util.Set;

public class AuthorizationService {

	public boolean authorize(String userRole, Set<String> rolesSet) {
		if (rolesSet.contains(userRole)) {
			return true;
		}
		return false;
	}
}
