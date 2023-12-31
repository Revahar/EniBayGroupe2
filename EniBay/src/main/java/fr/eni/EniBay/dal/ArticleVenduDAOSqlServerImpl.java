package fr.eni.EniBay.dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Utilisateur;

@Repository
public class ArticleVenduDAOSqlServerImpl implements ArticleVenduDAO{
	
	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final static String FIND_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_article= :no_article";
	private final static String INSERT = "INSERT INTO ARTICLES_VENDUS (nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie) "
			+ "VALUES (:nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente, :no_utilisateur, :no_categorie)";
	
	private final static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article= :nom_article, description= :description, date_debut_encheres= :date_debut_encheres, date_fin_encheres= :date_fin_encheres, prix_initial= :prix_initial, prix_vente= :prix_vente, no_utilisateur= :no_utilisateur, no_categorie= :no_categorie WHERE no_article= :no_article";
	private final static String DELETE = "DELETE FROM ARTICLES_VENDUS WHERE no_article= :no_article";
	//private final static String UPDATE_PRIX = "UPDATE ARTICLES_VENDUS SET prix_vente= :prix_vente WHERE no_article= :no_article";
	private final static String SELECT_ALL_MY_ARTICLES = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE no_utilisateur= :no_utilisateur";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	class ArticleRowMapper implements RowMapper<ArticleVendu>{

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu article = new ArticleVendu();
			
			article.setNo_article(rs.getInt("no_article"));
			article.setNom_article(rs.getString("nom_article"));
			article.setDescription(rs.getString("description"));
			article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
			article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
			article.setPrix_initial(rs.getInt("prix_initial"));
			article.setPrix_vente(rs.getInt("prix_vente"));
			article.setNo_utilisateur(rs.getInt("no_utilisateur"));
			article.setNo_categorie(rs.getInt("no_categorie"));
			
			return article;
		}		
	}

	@Override
	public ArticleVendu findById(Integer idArticleVendu) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_article", idArticleVendu);
		ArticleVendu article = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, mapSrc, new ArticleRowMapper());
		return article;
	}

	@Override
	public List<ArticleVendu> findAll() {
		List<ArticleVendu> listArticles;
		listArticles = namedParameterJdbcTemplate.query(SELECT_ALL, new ArticleRowMapper());
		return listArticles;
	}

	@Override
	public void save(ArticleVendu article, Utilisateur utilisateur) {
		if(article.getNo_article() == null) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			MapSqlParameterSource mapSrc = new MapSqlParameterSource("nom_article", article.getNom_article());			
			mapSrc.addValue("description", article.getDescription());
			mapSrc.addValue("date_debut_encheres", article.getDate_debut_encheres());
			mapSrc.addValue("date_fin_encheres", article.getDate_fin_encheres());
			mapSrc.addValue("prix_initial", article.getPrix_initial());
			mapSrc.addValue("prix_vente", article.getPrix_initial());
			mapSrc.addValue("no_utilisateur", utilisateur.getNo_utilisateur());
			mapSrc.addValue("no_categorie", article.getNo_categorie());
			
			namedParameterJdbcTemplate.update(INSERT, mapSrc, keyHolder);			
			article.setNo_article(keyHolder.getKey().intValue());
		} else {
			namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(article));
		}
	}
	
	@Override
	public void delete(ArticleVendu articleVendu) {
		namedParameterJdbcTemplate.update(DELETE, new BeanPropertySqlParameterSource(articleVendu));
	}

	@Override
	public List<ArticleVendu> findByCategorieAndNomArticleContainingIgnoreCase(String libelle, String nom_article) {
	    String sql = SELECT_ALL + " WHERE ARTICLES_VENDUS.no_categorie = :no_categorie AND LOWER(ARTICLES_VENDUS.nom_article) LIKE LOWER(:nom_article)";
	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("no_categorie", getCategorieIdByLibelle(libelle));
	    paramMap.addValue("nom_article", "%" + nom_article + "%");
	    return namedParameterJdbcTemplate.query(sql, paramMap, new ArticleRowMapper());
	}

	 

	@Override
	public List<ArticleVendu> findByCategorie(String libelle) {
	    String sql = SELECT_ALL + " WHERE ARTICLES_VENDUS.no_categorie = :no_categorie";
	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("no_categorie", getCategorieIdByLibelle(libelle));
	    return namedParameterJdbcTemplate.query(sql, paramMap, new ArticleRowMapper());
	}

	 

	@Override
	public List<ArticleVendu> findByNomArticleContainingIgnoreCase(String nom_article) {
	    String sql = SELECT_ALL + " WHERE LOWER(ARTICLES_VENDUS.nom_article) LIKE LOWER(:nom_article)";
	    MapSqlParameterSource paramMap = new MapSqlParameterSource();
	    paramMap.addValue("nom_article", "%" + nom_article + "%");
	    return namedParameterJdbcTemplate.query(sql, paramMap, new ArticleRowMapper());
	}

	 

	private Integer getCategorieIdByLibelle(String libelle) {
	    String sql = "SELECT no_categorie FROM CATEGORIES WHERE LOWER(libelle) = LOWER(:libelle)";
	    MapSqlParameterSource paramMap = new MapSqlParameterSource("libelle", libelle);
	    return namedParameterJdbcTemplate.queryForObject(sql, paramMap, Integer.class);
	}
	
	@Override
	public List<ArticleVendu> findAllMyArticles(Utilisateur utilisateur) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_utilisateur", utilisateur.getNo_utilisateur());
		List<ArticleVendu> listMesArticles;
		listMesArticles = namedParameterJdbcTemplate.query(SELECT_ALL_MY_ARTICLES, mapSrc, new ArticleRowMapper());
		if (listMesArticles.isEmpty()) {
			return null;
		}
		return listMesArticles;
	}


}
