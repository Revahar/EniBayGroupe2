package fr.eni.EniBay.bo;

import jakarta.validation.constraints.NotBlank;

public class Retrait {
	@NotBlank
	private Integer no_article;
	@NotBlank
	private String rue;
	@NotBlank
	private String code_postal;
	@NotBlank
	private String ville;
	
	public Retrait(@NotBlank Integer no_article, @NotBlank String rue, @NotBlank String code_postal,
			@NotBlank String ville) {
		this.no_article = no_article;
		this.rue = rue;
		this.code_postal = code_postal;
		this.ville = ville;
	}

	public Integer getNo_article() {
		return no_article;
	}

	public void setNo_article(Integer no_article) {
		this.no_article = no_article;
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

}
