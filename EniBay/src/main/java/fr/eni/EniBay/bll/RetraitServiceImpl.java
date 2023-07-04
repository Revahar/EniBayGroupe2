package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Retrait;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.RetraitDAO;

@Service
public class RetraitServiceImpl implements RetraitService{
	private final RetraitDAO retraitDAO;
    private final List<Retrait> lstRetraits;

    public RetraitServiceImpl(RetraitDAO retraitDAO) {
        this.retraitDAO = retraitDAO;
        this.lstRetraits = new ArrayList<>();
    }
    
    @Override
    public List<Retrait> getRetraits() {
        return retraitDAO.findAll();
    }
    
    @Override
    public void ajouterRetrait(Retrait retrait, ArticleVendu article, Utilisateur utilisateur) {
    	lstRetraits.add(retrait);
        save(retrait, article, utilisateur);
    }

    @Override
    public void save(Retrait retrait, ArticleVendu article, Utilisateur utilisateur) {
    	retraitDAO.save(retrait, article, utilisateur);
    }
}
