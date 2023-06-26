package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Retrait;

public interface RetraitDAO {
	List<Retrait> findAll();
	
	Retrait findById(Integer id);
	
	void save(Retrait retrait);
}
