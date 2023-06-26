package fr.eni.EniBay.bo;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public class Enchere {
	@NotBlank
	private Integer no_utilisateur;
	@NotBlank
	private Integer no_article;
	@NotBlank
	private Date date_enchere;
	@NotBlank
	private Integer montant;
	
	public Enchere(@NotBlank Integer no_utilisateur, @NotBlank Integer no_article, @NotBlank Date date_enchere,
			@NotBlank Integer montant) {
		this.no_utilisateur = no_utilisateur;
		this.no_article = no_article;
		this.date_enchere = date_enchere;
		this.montant = montant;
	}

	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}

	public Date getDate_enchere() {
		return date_enchere;
	}

	public void setDate_enchere(Date date_enchere) {
		this.date_enchere = date_enchere;
	}

	public Integer getMontant() {
		return montant;
	}

	public void setMontant(Integer montant) {
		this.montant = montant;
	}
	
}
