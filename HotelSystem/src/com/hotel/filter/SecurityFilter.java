package com.hotel.filter;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 import com.hotel.dao.UserDao;
import com.hotel.model.User;

public class SecurityFilter extends HttpServlet implements Filter {

	private static UserDao userDao = new UserDao();

	private static final ConcurrentMap<String, String> sessions = new ConcurrentHashMap<String, String>(
			1000);

 	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException   {

		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();

		uri = uri.substring(req.getContextPath().length());

		if (uri.equals("/login") || uri.endsWith(".jpg")
				|| uri.endsWith(".css") || uri.endsWith(".gif")
				|| uri.endsWith(".png")) {
			chain.doFilter(request, response);
			return;
		}
		if (uri.equals("/logout")) {
			logout(request, response);
			HttpServletResponse httpresponse = (HttpServletResponse) response;
			httpresponse.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		if (isAuthenticated(req) != null) {
			chain.doFilter(request, response);

		} else {

			String username = (String) request.getParameter("username");
			String password = (String)  request.getParameter ("password");

			User user = null;
 			
			user = authenticate(username, password);

			if (user != null) {
				HttpSession session = req.getSession(false);

				String sessionId = session.getId();
				sessions.put(sessionId, user.getUsername());
				session.setAttribute("username", user.getUsername());

				chain.doFilter(request, response);

			} else {

				req.getRequestDispatcher("/login").forward(request,
						response);
			}

		}
	}

	public static String isAuthenticated(ServletRequest request) {
		HttpSession session = ((HttpServletRequest) request).getSession(false);
		if (session == null) {
			return null;
		}
		return (String) sessions.get(session.getId());
	}

	private User authenticate(String username, String password)
			throws IOException {

		if (username == null) {
			return null;
		}
		User user = userDao.find(username, password);

		return user;

	}

	private void logout(ServletRequest request, ServletResponse response) {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String user = sessions.get(session.getId());
		if (user != null) {
			req.getSession().invalidate();
			sessions.remove(session.getId());
		} else {
			req.getSession().invalidate();
		}
	}

	public static void removeSession(String sessionId) {
		if (sessions.containsKey(sessionId)) {
			sessions.remove(sessionId);
		}
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}
}
