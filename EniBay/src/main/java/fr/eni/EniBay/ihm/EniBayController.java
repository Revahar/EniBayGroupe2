package fr.eni.EniBay.ihm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
import org.springframework.web.multipart.MultipartFile;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import fr.eni.EniBay.bll.ArticleVenduService;
import fr.eni.EniBay.bll.CategorieService;
import fr.eni.EniBay.bll.EnchereService;
import fr.eni.EniBay.bll.RetraitService;
import fr.eni.EniBay.bll.UtilisateurService;
import fr.eni.EniBay.bo.ArticleVendu;
import fr.eni.EniBay.bo.Enchere;
import fr.eni.EniBay.bo.LoginForm;
import fr.eni.EniBay.bo.Retrait;
import fr.eni.EniBay.bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class EniBayController {
	
	private CategorieService categorieService;
	private RetraitService retraitService;
	private UtilisateurService utilisateurService;
	private ArticleVenduService articleVenduService;
	private EnchereService enchereService;
	
	public EniBayController(CategorieService categorieService, RetraitService retraitService, UtilisateurService utilisateurService, 
			ArticleVenduService articleVenduService, EnchereService enchereService) {
		this.categorieService = categorieService;
		this.retraitService = retraitService;
		this.utilisateurService = utilisateurService;
		this.articleVenduService = articleVenduService;
		this.enchereService = enchereService;
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
    public String afficherAccueil(Model model, Principal principal) {
        List<Retrait> lstRetraits = retraitService.getRetraits();
        model.addAttribute("retraits", lstRetraits);
        List<ArticleVendu> lstArticles = articleVenduService.getArticlesVendus();
        model.addAttribute("articles", lstArticles);
//        List<Enchere> lstEncheres = enchereService.getEncheres();
//        model.addAttribute("encheres", lstEncheres);
        return "Accueil";
    }

	
	
	@PostMapping("/connecter")
	public String connexionProfil(@ModelAttribute("loginForm") LoginForm loginForm, Model model) {
	    // Effectuer la vérification des informations de connexion et renvoyer les erreurs si nécessaire
	    if (utilisateurService.verifierConnexion(loginForm)) {
			model.addAttribute("user", loginForm.getUsername());
			Utilisateur utilisateur = utilisateurService.findByName(loginForm.getUsername());
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
	public String afficherMonProfil(Model model) {
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
	public String versNouvelleVente(Model model, Principal principal) {
	
		System.out.println("arrivee nouvelle vente");
		System.out.println(principal.getName());
		
		var pseudoUtilisateur = principal.getName();
		model.addAttribute("utilisateur", utilisateurService.findByName(pseudoUtilisateur));
		model.addAttribute("article", new ArticleVendu());
		model.addAttribute("retrait", new Retrait());
		return "NouvelleVente";
	}
	
	@PostMapping("/enregistrer-nouvelle-vente")
	public String enregistrerNouvelleVente(@ModelAttribute ArticleVendu article, Retrait retrait, Principal principal, @RequestParam("imageFile") MultipartFile imageFile) {
		System.out.println("enregistrer nouvelle vente");
		var utilisateur = utilisateurService.findByName(principal.getName());
		do
			articleVenduService.ajouterArticleVendu(article, utilisateur);
		while (article.getNo_article() == null);
		
		retraitService.ajouterRetrait(retrait, article, utilisateur);
	    //System.out.println(image);
	    // Vérifier si un fichier image a été sélectionné
	    if (!imageFile.isEmpty()) {
	        try {
	            // Obtenir le nom d'origine du fichier
	            String originalFilename = imageFile.getOriginalFilename();
	            System.out.println(originalFilename);

	            // Extraire l'extension du fichier
	            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
	            System.out.println(fileExtension);

	            // Renommer le fichier selon le modèle "no_article.jpg"
	            String newFilename = article.getNo_article() + fileExtension;
	            System.out.println(newFilename);

	            // Renommer et enregistrer le fichier dans le répertoire souhaité
	            Path filePath = Paths.get("src/main/resources/static/images/", newFilename);
	            Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	            // Enregistrement
	            //articleVendu.setNom_article(newFilename);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return "redirect:/accueil";
	}


	@GetMapping("/encherir")
	public String encherir(Model model, @RequestParam(name = "no_article", required = true) Integer no_article, Principal principal){		
		System.out.println("arrivee encherir");
		//principal.getName()
		if(no_article > 0) {
			ArticleVendu article = articleVenduService.getArticleVenduById(no_article);
			if(article != null) {
				model.addAttribute("article", article);
				return "Encherir";
			} else {
				System.out.println("Article inconnu");
			} 
		} else {
			System.out.println("Numero d'article inconnu");
		}
		
		return "redirect:/accueil";
	}
	
	@PostMapping("/enregistrer_enchere")
	public String enregistrerEnchere(Model model, @ModelAttribute("enchere") Enchere enchere, @ModelAttribute ("article") ArticleVendu article, Principal principal) {
		System.out.println("enregistrer enchere");
		var utilisateur = utilisateurService.findByName(principal.getName());
		enchereService.ajouterEnchere(enchere);
		articleVenduService.ajouterArticleVendu(article, utilisateur);
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
