package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.Retrait;
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
    public void ajouterRetrait(Retrait retrait) {
    	lstRetraits.add(retrait);
        afficher(retrait);
    }

    @Override
    public void afficher(Retrait retrait) {
    	retraitDAO.save(retrait);
    }
}
