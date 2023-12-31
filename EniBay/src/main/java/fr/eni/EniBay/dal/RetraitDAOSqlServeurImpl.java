package fr.eni.EniBay.dal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Retrait;
import fr.eni.EniBay.bo.Utilisateur;

@Repository
public class RetraitDAOSqlServeurImpl implements RetraitDAO{
	
	private final static String SELECT_ALL = "SELECT no_article, rue, code_postal, ville FROM RETRAITS";
	private final static String SELECT_BY_ID = "SELECT no_article, rue, code_postal, ville FROM RETRAITS WHERE no_article = :no_article";
	private final static String INSERT = "INSERT INTO RETRAITS (no_article, rue, code_postal, ville) VALUES (:no_article, :rue, :code_postal, :ville)";
	private final static String UPDATE = "UPDATE RETRAITS SET no_article = :no_article, rue = :rue, code_postal = :code_postal, ville = :ville";
	//private final static String DELETE = "delete  RETRAITS where no_article = :no_article" ;

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	//private RetraitDAO retraitDAO;

	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	@Override
	public List<Retrait> findAll() {
		return namedParameterJdbcTemplate.query(
				SELECT_ALL, 
				new BeanPropertyRowMapper<>(Retrait.class)
		);
	}
	
	@Override
	public Retrait findById(Integer id) {
		Map<String, Object> map = new HashMap<>();
		map.put("no_article", id);
		return namedParameterJdbcTemplate.queryForObject(
				SELECT_BY_ID, 
				map, new BeanPropertyRowMapper<>(Retrait.class)
		);
	}

	@Override
	public void save(Retrait retrait, ArticleVendu article, Utilisateur utilisateur) {
		if(retrait.getNo_article() == null) {			
			MapSqlParameterSource mapSrc = new MapSqlParameterSource("rue", utilisateur.getRue());
			mapSrc.addValue("ville", utilisateur.getVille());
			mapSrc.addValue("code_postal", utilisateur.getCode_postal());
			mapSrc.addValue("no_article", article.getNo_article());
			
			namedParameterJdbcTemplate.update(INSERT, mapSrc);
		} else {
			namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(retrait));
		}
		
	}
}
