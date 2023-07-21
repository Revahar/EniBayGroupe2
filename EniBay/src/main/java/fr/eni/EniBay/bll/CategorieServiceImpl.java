package fr.eni.EniBay.bll;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.Categorie;
import fr.eni.EniBay.dal.CategorieDAO;


@Service("CategorieService")
public class CategorieServiceImpl implements CategorieService{
    private CategorieDAO categorieDAO;
    private List<Categorie> lstCategories;

    public CategorieServiceImpl(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }
    
    @Override
    public List<Categorie> getListCategories() {
        return categorieDAO.findAll();
    }
    
    @Override
    public void ajouterCategorie(Categorie categorie) {
        lstCategories.add(categorie);
        save(categorie);
    }

    @Override
    public void save(Categorie categorie) {
        categorieDAO.save(categorie);
    }

	@Override
	public Map<Integer, Categorie> getMapCategories() {
		return categorieDAO.getMapCategories();
	}

	@Override
	public Categorie getCategorieById(Integer no_categorie) {
		return categorieDAO.findById(no_categorie);
	}
}
