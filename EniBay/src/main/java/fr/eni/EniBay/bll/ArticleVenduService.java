package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;

public interface ArticleVenduService {
	List<ArticleVendu> getArticlesVendus();

	void ajouterArticleVendu(ArticleVendu articleVendu);

	void afficher(ArticleVendu articleVendu);
}
