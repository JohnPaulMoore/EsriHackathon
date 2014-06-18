package com.esri.security;
// http://padcom13.blogspot.ie/2011/09/cors-filter-for-java-applications.html

import java.io.*;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.*;
import javax.servlet.http.*;

public class CORSFilter implements Filter {

	public CORSFilter() { }

	public void init(FilterConfig fConfig) throws ServletException { }

	public void destroy() {	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
	
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "*");
		// ((HttpServletResponse)response).addHeader("Access-Control-Allow-Origin", "localhost:8080"); // http://domain.com:3000

		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Content-Type");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "GET,POST,OPTIONS,PUT,DELETE,HEAD");// , HEAD
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "User-Agent");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Headers", "Accept");
		
		((HttpServletResponse)response).addHeader("Allow", "GET,POST,OPTIONS,PUT,DELETE,HEAD");
		((HttpServletResponse)response).addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE,HEAD");

		
		((HttpServletResponse)response).addHeader("Accept", "*/*");
		// ((HttpServletResponse)response).addHeader("Accept", "charset=UTF-8");
		// ((HttpServletResponse)response).addHeader("Access-Control-Allow-Credentials", "true");
		
		((HttpServletResponse)response).addHeader("Content-Type", "application/json");	// application/x-javascript
		// ((HttpServletResponse)response).setContentType("application/json"); // charset=UTF-8
		

		// header("Access-Control-Allow-Methods", "GET, POST, OPTIONS");

		System.out.println("Filter done");
		chain.doFilter(request, response);
		
	}
}