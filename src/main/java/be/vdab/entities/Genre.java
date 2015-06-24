package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="genres")
public class Genre implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;	
	
	@Id
	@GeneratedValue
	private long id;
		
	private String naam;
	
	
	protected Genre(){};

	public long getId() {
		return id;
	}
	
	public String getNaam() {
		return naam;
	}
	
	//relatie naar films
	@OneToMany(mappedBy="genre")
	private Set<Film> films;
	
	public Set<Film>getFilms(){
		return Collections.unmodifiableSet(films);
	}
	
	public Film addFilm(Film film){
		getFilms().add(film);
		if(film.getGenre() !=this){
			film.setGenre(this);
		}
		return film;
	}
	
	public Film removeFilm(Film film){
		getFilms().remove(film);
		if(film.getGenre()==this){
			film.setGenre(null);
		}
		return film;
	}
	
	
}
