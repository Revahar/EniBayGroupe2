package fr.eni.EniBay.bll;

import java.util.List;

import fr.eni.EniBay.bo.LoginForm;
import fr.eni.EniBay.bo.Utilisateur;

public interface UtilisateurService {
	List<Utilisateur> getUtilisateurs();

	void ajouterUtilisateur(Utilisateur utilisateur);

	void save(Utilisateur utilisateur);

	void signIn(String identifiant, String password);

	boolean verifierConnexion(LoginForm loginForm);


}
