package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.Retrait;

public interface RetraitService {
	List<Retrait> getRetraits();

	void ajouterRetrait(Retrait retrait);

	void afficher(Retrait retrait);
}
