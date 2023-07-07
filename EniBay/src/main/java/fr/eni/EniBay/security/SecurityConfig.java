package fr.eni.EniBay.security;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfig{
	protected final Log logger = LogFactory.getLog(getClass());
	private final String SELECT_USER = "select email, password ,1 from UTILISATEURS where email=? AND actif=1";
	private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where email=?";

	@Autowired
	private DataSource dataSource ;

	private final PasswordEncoder passwordEncoder  = new BCryptPasswordEncoder() ;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return passwordEncoder;
	}

	@Autowired
	public void configureGlobal( AuthenticationManagerBuilder auth ) throws Exception {
		auth.jdbcAuthentication()
			.dataSource( dataSource )
			.passwordEncoder( passwordEncoder )
			.usersByUsernameQuery( " SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE actif=1 AND ? IN ( pseudo , email ) " )
			.authoritiesByUsernameQuery( " SELECT pseudo, ( CASE WHEN administrateur = 1 THEN 'admin' ELSE 'user' END ) FROM utilisateurs WHERE actif=1 AND ? IN ( pseudo , email ) " )
			;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth
					.requestMatchers(HttpMethod.GET, "/accueil").permitAll()
					.requestMatchers(HttpMethod.GET, "/connexion").permitAll()
					.requestMatchers("/connecter").permitAll()
					.requestMatchers(HttpMethod.GET, "/connecter").permitAll()
					.requestMatchers(HttpMethod.GET, "/creer").permitAll()
					.requestMatchers(HttpMethod.GET, "/recherche").permitAll()
					.requestMatchers(HttpMethod.GET, "/nouvelle-vente").authenticated()
					.requestMatchers("/enregistrer-nouvelle-vente").authenticated()
					.requestMatchers(HttpMethod.GET, "/profil").authenticated()
					.requestMatchers(HttpMethod.GET, "/mon-profil").authenticated()
					.requestMatchers("/enregistrer-nouveau-profil").permitAll()
					.requestMatchers("/error").permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/style.css").permitAll()
					.requestMatchers("/css/*").permitAll()
					.requestMatchers("/images/*").permitAll()
					.anyRequest().authenticated()
					;
		});
		
		http.sessionManagement()
	    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
	    .invalidSessionUrl("/connexion")
	    .maximumSessions(1)
	    .sessionRegistry(sessionRegistry());

		http.formLogin(form -> form
				.loginPage("/connexion")
				.loginProcessingUrl("/connecter")
				.failureUrl("/connexion")
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll()
				);
		
		http.logout(logout -> 
				logout
				.invalidateHttpSession(true)
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID")
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/deconnexion").permitAll());
		
		return http.build();
	}

	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
}
