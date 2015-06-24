package be.vdab.services;

import java.util.List;

import be.vdab.DAO.KlantDAO;
import be.vdab.entities.Klant;

public class KlantService {

	private KlantDAO klantDAO = new KlantDAO();
	
	public Klant read(int id){
		return klantDAO.read(id);
	}
	
	public List<Klant> findKlant(String naam){
		return klantDAO.findKlant(naam);
	}
}
