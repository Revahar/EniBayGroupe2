package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import fr.eni.EniBay.bo.LoginForm;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.UtilisateurDAO;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{
 	private final UtilisateurDAO utilisateurDAO;
    private final List<Utilisateur> lstUtilisateurs;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.lstUtilisateurs = new ArrayList<>();
    }
    
    @Override
    public List<Utilisateur> getUtilisateurs() {
        return utilisateurDAO.findAll();
    }
    
    @Override
    public void ajouterUtilisateur(Utilisateur utilisateur) {
    	lstUtilisateurs.add(utilisateur);
        save(utilisateur);
    }

    @Override
    public void save(Utilisateur utilisateur) {
    	utilisateurDAO.save(utilisateur);
    }
    
    @Override
    public void signIn(String identifiant, String password) {
    	System.out.println("true");
    	return;
    }

    @Override
    public Utilisateur findById(Integer id) {
        return utilisateurDAO.findById(id);
    }

    @Override
    public void delete(Utilisateur utilisateur) { utilisateurDAO.delete(utilisateur); }

	@Override
	public boolean verifierConnexion(LoginForm loginForm) {
	    String email = loginForm.getEmail();
	    String motDePasse = loginForm.getPassword();
	    
	    // Effectuez ici la logique de vérification des informations de connexion
	    // Vérifiez si les informations de connexion sont valides en les comparant à celles stockées dans la base de données
	    
	    Utilisateur utilisateur = utilisateurDAO.findByName(email); // Supposons que vous avez une méthode findByPseudo dans votre DAO
	    
	    if (utilisateur != null && utilisateur.getMot_de_passe().equals(motDePasse)) {
	    	System.out.println("login ok");
	        return true; // Les informations de connexion sont valides
	        
	    } else {
	    	System.out.println("pas le bon login");
	        return false; // Les informations de connexion sont incorrectes
	    }
	}

}

