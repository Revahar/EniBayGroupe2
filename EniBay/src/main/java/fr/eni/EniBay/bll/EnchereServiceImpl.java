package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.EnchereDAO;


@Service
public class EnchereServiceImpl implements EnchereService{
    private final EnchereDAO enchereDAO;

    public EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
    }
    
    @Override
    public List<Enchere> getEncheres() {
        return enchereDAO.findAll();
    }

    @Override
    public Enchere findByAtricle(Integer no_article){
        return enchereDAO.findByArticle(no_article);
    }

    @Override
    public List<Enchere> findByUtilisateur(Integer no_utilisateur) {
        return enchereDAO.findByUtilisateur(no_utilisateur);
    }

    @Override
    public void save(Enchere enchere, ArticleVendu article, Utilisateur utilisateur) {
        enchereDAO.save(enchere, article, utilisateur);
    }

	@Override
	public List<Enchere> getEncheresEnCours(String categorie, String nom_article) {
		// TODO Auto-generated method stub
		return null;
	}

    
//    //pour afficher les enchères dans le carousel
//	@Override
//	public List<Enchere> getTroisDernieresEncheres() {
//		// TODO Auto-generated method stub
//		return null;
//	}
    
    
   
}
