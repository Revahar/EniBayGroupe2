package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.Categorie;
import fr.eni.EniBay.dal.CategorieDAO;


@Service
public class CategorieServiceImpl implements CategorieService{
    private final CategorieDAO categorieDAO;
    private final List<Categorie> lstCategories;

    public CategorieServiceImpl(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
        this.lstCategories = new ArrayList<>();
    }
    
    @Override
    public List<Categorie> getCategories() {
        return categorieDAO.findAll();
    }
    
    @Override
    public void ajouterCategorie(Categorie categorie) {
        lstCategories.add(categorie);
        afficher(categorie);
    }

    @Override
    public void afficher(Categorie categorie) {
        categorieDAO.save(categorie);
    }
}
