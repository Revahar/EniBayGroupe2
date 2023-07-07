package fr.eni.EniBay.bo;

import jakarta.validation.constraints.*;

public class Utilisateur  {
	private Integer no_utilisateur;
	@NotBlank(message = "ne doit pas être null")
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "le pseudo doit être alphanumérique")
	private String pseudo;
	@NotBlank(message = "ne doit pas être null")
	private String nom;
	@NotBlank(message = "ne doit pas être null")
	private String prenom;
	@NotBlank(message = "ne doit pas être null")
	@Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email non valid")
	private String email;

	@Pattern(regexp = "^(?:[0-9]{6,15})$", message = "taille ou character invalide")
	private String telephone;
	@NotBlank(message = "ne doit pas être null")
	private String rue;
	@NotBlank(message = "ne doit pas être null")
	private String code_postal;
	@NotBlank(message = "ne doit pas être null")
	private String ville;
	@NotBlank(message = "ne doit pas être null")
	private String mot_de_passe;
	private Integer credit = 0;
	private Boolean administrateur = false;
	private Boolean actif = true;
    private String messageErreurColor = "red"; //<- sert à afficher le message d'erreur en rouge


	
	public Utilisateur() {
		

	}
	
	public Utilisateur(Integer no_utilisateur, @NotBlank String pseudo, @NotBlank String nom, @NotBlank String prenom,
			@NotBlank String email, String telephone, @NotBlank String rue, @NotBlank String code_postal,
			@NotBlank String ville, @NotBlank String mot_de_passe, Integer credit, Boolean administrateur, Boolean actif) {
		this.no_utilisateur = no_utilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
		this.mot_de_passe = mot_de_passe;
		this.credit = credit;
		this.administrateur = administrateur;
		this.actif = actif;
	}

	public Integer getNo_utilisateur() {
		return no_utilisateur;
	}

	public void setNo_utilisateur(Integer no_utilisateur) {
		this.no_utilisateur = no_utilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCode_postal() {
		return code_postal;
	}

	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public Boolean getAdministrateur() {
		return administrateur;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public void setAdministrateur(Boolean administrateur) {
		this.administrateur = administrateur;
	}

	@Override
	public String toString() {
		return "Utilisateur [no_utilisateur=" + no_utilisateur + ", pseudo=" + pseudo + ", nom=" + nom + ", prenom="
				+ prenom + ", email=" + email + ", telephone=" + telephone + ", rue=" + rue + ", code_postal="
				+ code_postal + ", ville=" + ville + ", mot_de_passe=" + mot_de_passe + ", credit=" + credit
				+ ", administrateur=" + administrateur + "]";
	}

	
	// getter et setter pour afficher les messages d'erreurs en rouge
	public String getMessageErreurColor() {
		return messageErreurColor;
	}

	public void setMessageErreurColor(String messageErreurColor) {
		this.messageErreurColor = messageErreurColor;
	}
	
}
