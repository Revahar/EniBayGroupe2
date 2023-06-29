package fr.eni.EniBay.dal;

import java.util.List;
import java.util.Map;

import fr.eni.EniBay.bo.Categorie;

public interface CategorieDAO {
	List<Categorie> findAll();
	
	Categorie findById(Integer id);
	
	void save(Categorie categorie);
	
	Map<Integer, Categorie> getMapCategories();
}
