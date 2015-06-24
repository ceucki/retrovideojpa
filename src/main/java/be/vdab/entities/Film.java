package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.vdab.valueobjects.Reservatie;

@Entity
@Table(name = "films")
public class Film implements Serializable {

	/**
	 * 
	 */

	@Id
	@GeneratedValue
	private int id;
	private static final long serialVersionUID = 1L;
	private int voorraad, gereserveerd;
	private String titel;
	private BigDecimal prijs;

	@ElementCollection
	@CollectionTable(name = "reservaties", joinColumns = @JoinColumn(name = "filmId"))
	private Set<Reservatie> reservaties;

	public Set<Reservatie> getReservaties() {
		return Collections.unmodifiableSet(reservaties); 
	}

	

	public void addReservatie(Reservatie reservatie) {
		reservaties.add(reservatie);
	}

	public void removeReservatie(Reservatie reservatie) {
		reservaties.remove(reservatie);
	}

	protected Film() {
	};
	

	public int getBeschikbaar() {
		return getVoorraad() - getGereserveerd();
	}

	public int getId() {
		return id;
	}

	public int getVoorraad() {
		return voorraad;
	}

	public int getGereserveerd() {
		return gereserveerd;
	}

	public String getTitel() {
		return titel;
	}

	public BigDecimal getPrijs() {
		return prijs;
	}

	public String getReserverenMogelijk() {
		if (getGereserveerd() < getVoorraad()) {
			return "reservatie mogelijk";
		} else {
			return "reservatie niet mogelijk";
		}

	}

	// relatie naar genres
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "genreid")
	private Genre genre;

	public Genre getGenre() {
		return genre;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Film)) {
			return false;
		}
		return ((Film) obj).titel.equalsIgnoreCase(titel);

	}

	@Override
	public int hashCode() {
		return titel.toUpperCase().hashCode();
	}

	public boolean reserveren() {  

		if (getBeschikbaar() > 0) {
			gereserveerd++;
			return true;
		} else {
			return false;
		}
		
	}

}
