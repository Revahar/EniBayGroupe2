package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Categorie;

public interface CategorieDAO {
	List<Categorie> findAll();
	
	Categorie findById(Integer id);
	
	void save(Categorie categorie);
}
