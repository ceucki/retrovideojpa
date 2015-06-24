package be.vdab.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.vdab.entities.Film;

/**
 * Servlet implementation class RapportServlet
 */
@WebServlet("/rapport.htm")
public class RapportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/rapport.jsp";
	private static final String RESERVATIERAPPORT = "gelukteMislukteReservaties";

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			@SuppressWarnings("unchecked")
			Map<String, Film> gelukteMislukteReservaties = (Map<String, Film>) session
					.getAttribute(RESERVATIERAPPORT);
			Set<Film> mislukteReservaties = new HashSet<>();
			for (Map.Entry<String, Film> entry : gelukteMislukteReservaties.entrySet()) {
				if (entry.getKey().equals("mislukt")) {
					mislukteReservaties.add( entry.getValue());
				}
			}
			if (mislukteReservaties.isEmpty()) {
				request.setAttribute("ReservatieGeluktMislukt", "OK");
			} else {
				request.setAttribute("ReservatieGeluktMislukt",
						mislukteReservaties);
			}

			session.invalidate();
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
