package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;

public interface ArticleVenduService {
	List<ArticleVendu> getArticlesVendus();

	void ajouterArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateur);
	
	ArticleVendu getArticleVenduById(Integer no_article);

	void save(ArticleVendu articleVendu, Utilisateur utilisateur);

	List<ArticleVendu> getArticlesEnCours(String categorie, String nom_article);

	List<ArticleVendu> getAllMyArticles(Utilisateur utilisateur);

	ArticleVendu findById(Integer no_article);


}
