package fr.eni.EniBay.dal;

import java.util.List;

import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.Utilisateur;

public interface EnchereDAO {

	List<Enchere> findAll();

	void save(Enchere enchere, ArticleVendu article, Utilisateur utilisateur);

	Enchere findById(Integer no_article, Integer no_utilisateur);

	Enchere read(Integer no_article, Integer no_utilisateur);

	void delete(Integer no_article, Integer no_utilisateur);

}
