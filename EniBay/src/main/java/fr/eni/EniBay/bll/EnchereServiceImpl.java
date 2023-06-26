package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.Enchere;
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
    public void ajouterEnchere(Enchere enchere) {
        lstEncheres.add(enchere);
        afficher(enchere);
    }

    @Override
    public void afficher(Enchere enchere) {
        enchereDAO.save(enchere);
    }
}
