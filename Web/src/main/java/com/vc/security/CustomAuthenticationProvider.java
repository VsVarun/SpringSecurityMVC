package com.vc.security;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Override
	public Authentication authenticate(Authentication auth) {
		Authentication authInfo = null;
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		String decodedString = new String(Base64Utils.decodeFromString(password));
		try {
			System.out.println("Credentials : UserName{"+username+"} Password Encoded{"+password+"} Password{"+decodedString+"}");
			authInfo = new UsernamePasswordAuthenticationToken(username, decodedString, Collections.emptyList());
		} catch (Exception e) {
			String exceptionMsg = null;
			if(e.getMessage().contains("AcceptSecurityContext error")) {
				exceptionMsg = "Invalid Username or Password";
			}else {
				exceptionMsg = e.getMessage();
			}
			throw new BadCredentialsException(exceptionMsg);
		}
		return authInfo;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
	
}