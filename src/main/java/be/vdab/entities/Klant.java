package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="klanten")
public class Klant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private int id;
	private String familienaam,voornaam,straatNummer,postcode,gemeente;	
		
	protected Klant(){};
	
	public int getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}	

	public String getStraatNummer() {
		return straatNummer;
	}	

	public String getPostcode() {
		return postcode;
	}	

	public String getGemeente() {
		return gemeente;
	}	
	
	public String getFamilienaam() {
		return familienaam;
	}	
	

	

}
