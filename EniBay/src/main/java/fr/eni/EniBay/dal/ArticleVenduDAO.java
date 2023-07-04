package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Utilisateur;
public interface ArticleVenduDAO {
	
	ArticleVendu findById(Integer idArticleVendu);
	
	List<ArticleVendu> findAll();
	
	void save(ArticleVendu article, Utilisateur utilisateur);
	
	void delete(ArticleVendu articleVendu);
}
