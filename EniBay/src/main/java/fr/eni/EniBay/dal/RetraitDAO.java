package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Retrait;
import fr.eni.EniBay.bo.Utilisateur;

public interface RetraitDAO {
	List<Retrait> findAll();
	
	Retrait findById(Integer id);
	
	void save(Retrait retrait, ArticleVendu article, Utilisateur utilisateur);
}
