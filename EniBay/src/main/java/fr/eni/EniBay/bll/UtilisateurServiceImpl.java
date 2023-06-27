package fr.eni.EniBay.bll;

import java.util.ArrayList;
import java.util.List;

import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.UtilisateurDAO;

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
    public void ajouterUtilisateur(Utilisateur categorie) {
    	lstUtilisateurs.add(categorie);
        afficher(categorie);
    }

    @Override
    public void afficher(Utilisateur utilisateur) {
    	utilisateurDAO.save(utilisateur);
    }
}
