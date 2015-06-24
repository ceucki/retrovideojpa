package be.vdab.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Film;
import be.vdab.services.FilmService;
import be.vdab.services.KlantService;

/**
 * Servlet implementation class BevestigenServlet
 */
@WebServlet("/bevestigen.htm")
public class BevestigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/bevestigen.jsp";
	private static final String MANDJE = "mandje";
	private static final String REDIRECT_URL = "%s/rapport.htm";
	private static final transient FilmService filmService = new FilmService();
	private static final transient KlantService klantService = new KlantService();
	private static final String RESERVATIERAPPORT = "gelukteMislukteReservaties";	
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getQueryString() != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			request.setAttribute("klant", klantService.read(id));			
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<String> mandje = (Set<String>) session.getAttribute(MANDJE);			
			request.setAttribute("mandje", mandje);
			request.setAttribute("aantalFilms", mandje.size());
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);			
			Map<String, Film> gelukteMislukteReservaties = new HashMap<>();
			for (int idFilm : mandje) {
				Boolean geluktMislukt = filmService.reserveren(idFilm, Integer.parseInt(request.getParameter("id")));
				if (geluktMislukt){
					gelukteMislukteReservaties.put("gelukt", filmService.read(idFilm));
				}else {
					gelukteMislukteReservaties.put("mislukt", filmService.read(idFilm));
				}			
				
			}			
			
			session.setAttribute(RESERVATIERAPPORT,
					gelukteMislukteReservaties);

		}
		response.sendRedirect(String.format(REDIRECT_URL,	
				request.getContextPath()));
	}

}
