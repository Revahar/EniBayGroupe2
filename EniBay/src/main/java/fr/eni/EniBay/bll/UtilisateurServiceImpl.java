package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

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
}

