package fr.eni.EniBay.security;


import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;





@Configuration
@EnableWebSecurity

public class SecurityConfig {
	protected final Log logger = LogFactory.getLog(getClass());
	private final String SELECT_USER = "select email, password ,1 from UTILISATEURS where email=?";
	private final String SELECT_ROLES = "select email, 'admin' from UTILISATEURS where email=?";
	//private final String SELECT_ROLES = "select u.email, r.role from UTILISATEURS u inner join ROLES r on r.IS_ADMIN = m.admin where m.email = ?";

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth
					.requestMatchers(HttpMethod.GET, "/accueil").permitAll()
					.requestMatchers(HttpMethod.GET, "/connexion").permitAll()
					.requestMatchers("/connecter").permitAll()
					.requestMatchers(HttpMethod.GET, "/connecter").permitAll()
					.requestMatchers(HttpMethod.GET, "/creer").permitAll()
					.requestMatchers(HttpMethod.GET, "/profil").permitAll()
					.requestMatchers(HttpMethod.GET, "/mon-profil").permitAll()
					.requestMatchers(HttpMethod.GET, "/NouvelleVente").authenticated()
					.requestMatchers("/enregistrer-nouveau-profil").permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
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
	
//	http.logout()
//    .invalidateHttpSession(true)
//    .clearAuthentication(true)
//    .deleteCookies("JSESSIONID")
//    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//    .logoutSuccessUrl("/deconnexion").permitAll();

	
	@Bean
	UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

		jdbcUserDetailsManager.setUsersByUsernameQuery(SELECT_USER);
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);

		return jdbcUserDetailsManager;
	}
	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource dataSource) {
//	    JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//	    userDetailsManager.setUsersByUsernameQuery(SELECT_USER);
//	    // userDetailsManager.setAuthoritiesByUsernameQuery(SELECT_ROLES);
//	    return userDetailsManager;
//	}
	
	@Bean
	public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
	    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
	    authenticationProvider.setUserDetailsService(userDetailsService);
	    authenticationProvider.setPasswordEncoder(passwordEncoder());
	    return authenticationProvider;
	}
	


	
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
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}

}
