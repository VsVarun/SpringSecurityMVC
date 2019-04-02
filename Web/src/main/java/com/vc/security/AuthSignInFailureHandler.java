package com.vc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.vc.common.utils.TimeUtils;

@Component
public class AuthSignInFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	TimeUtils timeUtils;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae)
			throws IOException, ServletException {
		String redirectUrl = request.getContextPath()+"/login.html?error="+ae.getLocalizedMessage();
		redirectUrl = response.encodeRedirectURL(redirectUrl);
		response.setStatus(200);
		response.sendRedirect(redirectUrl);
		System.out.println("["+timeUtils.getTimeStamp().toString()+"] "+ae.getLocalizedMessage());
	}

}
