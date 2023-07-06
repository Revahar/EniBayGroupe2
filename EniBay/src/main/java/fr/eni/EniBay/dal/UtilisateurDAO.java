package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Utilisateur;

public interface UtilisateurDAO {
	List<Utilisateur> findAll();
	
	Utilisateur findById(Integer id);
	
	void save(Utilisateur utilisateur);
	
	void delete(Utilisateur utilisateur);

	Utilisateur findByName(String id);

	void updateCredit(Utilisateur utilisateur);
}
