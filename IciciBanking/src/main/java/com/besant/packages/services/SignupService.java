package com.besant.packages.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SignupService {
	public void signUp(HttpServletRequest req,
			HttpServletResponse res);
}
