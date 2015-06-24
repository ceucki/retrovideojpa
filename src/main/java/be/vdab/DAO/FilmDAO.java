package be.vdab.DAO;

import java.util.List;
import java.util.Set;

import be.vdab.entities.Film;

public class FilmDAO extends AbstractDAO {
	
	public Film read(int id){
		return getEntityManager().find(Film.class, id);
	}
	
	public List<Film> findFilmTitlesInMandje(Set<Integer> mandje){
		return getEntityManager().createNamedQuery("Film.findFilmTitlesInMandje", Film.class).setParameter("filmids", mandje).getResultList();
	}
		
}
