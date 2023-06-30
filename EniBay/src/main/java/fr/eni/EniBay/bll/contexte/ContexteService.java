package fr.eni.EniBay.bll.contexte;

import fr.eni.EniBay.bo.Utilisateur;

public interface ContexteService {
	Utilisateur charger(String email);
}
