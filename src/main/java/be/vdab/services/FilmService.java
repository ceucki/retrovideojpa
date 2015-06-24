package be.vdab.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import be.vdab.DAO.FilmDAO;
import be.vdab.DAO.KlantDAO;
import be.vdab.entities.Film;
import be.vdab.valueobjects.Reservatie;

public class FilmService {

	private final FilmDAO filmDAO = new FilmDAO();
	private final KlantDAO klantDAO = new KlantDAO();

	public Film read(int id) {
		return filmDAO.read(id);
	}

	public List<Film> findFilmTitlesInMandje(Set<Integer> mandje) {
		return filmDAO.findFilmTitlesInMandje(mandje);
	}

	public Boolean reserveren(int filmId, int klantId) {
		Boolean geluktMislukt = false;
		filmDAO.beginTransaction();
		Film film = filmDAO.read(filmId);
		String gelukt = film.reserveren();
		Reservatie reservatie = new Reservatie(klantDAO.read(klantId), new Date());
		film.addReservatie(reservatie);	
		if (gelukt.equals("gelukt") && film.getReservaties().contains(reservatie)){			
			geluktMislukt = true;
		} else {
		geluktMislukt = false;	
		}				
		filmDAO.commit();		
		return geluktMislukt;
	}

}
