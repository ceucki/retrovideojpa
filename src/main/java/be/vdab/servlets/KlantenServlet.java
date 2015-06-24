package be.vdab.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.services.KlantService;

/**
 * Servlet implementation class KlantenServlet
 */
@WebServlet("/klant.htm")
public class KlantenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/klanten.jsp";
	private static final transient KlantService klantService = new KlantService();	

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getQueryString() != null) {			
			String naam = request.getParameter("zoekKlant");
			if (!naam.isEmpty()) { 				
				request.setAttribute("klanten", klantService.findKlant(naam));
			} else {
				request.setAttribute("fout", "Tik minstens 1 letter");
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}	
	

}
