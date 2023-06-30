//package fr.eni.EniBay.security;
//
//import java.security.Principal;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.SessionAttributes;
//
//import fr.eni.EniBay.bo.Utilisateur;
//import fr.eni.EniBay.bll.contexte.ContexteService;
//
//@Controller
////Injection de la liste des attributs en session
//@SessionAttributes({ "membreEnSession" })
//class LoginController {
//	private ContexteService service;
//
//	public LoginController(ContexteService service) {
//		this.service = service;
//	}
//
//	@GetMapping("/connexion")
//	String login() {
//		return "Connexion";
//	}
//
//	@GetMapping("/chargerConnexion")
//	String chargerMembreEnSession(@ModelAttribute("membreEnSession") Utilisateur membreEnSession, Principal principal) {
//		String email = principal.getName();
//		Utilisateur aCharger = service.charger(email);
//		if (aCharger != null) {
//			membreEnSession.setNo_utilisateur(aCharger.getNo_utilisateur());
//			membreEnSession.setNom(aCharger.getNom());
//			membreEnSession.setPrenom(aCharger.getPrenom());
//			membreEnSession.setPseudo(aCharger.getPseudo());
//			membreEnSession.setAdministrateur(aCharger.getAdministrateur());
//
//		} else {
//			membreEnSession.setNo_utilisateur(0);
//			membreEnSession.setNom(null);
//			membreEnSession.setPrenom(null);
//			membreEnSession.setPseudo(null);
//			membreEnSession.setAdministrateur(false);
//
//		}
//		System.out.println(membreEnSession);
//
//		return "redirect:/accueil";
//	}
//
//	// Cette méthode met par défaut un nouveau membre en session
//	@ModelAttribute("membreEnSession")
//	public Utilisateur membreEnSession() {
//		System.out.println("Add Attribut Session");
//		return new Utilisateur();
//	}
//}
