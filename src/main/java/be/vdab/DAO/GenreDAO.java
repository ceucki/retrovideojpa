package be.vdab.DAO;

import java.util.List;

import be.vdab.entities.Genre;

public class GenreDAO extends AbstractDAO {
		
	public Genre read(long id){
		return getEntityManager().find(Genre.class, id);
	}
	
	public List<Genre> findAll(){
		
		return getEntityManager().createNamedQuery("Genre.findAll",Genre.class).getResultList();
	}

}
