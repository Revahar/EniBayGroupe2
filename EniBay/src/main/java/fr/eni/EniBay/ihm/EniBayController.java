package fr.eni.EniBay.ihm;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.security.Principal;


import org.springframework.web.bind.annotation.SessionAttributes;


import fr.eni.EniBay.bll.*;
import fr.eni.EniBay.bo.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class EniBayController {
	
	private CategorieService categorieService;
	private RetraitService retraitService;
	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	
	public EniBayController(CategorieService categorieService, RetraitService retraitService, UtilisateurService utilisateurService, ArticleVenduService articleVenduService) {
		this.categorieService = categorieService;
		this.retraitService = retraitService;
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
	}
	
	//Méthode de vérifiaction de connexion (sert à rendre dynamique les liens) 
    private boolean isConnected() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.isAuthenticated();
    }
    
    //Méthode pour se déconnecter
    @GetMapping("/deconnexion")
    public String deconnexion(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "accueil";
    }

	
    @GetMapping({"/", "/accueil"})
    public String afficherAccueil(@SessionAttribute("user") String username, Model model, Principal principal) {
        boolean isConnected = isConnected();
        if (isConnected) {
            System.out.println(isConnected);
            //System.out.println(principal.getName());
        }
        List<Retrait> lstCategorie = retraitService.getRetraits();

        model.addAttribute("retrait", lstCategorie);
        model.addAttribute("isConnected", isConnected);
        System.out.println(username);
        return "Accueil";
    }
	
	
	@PostMapping("/connecter")
	public String connexionProfil(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
	    // Effectuer la vérification des informations de connexion et renvoyer les erreurs si nécessaire
	    if (utilisateurService.verifierConnexion(loginForm)) {
	        System.out.println("connexion ok");
	        System.out.println(loginForm.getUsername());
			Utilisateur test = utilisateurService.findByName(loginForm.getUsername());
			model.addAttribute("user", loginForm.getUsername());
	        return "redirect:/accueil";
	    } else {
	        model.addAttribute("error", "Identifiants incorrects"); // Ajouter un message d'erreur au modèle
	        System.out.println("erreur");
	        return "Connexion";
	    }
	}


	@GetMapping("/connexion")
	public String versConnexion(Model model) {
	    model.addAttribute("loginForm", new LoginForm()); // Initialiser le modèle LoginForm
	    
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    boolean isConnected = authentication.isAuthenticated();
	    model.addAttribute("isConnected", isConnected); // Ajout de la variable isConnected
	    
	    return "Connexion";
	}

//	@GetMapping("/connexion")
//	public String versConnexion(@ModelAttribute("loginForm")LoginForm loginForm) {
//		System.out.println("arrivée connexion");
//		return "Connexion";
//	}
	
//	@PostMapping("/connecter")
//	public String connexionProfil(@ModelAttribute("LoginForm")LoginForm loginForm) {
//		System.out.println("connecter");
//		return "redirect:/accueil";
//	}
	
	@GetMapping("/creer")
	public String versCreation(Model model) {
		System.out.println("arrivée creer");
		model.addAttribute("utilisateur", new Utilisateur());
		return "Creation";
	}
	
	@PostMapping("/enregistrer-nouveau-profil")
	public String enregistrerNouveauProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
										   BindingResult bindingResult) {
		System.out.println("enregistrer nouveau profil");
		if (bindingResult.hasErrors()) {
			return "Creation";
		}
		utilisateurService.ajouterUtilisateur(utilisateur);
		
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
	public String afficherProfil(/*@RequestParam Utilisateur utilisateur, Model model*/) {
		System.out.println("afficher profil");
		//model.addAttribute("utilisateur", utilisateur);
		return "Profil";
	}
	
	@GetMapping("/mon-profil")
	public String afficherMonProfil(Model model, @SessionAttribute("user") String username) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication.getName());
		
		System.out.println("afficher mon profil");
		model.addAttribute("utilisateur", utilisateurService.findById(1));
		return "Profil";
	}
	
	@GetMapping("/modifier-profil")
	public String versModifProfil(@RequestParam Integer no_utilisateur, Model model) {
		model.addAttribute("utilisateur", utilisateurService.findById(no_utilisateur));
		return "ModifProfil";
	}
	
	@PostMapping("/enregistrer-modifs")
	public String enregistrerModifsProfil(@Valid @ModelAttribute("utilisateur") Utilisateur utilisateur,
										  BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "ModifProfil";
		}
		utilisateurService.save(utilisateur);
		System.out.println("enregistrer modifs profil");
		return "redirect:/mon-profil";
	}

	@PostMapping("/supprimer-profil")
	public String supprimerProfil(Utilisateur utilisateur) {
		utilisateurService.delete(utilisateur);
		System.out.println("supprimer profil");
		return "redirect:/accueil";
	}
	
	@GetMapping("/nouvelle-vente")
	public String versNouvelleVente(Model model) {
		System.out.println("arrivee nouvelle vente");
		model.addAttribute("article", new ArticleVendu());
		return "NouvelleVente";
	}
	
	@PostMapping("/enregistrer-nouvelle-vente")
	public String enregistrerNouvelleVente(@ModelAttribute ArticleVendu article) {
		System.out.println("enregistrer nouvelle vente");
		articleVenduService.ajouterArticleVendu(article);
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
	
	@GetMapping("/retrait-article")
	public String retraitArticle() {
		System.out.println("retrait article");
		return "redirect:/accueil";
	}
}
