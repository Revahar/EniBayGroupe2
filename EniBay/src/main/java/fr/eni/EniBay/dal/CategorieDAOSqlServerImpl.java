package fr.eni.EniBay.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
//import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.Categorie;

@Repository
public class CategorieDAOSqlServerImpl implements CategorieDAO{
	
	private final static String SELECT_ALL = "SELECT no_categorie, libelle FROM CATEGORIES";
	private final static String SELECT_BY_ID = "SELECT * FROM CATEGORIES WHERE no_categorie = :no_categorie";
	//private final static String INSERT = "INSERT INTO CATEGORIES (no_categorie, libelle) VALUES (:no_categorie, :libelle)";
	//private final static String UPDATE = "UPDATE CATEGORIES SET no_categorie = :no_categorie, libelle = :libelle";
	//private final static String DELETE = "delete  CATEGORIES where no_categorie = :no_categorie" ;

	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private List<Categorie> categories;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Categorie> findAll() {
		return namedParameterJdbcTemplate.query(
				SELECT_ALL, 
				new BeanPropertyRowMapper<>(Categorie.class)
		);
	}
	
	@Override
	public Categorie findById(Integer no_categorie) {
		Map<String, Object> params = new HashMap<>();
		params.put("no_categorie", no_categorie);
		
		Categorie categorie = null;
		
		categorie = namedParameterJdbcTemplate.queryForObject(
				SELECT_BY_ID, 
				params,
				new BeanPropertyRowMapper<>(Categorie.class));
		
		return categorie;
	}
	
	@Override
	public Map<Integer, Categorie> getMapCategories() {
		var mapCategories = new HashMap<Integer, Categorie>();
		categories.forEach(categorie -> mapCategories.put(categorie.getNo_categorie(), categorie));
		return mapCategories;
	}
	
	@Override
	public void save(Categorie categorie) {
	}	
}
