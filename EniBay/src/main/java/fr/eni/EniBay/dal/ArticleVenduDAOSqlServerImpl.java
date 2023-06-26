package fr.eni.EniBay.dal;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.EniBay.bo.ArticleVendu;

public class ArticleVenduDAOSqlServerImpl implements ArticleVenduDAO{
	
	private final static String SELECT_ALL = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS";
	private final static String FIND_BY_ID = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, no_utilisateur, no_categorie FROM ARTICLES_VENDUS WHERE ARTICLES_VENDUS.no_article= :no_article";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	class ArticleRowMapper implements RowMapper<ArticleVendu>{

		@Override
		public ArticleVendu mapRow(ResultSet rs, int rowNum) throws SQLException {
			ArticleVendu article = new ArticleVendu(null, null, null, null, null, null, null, null, null);
			
			article.setNo_article(rs.getInt("no_article"));
			article.setNom_article(rs.getString("nom_article"));
			article.setDesciption(rs.getString("description"));
			article.setDate_debut_encheres(rs.getDate("date_debut_encheres"));
			article.setDate_fin_encheres(rs.getDate("date_fin_encheres"));
			article.setPrix_initial(rs.getInt("prix_initial"));
			article.setPrix_vente(rs.getInt("prix_vente"));
			article.setNo_utilisateur(rs.getInt("no_utilisateur"));
			article.setNo_categorie(rs.getInt("no_categorie"));
			
			return null;
		}
		
	}

	@Override
	public ArticleVendu findById(Integer idArticleVendu) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_article", idArticleVendu);
		ArticleVendu article = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, mapSrc, new ArticleRowMapper());
		return article;
	}
}
