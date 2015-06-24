package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Film;
import be.vdab.services.FilmService;

/**
 * Servlet implementation class FilmBestellenServlet
 */
@WebServlet("/reservaties/filmbestellen.htm")
public class MandjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/filmbestellen.jsp";
	private static final transient FilmService filmService = new FilmService();
	private static final String MANDJE = "mandje";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (!mandje.isEmpty()) {				
				List<Film> filmsInMandje = filmService.findFilmTitlesInMandje(mandje);
				request.setAttribute("filmsInMandje", filmsInMandje
						);
				
				BigDecimal totalePrijs = BigDecimal.ZERO;
				for (Film film:filmsInMandje){
					totalePrijs = totalePrijs.add(film.getPrijs());
				}				
				request.setAttribute("totalePrijs",
						totalePrijs);

			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session != null) {
			@SuppressWarnings("unchecked")
			Set<Integer> mandje = (Set<Integer>) session.getAttribute(MANDJE);
			if (mandje != null) {
				if (request.getParameter("id") != null) {
					for (String id : request.getParameterValues("id")) {
						mandje.remove(Integer.parseInt(id));
					}
				}
				session.setAttribute(MANDJE, mandje);
			}
		}

		response.sendRedirect(response.encodeRedirectURL(request
				.getRequestURI()));
	}
	

}
