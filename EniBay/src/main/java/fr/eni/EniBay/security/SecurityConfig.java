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
	private final String SELECT_USER = "select email, password ,1 from UTILISATEURS where email=?";
	private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where email=?";
	//private final String SELECT_ROLES = "select u.email, r.role from UTILISATEURS u inner join ROLES r on r.IS_ADMIN = m.admin where m.email = ?";

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
			.usersByUsernameQuery( " SELECT pseudo, mot_de_passe, 1 FROM utilisateurs WHERE ? IN ( pseudo , email ) " )
			.authoritiesByUsernameQuery( " SELECT pseudo, ( CASE WHEN administrateur = 1 THEN 'admin' ELSE 'user' END ) FROM utilisateurs WHERE ? IN ( pseudo , email ) " )
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
					//.anyRequest().authenticated()
					;
		});
		
		//http.formLogin(Customizer.withDefaults());
		

//		http.sessionManagement(session -> session
//		        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//		        .invalidSessionUrl("/connexion")
//		        .maximumSessions(1)
//		        .sessionRegistry(sessionRegistry()));
		
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

	
//	@Bean
//	UserDetailsManager userDetailsManager(DataSource dataSource) {
//		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//
//		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
//
//		return jdbcUserDetailsManager;
//	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//	    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//	    userDetailsManager.setUsersByUsernameQuery(SELECT_USER);
//	    // userDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
//	    return userDetailsManager;
//	}
	
//	@Bean
//	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
//	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//	    authenticationProvider.setUserDetailsService(userDetailsService);
//	    authenticationProvider.setPasswordEncoder(passwordEncoder());
//	    return authenticationProvider;
//	}
	


	
//	@Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withUsername("user1@gmail.com")
//            .password(passwordEncoder().encode("user1Pass"))
//            .roles("USER")
//            .build();
//        UserDetails user2 = User.withUsername("user2")
//            .password(passwordEncoder().encode("user2Pass"))
//            .roles("USER")
//            .build();
//        UserDetails admin = User.withUsername("admin")
//            .password(passwordEncoder().encode("adminPass"))
//            .roles("ADMIN")
//            .build();
//        return new InMemoryUserDetailsManager(user1, user2, admin);
//    }
	


	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
}
