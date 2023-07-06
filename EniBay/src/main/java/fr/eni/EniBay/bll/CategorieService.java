package fr.eni.EniBay.bll;

import java.util.List;
import java.util.Map;

import fr.eni.EniBay.bo.Categorie;

public interface CategorieService {
	List<Categorie> getListCategories();

	void ajouterCategorie(Categorie categorie);

	void save(Categorie categorie);
	
	public Map<Integer, Categorie> getMapCategories();
}
