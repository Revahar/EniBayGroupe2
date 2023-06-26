package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import fr.eni.EniBay.bo.Enchere;


@Repository
@Primary
public class EnchereDAOSqlServerImpl implements EnchereDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private EnchereDAO enchereDAO;

	@Override
	public List<Enchere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Enchere enchere) {
		// TODO Auto-generated method stub
		
	}
	
	public EnchereDAO getEnchereDAO() {
		return enchereDAO;
	}

	public void setEnchereDAO(EnchereDAO enchereDAO) {
		this.enchereDAO = enchereDAO;
	}

}
