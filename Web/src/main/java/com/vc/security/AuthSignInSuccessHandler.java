package com.vc.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.vc.common.utils.TimeUtils;


@Component
public class AuthSignInSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	TimeUtils timeUtils;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {
		response.addCookie(new Cookie("userId", auth.getName()));
		response.sendRedirect(request.getContextPath()+"/app/home.html");
		
		System.out.println(auth.getName()+" Login time : "+timeUtils.getTimeStamp().toString());
	}

}
