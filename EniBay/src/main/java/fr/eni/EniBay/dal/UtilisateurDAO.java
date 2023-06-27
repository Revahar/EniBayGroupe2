package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Utilisateur;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	
	Utilisateur findById(Integer id);
	
	void save(Utilisateur utilisateur);
}
