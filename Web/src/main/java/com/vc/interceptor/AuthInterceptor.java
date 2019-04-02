package com.vc.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.common.base.Strings;

@CrossOrigin
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
		boolean isValid = false;
		Cookie[] cookies = request.getCookies();
		if(cookies!=null && cookies.length>0) {
			for(int i=0;i<cookies.length;i++) {
				Cookie cookie = cookies[i];
				if(cookie!=null && !Strings.isNullOrEmpty(cookie.getValue())) {
				}
			}
			response.sendRedirect(request.getContextPath() + "/index.html");
			isValid = true;
		}else {
			response.sendRedirect(request.getContextPath() + "/login.html");
		}
        return isValid;
    }
}
