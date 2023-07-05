package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.LoginForm;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
 	private final UtilisateurDAO utilisateurDAO;
    private final List<Utilisateur> lstUtilisateurs;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO, PasswordEncoder passwordEncoder) {
        this.utilisateurDAO = utilisateurDAO;
        this.lstUtilisateurs = new ArrayList<>();
		this.passwordEncoder = passwordEncoder;
    }
    
    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurDAO.findAll();
    }
    
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	utilisateur.setMot_de_passe(passwordEncoder.encode(utilisateur.getMot_de_passe()));
    	lstUtilisateurs.add(utilisateur);
        save(utilisateur);
    }

    @Override
    public void save(Utilisateur utilisateur) {
        utilisateur.setMot_de_passe(passwordEncoder.encode(utilisateur.getMot_de_passe()));
        utilisateurDAO.save(utilisateur);
    }
    
    @Override
    public void signIn(String identifiant, String password) {
    	System.out.println("vrai");
    	return;
    }

    @Override
    public Utilisateur findById(Integer id) {
        return utilisateurDAO.findById(id);
    }
    @Override
    public Utilisateur findByName(String id) {
        return utilisateurDAO.findByName(id);
    }

    @Override
    public void delete(Utilisateur utilisateur) { utilisateurDAO.delete(utilisateur); }

	@Override
	public boolean verifierConnexion(LoginForm loginForm) {
	    String email = loginForm.getUsername();
	    String motDePasse = loginForm.getPassword();
	    
	    // Effectuez ici la logique de vérification des informations de connexion
	    // Vérifiez si les informations de connexion sont valides en les comparant à celles stockées dans la base de données
	    
	    Utilisateur utilisateur = utilisateurDAO.findByName(email); // Supposons que vous avez une méthode findByPseudo dans votre DAO

	    return true;
	}

}

