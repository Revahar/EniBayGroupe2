package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Enchere;

public interface EnchereDAO {

	List<Enchere> findAll();

	void save(Enchere enchere);

}
