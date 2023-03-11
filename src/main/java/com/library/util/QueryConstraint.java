package com.library.util;

public final class QueryConstraint {

	public static class SOFT_DELETE_QUERY {
		public static final String SOFT_DELETE= "UPDATE users SET deleted = true WHERE user_id = ?";
		public static final String DELETE_CLAUSE = "deleted = false";
	}
	
}
