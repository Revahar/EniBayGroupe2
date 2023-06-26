package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.Enchere;

public interface EnchereService {

	List<Enchere> getEncheres();

	void ajouterEnchere(Enchere enchere);

	void afficher(Enchere enchere);

}
