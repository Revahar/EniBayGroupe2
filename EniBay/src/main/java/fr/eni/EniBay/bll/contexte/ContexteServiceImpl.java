package fr.eni.EniBay.bll.contexte;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.dal.UtilisateurDAO;
import fr.eni.EniBay.bo.Utilisateur;

@Service
public class ContexteServiceImpl implements ContexteService {

	private UtilisateurDAO utilisateurDAO;

	public ContexteServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public Utilisateur charger(String email) {
		return utilisateurDAO.findByName(email);
	}
}
