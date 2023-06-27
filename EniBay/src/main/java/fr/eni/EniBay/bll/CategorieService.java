package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.Categorie;

public interface CategorieService {
	List<Categorie> getCategories();

	void ajouterCategorie(Categorie categorie);

	void afficher(Categorie categorie);
}
