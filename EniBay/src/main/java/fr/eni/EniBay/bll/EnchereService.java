package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;

public interface EnchereService {

	List<Enchere> getEncheres();

	void ajouterEnchere(Enchere enchere, ArticleVendu article, Utilisateur utilisateur);

	void afficher(Enchere enchere, ArticleVendu article,Utilisateur utilisateur);

	List<Enchere> getEncheresEnCours(String categorie, String nom_article);

}
