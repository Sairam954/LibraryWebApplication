package com.epam.finalweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestFilter implements Filter {
	private static final String INDEX_PAGE = "index.jsp";

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String email = req.getParameter("emailId");
		String password = req.getParameter("password");
		if (email.isEmpty() || email == null) {
			req.setAttribute("errorMessage", "Email cannot be null or empty");
			res.sendRedirect(INDEX_PAGE);
		} else

		if (password.isEmpty() || password == null) {
			req.setAttribute("errorMessage", "Password cannot be null or empty");
			res.sendRedirect(INDEX_PAGE);
		} else {
			filterChain.doFilter(req, res);
		}

	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
