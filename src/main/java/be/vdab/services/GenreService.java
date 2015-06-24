package be.vdab.services;

import java.util.List;

import be.vdab.DAO.GenreDAO;
import be.vdab.entities.Genre;

public class GenreService {
private GenreDAO  genreDAO = new GenreDAO();

public Genre read(long id){
	return genreDAO.read(id);
}

public List<Genre> findAll(){
	return genreDAO.findAll();
}
}
