package fr.eni.EniBay.ihm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EniBayController {
	
	
	@GetMapping({"/", "/accueil"})
	public String afficherAccueil() {
		return "Accueil";
	}
	
	@GetMapping("/connexion")
	public String versConnexion() {
		return "Connexion";
	}
	
	@PostMapping("/connecter")
	public String connexionProfil() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/creer")
	public String versCreation() {
		return "Creation";
	}
	
	@PostMapping("/enregistrer-nouveau-profil")
	public String enregistrerNouveauProfil() {
		return "redirect:/accueil";
	}
	
	@GetMapping("/encheres")
	public String encheresConnecte() {
		return "EncheresConnecte";
	}
	
	@GetMapping("mes-ventes")
	public String mesVentes() {
		return "MesVentes";
	}
	
	@GetMapping("/profil")
	public String afficherProfil() {
		return "Profil";
	}
	
	@GetMapping("/mon-profil")
	public String afficherMonProfil() {
		return "MonProfil";
	}
	
	@GetMapping("/modifier-profil")
	public String versModifProfil() {
		return "ModifProfil";
	}
	
	@PostMapping("/enregistrer-modifs")
	public String enregistrerModifsProfil() {
		return "redirect:/profil";
	}
	
	@GetMapping("/nouvelle-vente")
	public String versNouvelleVente() {
		return "NouvelleVente";
	}
	
	@PostMapping("/enregistrer-nouvelle-vente")
	public String enregistrerNouvelleVente() {
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
}
