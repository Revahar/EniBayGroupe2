package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.Categorie;

@Repository
public class CategorieDAOSqlServerImpl implements CategorieDAO{
	
	private final static String SELECT_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private final static String INSERT = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
	private final static String UPDATE = "UPDATE encheres SET no_utilisateur = :no_utilisateur, no_article = :no_article, date_enchere = :date_enchere, montant_enchere = :montant_enchere WHERE id = :id";
	
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private CategorieDAO categorieDAO;

	@Override
	public List<Categorie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Categorie findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Categorie categorie) {
		// TODO Auto-generated method stub
		
	}

	public CategorieDAO getCategorieDAO() {
		return categorieDAO;
	}

	public void setCategorieDAO(CategorieDAO categorieDAO) {
		this.categorieDAO = categorieDAO;
	}
}
