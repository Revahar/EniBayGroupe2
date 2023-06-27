package fr.eni.EniBay.bo;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;

public class ArticleVendu {
	@NotBlank
	private Integer no_article;
	@NotBlank
	private String nom_article;
	@NotBlank
	private String description;
	@NotBlank
	private Date date_debut_encheres;
	@NotBlank
	private Date date_fin_encheres;
	private Integer prix_initial;
	private Integer prix_vente;
	@NotBlank
	private Integer no_utilisateur;
	@NotBlank
	private Integer no_categorie;

	public ArticleVendu() {
		// TODO Auto-generated constructor stub
	}
	
	public ArticleVendu(@NotBlank Integer no_article, @NotBlank String nom_article, @NotBlank String description,
			@NotBlank Date date_debut_encheres, @NotBlank Date date_fin_encheres, Integer prix_initial,
			Integer prix_vente, @NotBlank Integer no_utilisateur, @NotBlank Integer no_categorie) {
		super();
		this.no_article = no_article;
		this.nom_article = nom_article;
		this.description = description;
		this.date_debut_encheres = date_debut_encheres;
		this.date_fin_encheres = date_fin_encheres;
		this.prix_initial = prix_initial;
		this.prix_vente = prix_vente;
		this.no_utilisateur = no_utilisateur;
		this.no_categorie = no_categorie;
	}
	
	public Integer getNo_article() {
		return no_article;
	}
	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
	}
	public String getNom_article() {
		return nom_article;
	}
	public void setNom_article(String nom_article) {
		this.nom_article = nom_article;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate_debut_encheres() {
		return date_debut_encheres;
	}
	public void setDate_debut_encheres(Date date_debut_encheres) {
		this.date_debut_encheres = date_debut_encheres;
	}
	public Date getDate_fin_encheres() {
		return date_fin_encheres;
	}
	public void setDate_fin_encheres(Date date_fin_encheres) {
		this.date_fin_encheres = date_fin_encheres;
	}
	public Integer getPrix_initial() {
		return prix_initial;
	}
	public void setPrix_initial(Integer prix_initial) {
		this.prix_initial = prix_initial;
	}
	public Integer getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(Integer prix_vente) {
		this.prix_vente = prix_vente;
	}
	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}
	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}
	public Integer getNo_categorie() {
		return no_categorie;
	}
	public void setNo_categorie(Integer no_categorie) {
		this.no_categorie = no_categorie;
	}

	@Override
	public String toString() {
		return "ArticleVendu [no_article=" + no_article + ", nom_article=" + nom_article + ", description=" + description
				+ ", date_debut_encheres=" + date_debut_encheres + ", date_fin_encheres=" + date_fin_encheres
				+ ", prix_initial=" + prix_initial + ", prix_vente=" + prix_vente + ", no_utilisateur=" + no_utilisateur
				+ ", no_categorie=" + no_categorie + "]";
	}
	
}
