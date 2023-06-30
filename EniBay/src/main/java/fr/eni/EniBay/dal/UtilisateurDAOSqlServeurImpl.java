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

import fr.eni.EniBay.bo.Utilisateur;

@Repository
public class UtilisateurDAOSqlServeurImpl implements UtilisateurDAO{
	
	private final static String INSERT = "INSERT INTO UTILISATEURS (pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) "
			+ "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit, :administrateur)";
	private final static String FIND_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS";
	private final static String FIND_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit FROM UTILISATEURS WHERE no_utilisateur= :no_utilisateur";
	private final static String DELETE = "DELETE FROM UTILISATEURS WHERE no_utilisateur= :no_utilisateur";
	private final static String FIND_BY_NAME = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur FROM UTILISATEURS WHERE pseudo= :id OR email= :id";
	private final static String UPDATE = "UPDATE UTILISATEURS SET pseudo= :pseudo, nom= :nom, prenom= :prenom, email= :email, telephone= :telephone, rue= :rue, code_postal= :code_postal, ville= :ville, mot_de_passe= :mot_de_passe, credit= :credit, administrateur= :administrateur WHERE no_utilisateur= :no_utilisateur";
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setUtilisateurDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}
	
	class UtilisateurRowMapper implements RowMapper<Utilisateur>{
			
		@Override
		public Utilisateur mapRow(ResultSet rs, int rowNum) throws SQLException {
			Utilisateur utilisateur = new Utilisateur();
			utilisateur.setNo_utilisateur(rs.getInt("no_utilisateur"));
			utilisateur.setPseudo(rs.getString("pseudo"));
			utilisateur.setNom(rs.getString("nom"));
			utilisateur.setPrenom(rs.getString("prenom"));
			utilisateur.setEmail(rs.getString("email"));
			utilisateur.setTelephone(rs.getString("telephone"));
			utilisateur.setRue(rs.getString("rue"));
			utilisateur.setCode_postal(rs.getString("code_postal"));
			utilisateur.setVille(rs.getString("ville"));
//			utilisateur.setMot_de_passe(rs.getString("mot_de_passe"));
			utilisateur.setCredit(rs.getInt("credit"));
//			utilisateur.setAdministrateur(rs.getBoolean("administrateur"));
			System.out.println(utilisateur);
			return utilisateur;
		}
	}
	
	@Override
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs;
		utilisateurs = namedParameterJdbcTemplate.query(FIND_ALL, new  UtilisateurRowMapper());
		return utilisateurs;
	}
	
	@Override
	public Utilisateur findById(Integer id) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource("no_utilisateur", id);
		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(FIND_BY_ID, mapSrc, new UtilisateurRowMapper());
		return utilisateur;
	}

	@Override
	public void save(Utilisateur utilisateur) {
		if(utilisateur.getNo_utilisateur() == null) {
			KeyHolder keyHolder = new GeneratedKeyHolder();
			
			MapSqlParameterSource mapSrc = new MapSqlParameterSource("pseudo", utilisateur.getPseudo());
			mapSrc.addValue("nom", utilisateur.getNom());
			mapSrc.addValue("prenom", utilisateur.getPrenom());
			mapSrc.addValue("email", utilisateur.getEmail());
			mapSrc.addValue("telephone", utilisateur.getTelephone());
			mapSrc.addValue("rue", utilisateur.getRue());
			mapSrc.addValue("code_postal", utilisateur.getCode_postal());
			mapSrc.addValue("ville", utilisateur.getVille());
			mapSrc.addValue("mot_de_passe", utilisateur.getMot_de_passe());
			mapSrc.addValue("credit", utilisateur.getCredit());
			mapSrc.addValue("administrateur", utilisateur.getAdministrateur());
			
			namedParameterJdbcTemplate.update(INSERT, mapSrc, keyHolder);
			utilisateur.setNo_utilisateur(keyHolder.getKey().intValue());
			System.out.println(utilisateur);
		} else {
			namedParameterJdbcTemplate.update(UPDATE, new BeanPropertySqlParameterSource(utilisateur));
		}
	}
	
	@Override
	public void delete(Utilisateur utilisateur) {
		namedParameterJdbcTemplate.update(DELETE, new BeanPropertySqlParameterSource(utilisateur));
	}

	@Override
	public Utilisateur findByName(String id) {
		MapSqlParameterSource mapSrc = new MapSqlParameterSource();
		mapSrc.addValue("id", id);
		Utilisateur utilisateur = namedParameterJdbcTemplate.queryForObject(FIND_BY_NAME, mapSrc, new UtilisateurRowMapper());
		return utilisateur;
	}

	
}
