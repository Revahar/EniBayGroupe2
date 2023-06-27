package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	private final static String SELECT_BY_ID = "SELECT enchere.no_article, enchere.no_utilisateur, enchere.date_enchere, enchere.montant_enchere AS enchere_ " +
		    "FROM enchere enchere " +
		    "WHERE enchere.no_article = :no_article";
	private final static String DELETE = "DELETE FROM enchere WHERE no_article = :no_article";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private EnchereDAO enchereDAO;

	@Override
	public List<Enchere> findAll() {
		return namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Enchere.class));
	}
	
	@Override
	public Enchere findById(Integer no_article, Integer no_utilisateur) {
		Enchere src = new Enchere(no_article, no_utilisateur);
		src.setNo_article(no_article, no_utilisateur);
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertySqlParameterSource(src),
				new BeanPropertyRowMapper<>(Enchere.class));
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
	
    @Override
    public void delete(Integer no_article, Integer no_utilisateur) {
        namedParameterJdbcTemplate.update(DELETE, new MapSqlParameterSource("no_article", no_article));
        System.out.println("Delete enchere with no_article: " + no_article);
    }
    
	@Override
	public Enchere read(Integer no_article, Integer no_utilisateur) {
	    Enchere src = new Enchere(no_article, no_utilisateur);
	    src.setNo_article(no_article, no_utilisateur);
	    List<Enchere> encheres = namedParameterJdbcTemplate.query(SELECT_BY_ID, new BeanPropertySqlParameterSource(src),
	            new BeanPropertyRowMapper<>(Enchere.class));
	    return encheres.isEmpty() ? null : encheres.get(0);
	}
	
	
	
	public EnchereDAO getEnchereDAO() {
		return enchereDAO;
	}

	public void setEnchereDAO(EnchereDAO enchereDAO) {
		this.enchereDAO = enchereDAO;
	}



}
