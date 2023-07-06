package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Utilisateur;
public interface ArticleVenduDAO {
	
	ArticleVendu findById(Integer idArticleVendu);
	
	List<ArticleVendu> findAll();
	
	void save(ArticleVendu article, Utilisateur utilisateur);
	
	void delete(ArticleVendu articleVendu);

	List<ArticleVendu> findByCategorieAndNomArticleContainingIgnoreCase(String libelle, String nom_article);

	List<ArticleVendu> findByCategorie(String libelle);

	List<ArticleVendu> findByNomArticleContainingIgnoreCase(String nom_article);

	List<ArticleVendu> findAllMyArticles(Utilisateur utilisateur);

}
