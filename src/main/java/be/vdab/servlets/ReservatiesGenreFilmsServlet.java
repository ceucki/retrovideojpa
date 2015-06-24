package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.GenreService;

/**
 * Servlet implementation class ReservatiesGenreFilmsServlet
 */
@WebServlet("/reservaties/films.htm")
public class ReservatiesGenreFilmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/reservatiesgenrefilms.jsp";
	private static final transient GenreService genreService = new GenreService();	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("genre", genreService.read((Integer.parseInt(request.getParameter("id")))));	
		
		request.setAttribute("genres", genreService.findAll());		
		
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	
	

}
