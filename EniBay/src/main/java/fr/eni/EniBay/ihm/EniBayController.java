package fr.eni.EniBay.ihm;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.eni.EniBay.bll.*;
import fr.eni.EniBay.bo.*;

@Controller
public class EniBayController {
	
	private CategorieService categorieService;
	private RetraitService retraitService;
	private UtilisateurService utilisateurService;
	
	public EniBayController(CategorieService categorieService, RetraitService retraitService, UtilisateurService utilisateurService) {
		this.categorieService = categorieService;
		this.retraitService = retraitService;
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil(Model model) {
		List<Retrait> lstCategorie = retraitService.getRetraits();
		
		model.addAttribute("retrait", lstCategorie);
		return "Accueil";
	}
	
	@GetMapping("/connexion")
	public String versConnexion(@ModelAttribute("loginForm")LoginForm loginForm) {
		return "Connexion";
	}
	
	@PostMapping("/connecter")
	public String connexionProfil(@ModelAttribute("LoginForm")LoginForm loginForm) {
		
		return "redirect:/accueil";
	}
	
	@GetMapping("/creer")
	public String versCreation() {
		return "Creation";
	}
	
	@PostMapping("/enregistrer-nouveau-profil")
	public String enregistrerNouveauProfil(@ModelAttribute Utilisateur utilisateur) {
		return "redirect:/accueil";
	}
	
	@GetMapping("/encheres")
	public String encheresConnecte() {
		return "EncheresConnecte";
	}
	
	@GetMapping("/mes-ventes")
	public String mesVentes() {
		return "MesVentes";
	}
	
	@GetMapping("/profil")
	public String afficherProfil(@RequestParam Utilisateur utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateur);
		return "Profil";
	}
	
	@GetMapping("/mon-profil")
	public String afficherMonProfil() {
		return "MonProfil";
	}
	
	@GetMapping("/modifier-profil")
	public String versModifProfil(@RequestParam Integer no_utilisateur, Model model) {
		return "ModifProfil";
	}
	
	@PostMapping("/enregistrer-modifs")
	public String enregistrerModifsProfil() {
		return "redirect:/profil";
	}
	
	@GetMapping("/nouvelle-vente")
	public String versNouvelleVente(Model model/*, @RequestParam Utilisateur utilisateur*/) {
		//model.addAttribute("utilisateur", utilisateur);
		model.addAttribute("article", new ArticleVendu());
		return "NouvelleVente";
	}
	
	@PostMapping("/enregistrer-nouvelle-vente")
	public String enregistrerNouvelleVente(@ModelAttribute ArticleVendu article) {
		return "redirect:/accueil";
	}
	
	@GetMapping("/encherir")
	public String encherir() {
		return "Encherir";
	}
	
	@PostMapping("/enregistrer_enchere")
	public String enregistrerEnchere() {
		return "redirect/accueil";
	}
	
	@GetMapping("/acquisition")
	public String versAcquisition() {
		return "Acquisition";
	}
	
	@GetMapping("/details-fin-enchere")
	public String detailsFinEnchere() {
		return "DetailsFinEnchere";
	}
	
	@GetMapping("/supprimer-profil")
	public String supprimerProfil() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/retrait-article")
	public String retraitArticle() {
		return "redirect:/accueil";
	}
}
