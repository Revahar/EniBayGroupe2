package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.ArticleVendu;
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
	    public void ajouterArticleVendu(ArticleVendu articleVendu) {
	        lstArticlesVendus.add(articleVendu);
	        afficher(articleVendu);
	    }

	    @Override
	    public void afficher(ArticleVendu articleVendu) {
	    	articleVenduDAO.save(articleVendu);
	    }
}
