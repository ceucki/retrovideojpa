package be.vdab.servlets;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.services.FilmService;

/**
 * Servlet implementation class ReserverenServlet
 */
@WebServlet("/reservaties/reserveren.htm")
public class ReserverenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reserveren.jsp";
	private static final transient FilmService filmService = new FilmService();
	private static final String REDIRECT_URL = "%s/reservaties/filmbestellen.htm";
	private static final String MANDJE = "mandje";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {		
		request.setAttribute("film", filmService.read((Integer.parseInt(request.getParameter("id")))));
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
		if (mandje == null) {
			mandje = new LinkedHashSet<>();
		}
		mandje.add(Integer.parseInt(request.getParameter("id")));
		session.setAttribute(MANDJE, mandje);

		response.sendRedirect(String.format(REDIRECT_URL,
				request.getContextPath()));
	}
	
}
