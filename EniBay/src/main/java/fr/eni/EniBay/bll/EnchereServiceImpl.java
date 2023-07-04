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
    private final List<Enchere> lstEncheres;

    public EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
        this.lstEncheres = new ArrayList<>();
    }
    
    @Override
    public List<Enchere> getEncheres() {
        return enchereDAO.findAll();
    }
    
    @Override
    public void ajouterEnchere(Enchere enchere, ArticleVendu article, Utilisateur utilisateur) {
        lstEncheres.add(enchere);
        afficher(enchere, article, utilisateur);
    }

    @Override
    public void afficher(Enchere enchere, ArticleVendu article, Utilisateur utilisateur) {
        enchereDAO.save(enchere, article, utilisateur);
    }

    
//    //pour afficher les enchères dans le carousel
//	@Override
//	public List<Enchere> getTroisDernieresEncheres() {
//		// TODO Auto-generated method stub
//		return null;
//	}
   
}
