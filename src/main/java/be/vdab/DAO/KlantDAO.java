package be.vdab.DAO;

import java.util.List;

import be.vdab.entities.Klant;

public class KlantDAO extends AbstractDAO{	

	public List<Klant> findKlant(String naam){
		return getEntityManager().createNamedQuery("Klant.findKlant", Klant.class).setParameter("naam", '%' + naam +'%').getResultList();
	}
	
	public Klant read(int id){
		return getEntityManager().find(Klant.class, id);
	}
	
	
}
