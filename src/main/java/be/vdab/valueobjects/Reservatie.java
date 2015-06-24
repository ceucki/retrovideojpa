package be.vdab.valueobjects;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.vdab.entities.Klant;

@Embeddable

public class Reservatie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date reservatieDatum;
	

	public Date getReservatieDatum() {
		return reservatieDatum;
	}

	public Reservatie(Klant klant, Date reservatieDatum) {
		this.klant=klant;
		this.reservatieDatum=reservatieDatum;
	}

	public Reservatie() {
	};

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name="klantId")
	private Klant klant;


	public Klant getKlant() {
		return klant;
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}
}
