package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;

public interface EnchereService {

	List<Enchere> getEncheres();

	Enchere findByAtricle(Integer no_article);
	
	List<Enchere> findByUtilisateur(Integer no_utilisateur);

	void save(Enchere enchere, ArticleVendu article,Utilisateur utilisateur);

	List<Enchere> getEncheresEnCours(String categorie, String nom_article);

}
