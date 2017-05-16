package com.epam.finalweb.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.finalweb.command.Command;

public class LogoutCommand implements Command{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
    	if(cookies != null){
    	for(Cookie cookie : cookies){
    		cookie.setMaxAge(0);
    		response.addCookie(cookie);
    	}
    	}
    	
    	HttpSession session = request.getSession(false);
    
    	if(session != null){
    	
    		session.invalidate();
    	}
    	
    	
    	response.sendRedirect("index.jsp");
		
	}

}
