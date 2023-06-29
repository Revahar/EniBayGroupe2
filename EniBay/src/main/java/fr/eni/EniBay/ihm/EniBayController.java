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
		System.out.println("arrivée connexion");
		return "Connexion";
	}
	
	@PostMapping("/connecter")
	public String connexionProfil(@ModelAttribute("LoginForm")LoginForm loginForm) {
		System.out.println("connecter");
		return "redirect:/accueil";
	}
	
	@GetMapping("/creer")
	public String versCreation(Model model) {
		System.out.println("arrivée creer");
		model.addAttribute("utilisateur", new Utilisateur());
		return "Creation";
	}
	
	@PostMapping("/enregistrer-nouveau-profil")
	public String enregistrerNouveauProfil(@ModelAttribute Utilisateur utilisateur) {
		System.out.println("enregistrer nouveau profil");
		utilisateurService.ajouterUtilisateur(utilisateur);
		
		//System.out.println(utilisateur);
		return "redirect:/accueil";
	}
	
	@GetMapping("/encheres")
	public String encheresConnecte() {
		System.out.println("arrivée encheres connecte");
		return "EncheresConnecte";
	}
	
	@GetMapping("/mes-ventes")
	public String mesVentes() {
		System.out.println("arrivee mes ventes");
		return "MesVentes";
	}
	
	@GetMapping("/profil")
	public String afficherProfil(@RequestParam Utilisateur utilisateur, Model model) {
		System.out.println("afficher profil");
		model.addAttribute("utilisateur", utilisateur);
		return "Profil";
	}
	
	@GetMapping("/mon-profil")
	public String afficherMonProfil() {
		System.out.println("afficher mon profil");
		return "Profil";
	}
	
	@GetMapping("/modifier-profil")
	public String versModifProfil(@RequestParam Integer no_utilisateur, Model model) {
		System.out.println("arrivee modif profil");
		return "ModifProfil";
	}
	
	@PostMapping("/enregistrer-modifs")
	public String enregistrerModifsProfil() {
		System.out.println("enregistrer modifs profil");
		return "redirect:/profil";
	}
	
	@GetMapping("/nouvelle-vente")
	public String versNouvelleVente(Model model/*, @RequestParam Utilisateur utilisateur*/) {
		//model.addAttribute("utilisateur", utilisateur);
		System.out.println("arrivee nouvelle vente");
		model.addAttribute("article", new ArticleVendu());
		return "NouvelleVente";
	}
	
	@PostMapping("/enregistrer-nouvelle-vente")
	public String enregistrerNouvelleVente(@ModelAttribute ArticleVendu article) {
		System.out.println("enregistrer nouvelle vente");
		return "redirect:/accueil";
	}
	
	@GetMapping("/encherir")
	public String encherir() {
		System.out.println("arrivee encherir");
		return "Encherir";
	}
	
	@PostMapping("/enregistrer_enchere")
	public String enregistrerEnchere() {
		System.out.println("enregistrer enchere");
		return "redirect/accueil";
	}
	
	@GetMapping("/acquisition")
	public String versAcquisition() {
		System.out.println("arrivee acquisition");
		return "Acquisition";
	}
	
	@GetMapping("/details-fin-enchere")
	public String detailsFinEnchere() {
		System.out.println("details fin enchere");
		return "DetailsFinEnchere";
	}
	
	@GetMapping("/supprimer-profil")
	public String supprimerProfil() {
		System.out.println("supprimer profil");
		return "redirect:/accueil";
	}
	
	@GetMapping("/retrait-article")
	public String retraitArticle() {
		System.out.println("retrait article");
		return "redirect:/accueil";
	}
}
