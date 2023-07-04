package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Retrait;
import fr.eni.EniBay.bo.Utilisateur;

public interface RetraitService {
	List<Retrait> getRetraits();

	void ajouterRetrait(Retrait retrait, ArticleVendu article, Utilisateur utilisateur);

	void save(Retrait retrait, ArticleVendu article, Utilisateur utilisateur);
}
