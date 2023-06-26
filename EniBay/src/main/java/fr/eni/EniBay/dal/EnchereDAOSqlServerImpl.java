package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.Enchere;




@Repository
@Primary
public class EnchereDAOSqlServerImpl implements EnchereDAO{
	
	private final static String SELECT_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private final static String INSERT = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
	private final static String UPDATE = "UPDATE encheres SET no_utilisateur = :no_utilisateur, no_article = :no_article, date_enchere = :date_enchere, montant_enchere = :montant_enchere WHERE id = :id";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private EnchereDAO enchereDAO;

	@Override
	public List<Enchere> findAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Enchere.class));
	}

	@Override
	public void save(Enchere enchere) {
	    if (enchere.getNo_article() == null) {
	        // insert
	        String query = INSERT;
	        SqlParameterSource parameters = new MapSqlParameterSource()
	                   .addValue("no_utilisateur", enchere.getNo_utilisateur())
	                    .addValue("no_article", enchere.getNo_article())
	                    .addValue("date_enchere", enchere.getDate_enchere())
	                    .addValue("montant_enchere", enchere.getMontant());

	        KeyHolder keyHolder = new GeneratedKeyHolder();
	        namedParameterJdbcTemplate.update(query, parameters, keyHolder);
	        enchere.setNo_article(keyHolder.getKey().intValue());
	        System.out.println("Insert enchere: " + enchere);
	    } else {
	        // update
	        String query = UPDATE;
	        SqlParameterSource parameters = new MapSqlParameterSource()
                    .addValue("no_utilisateur", enchere.getNo_utilisateur())
                    .addValue("no_article", enchere.getNo_article())
                    .addValue("date_enchere", enchere.getDate_enchere())
                    .addValue("montant_enchere", enchere.getMontant());
 

	        namedParameterJdbcTemplate.update(query, parameters);
	        System.out.println("Update enchere: " + enchere);
	    }
	}
	
	public EnchereDAO getEnchereDAO() {
		return enchereDAO;
	}

	public void setEnchereDAO(EnchereDAO enchereDAO) {
		this.enchereDAO = enchereDAO;
	}

}
