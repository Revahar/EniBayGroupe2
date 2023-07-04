package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.ArticleVenduDAO;

@Service
public class ArticleVenduServiceImpl implements ArticleVenduService{
	 private final ArticleVenduDAO articleVenduDAO;
	    private final List<ArticleVendu> lstArticlesVendus;

	    public ArticleVenduServiceImpl(ArticleVenduDAO articleVenduDAO) {
	        this.articleVenduDAO = articleVenduDAO;
	        this.lstArticlesVendus = new ArrayList<>();
	    }
	    
	    @Override
	    public List<ArticleVendu> getArticlesVendus() {
	        return articleVenduDAO.findAll();
	    }
	    
	    @Override
	    public void ajouterArticleVendu(ArticleVendu articleVendu, Utilisateur utilisateur) {
	        lstArticlesVendus.add(articleVendu);
	        save(articleVendu, utilisateur);
	    }

	    @Override
	    public void save(ArticleVendu articleVendu, Utilisateur utilisateur) {
	    	articleVenduDAO.save(articleVendu, utilisateur);
	    }

		@Override
		public ArticleVendu getArticleVenduById(Integer no_article) {
			return articleVenduDAO.findById(no_article);
		}
}
