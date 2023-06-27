package fr.eni.EniBay.bo;

import jakarta.validation.constraints.NotBlank;

public class Categorie {
	@NotBlank
	private Integer no_categorie;
	@NotBlank
	private String libelle;
	
	public Categorie() {}

	public Categorie(@NotBlank Integer no_categorie, @NotBlank String libelle) {
		this.no_categorie = no_categorie;
		this.libelle = libelle;
	}

	public Categorie(Integer no_categorie) {
		this.no_categorie = no_categorie;
	}

	public Integer getNo_categorie() {
		return no_categorie;
	}

	public void setNo_categorie(Integer no_categorie) {
		this.no_categorie = no_categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
}
