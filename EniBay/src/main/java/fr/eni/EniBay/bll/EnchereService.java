package fr.eni.EniBay.bll;

import java.sql.Date;
import java.util.List;

import fr.eni.EniBay.bo.Enchere;
import jakarta.validation.constraints.NotBlank;

public interface EnchereService {

	List<Enchere> getEncheres();

	void ajouterEnchere(Enchere enchere);

	void afficher(Enchere enchere);


	List<Enchere> getTroisDernieresEncheres();

}
