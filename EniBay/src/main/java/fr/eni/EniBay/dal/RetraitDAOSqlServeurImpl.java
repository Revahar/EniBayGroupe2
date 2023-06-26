package fr.eni.EniBay.dal;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import fr.eni.EniBay.bo.Retrait;

public class RetraitDAOSqlServeurImpl implements RetraitDAO{
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private RetraitDAO retraitDAO;

	@Override
	public List<Retrait> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Retrait findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Retrait retrait) {
		// TODO Auto-generated method stub
		
	}

	public RetraitDAO getRetraitDAO() {
		return retraitDAO;
	}

	public void setRetraitDAO(RetraitDAO retraitDAO) {
		this.retraitDAO = retraitDAO;
	}
}
