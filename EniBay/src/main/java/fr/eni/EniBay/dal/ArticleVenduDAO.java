package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;

public interface ArticleVenduDAO {
	
	ArticleVendu findById(Integer idArticleVendu);
	
	List<ArticleVendu> findAll();
	
	void save(ArticleVendu articleVendu);
	
	void delete(ArticleVendu articleVendu);
}
