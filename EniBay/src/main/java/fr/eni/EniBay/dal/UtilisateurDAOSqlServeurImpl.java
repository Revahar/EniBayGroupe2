package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.Utilisateur;

@Repository
public class UtilisateurDAOSqlServeurImpl implements UtilisateurDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private UtilisateurDAO utilisateurDAO;

	@Override
	public List<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Utilisateur findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Utilisateur utilisateur) {
		// TODO Auto-generated method stub
		
	}

	public UtilisateurDAO getUtilisateurDAO() {
		return utilisateurDAO;
	}

	public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}
}
