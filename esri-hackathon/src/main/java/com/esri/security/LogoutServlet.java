package com.esri.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	// private static Logger log = Logger.getLogger(LogoutServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("User " + request.getUserPrincipal() + " logged out at " + new Date() );
    	
    	response.setContentType("text/html"); 
    	Cookie[] cookie = request.getCookies();

		// if(j.getName().equals("JSESSIONID")){
    	if(cookie != null){
            for (int i = 0; i < cookie.length; i++) {
//                log.debug("Hello this is an debug message");
//                log.info("JSESSIONID="+ cookie[i].getValue() + "| Name:" + cookie[i].getName());  
//				System.out.println("JSESSIONID="+ cookie[i].getValue() + "| Name:" + cookie[i].getName());
//                String str = cookie[i].getName() + "logged out " + cookie[i].getValue();
//                log(str);
//                System.out.println("Cooke name " + cookie[i].getName());
                /**
                 * Destroy cookie
                 */
            	cookie[i].setValue("");
            	cookie[i].setPath("/");
            	cookie[i].setMaxAge(0);
                response.addCookie(cookie[i]);
                break;
	    	}
    	}
    	
    	HttpSession session = request.getSession(false);
//    	System.out.println("User="+session.getAttribute("user"));
    	if(session != null){
    		System.out.println("Session was not null");
    		session.invalidate();
    	}

    	/**
    	 *  Redirects to login page as admin/index.html is protected. Cannot request login.html directly
    	 */
    	response.sendRedirect("http://localhost:8080/esri-hackathon/admin/index.html");
    }

}
