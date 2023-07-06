package fr.eni.EniBay.dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;
import fr.eni.EniBay.dal.UtilisateurDAOSqlServeurImpl.UtilisateurRowMapper;

@Repository
@Primary
public class EnchereDAOSqlServerImpl implements EnchereDAO {

	//private final static String SELECT_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private final static String INSERT = "INSERT INTO encheres (no_utilisateur, no_article, date_enchere, montant_enchere) VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
	private final static String UPDATE = "UPDATE encheres SET no_utilisateur = :no_utilisateur, no_article = :no_article, date_enchere = :date_enchere, montant_enchere = :montant_enchere WHERE id = :id";
	private final static String SELECT_BY_ID = "SELECT enchere.no_article, enchere.no_utilisateur, enchere.date_enchere, enchere.montant_enchere AS enchere_ " +
			"FROM enchere enchere " +
			"WHERE enchere.no_article = :no_article";
	private final static String DELETE = "DELETE FROM ENCHERES WHERE no_article = :no_article AND no_utilisateur = :no_utilisateur";
	private final static String FIND_ALL = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private final static String SELECT_BY_ARTICLE = "SELECT TOP 1 no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = :no_article ORDER BY montant_enchere DESC";
	private final static String SELECT_BY_UTILISATEUR = "SELECT no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_utilisateur = :no_utilisateur";
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	class EnchereRowMapper implements RowMapper<Enchere>{
		@Override
		public Enchere mapRow(ResultSet rs, int rowNum) throws SQLException {
			Enchere enchere = new Enchere();
			enchere.setNo_utilisateur(rs.getInt("no_utilisateur"));
			enchere.setNo_article(rs.getInt("no_article"));
			enchere.setDate_enchere(rs.getDate("date_enchere"));
			enchere.setMontant(rs.getInt("montant_enchere"));
			System.out.println(enchere);
			return enchere;
		}
	}

	public EnchereDAOSqlServerImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

//	@Override
//	public List<Enchere> findAll() {
//		return namedParameterJdbcTemplate.query(SELECT_ALL, new BeanPropertyRowMapper<>(Enchere.class));
//	}
	
	@Override
	public List<Enchere> findAll() {
		List<Enchere> encheres;
		encheres = namedParameterJdbcTemplate.query(FIND_ALL, new  EnchereRowMapper());
		return encheres;
	}

	@Override
	public Enchere findById(Integer no_article, Integer no_utilisateur) {
		Enchere src = new Enchere(no_article, no_utilisateur);
		src.setNo_article(no_article, no_utilisateur);
		return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertySqlParameterSource(src),
				new BeanPropertyRowMapper<>(Enchere.class));
	}

	@Override
	public Enchere findByArticle(Integer noArticle) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_article", noArticle);
		List<Enchere> enchere = namedParameterJdbcTemplate.query(
				SELECT_BY_ARTICLE, mapSrc, new EnchereRowMapper()
		);
		if (enchere.isEmpty()) {return null;}
		return enchere.get(0);
	}

	@Override
	public List<Enchere> findByUtilisateur(Integer noUtilisateur) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_utilisateur", noUtilisateur);
		List<Enchere> enchere = namedParameterJdbcTemplate.query(
				SELECT_BY_UTILISATEUR, mapSrc, new EnchereRowMapper()
		);
		if (enchere.isEmpty()) {return null;}
		return enchere;
	}

	@Override
	public void save(Enchere enchere, ArticleVendu article, Utilisateur utilisateur) {
		if (enchere.getNo_article() != null) {
			MapSqlParameterSource mapsrc = new MapSqlParameterSource()
					.addValue("no_article", article.getNo_article())
					.addValue("no_utilisateur", utilisateur.getNo_utilisateur());
			namedParameterJdbcTemplate.update(DELETE, mapsrc);
		}
		// insert
		String query = INSERT;
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("no_utilisateur", utilisateur.getNo_utilisateur())
				.addValue("no_article", article.getNo_article())
				.addValue("date_enchere", LocalDate.now())
				.addValue("montant_enchere", enchere.getMontant());
			
			namedParameterJdbcTemplate.update(query, parameters);
			System.out.println("Insert enchere: " + enchere);
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
}
