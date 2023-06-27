package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.Enchere;

public interface EnchereDAO {

	List<Enchere> findAll();

	void save(Enchere enchere);

	Enchere findById(Integer no_article, Integer no_utilisateur);

	Enchere read(Integer no_article, Integer no_utilisateur);

	void delete(Integer no_article, Integer no_utilisateur);

}
