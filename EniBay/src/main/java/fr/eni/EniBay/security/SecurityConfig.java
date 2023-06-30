package fr.eni.EniBay.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity

public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(auth -> {
			auth
					.requestMatchers("/accueil").permitAll()
					.requestMatchers("/connexion").permitAll()
					.requestMatchers("/connecter").permitAll()
					.requestMatchers("/connecter").permitAll()
					.requestMatchers("/creer").permitAll()
					.requestMatchers("/profil").permitAll()
					.requestMatchers("/mon-profil").permitAll()
					.requestMatchers("/enregistrer-nouveau-profil").permitAll()
					.requestMatchers("/").permitAll()
					.requestMatchers("/css/*").permitAll().requestMatchers("/images/*").permitAll()
					.anyRequest().authenticated();
		});
		
		//http.formLogin(Customizer.withDefaults());
		

//		http.sessionManagement(session -> session
//		        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//		        .invalidSessionUrl("/connexion")
//		        .maximumSessions(1)
//		        .sessionRegistry(sessionRegistry()));

		

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
				.logoutSuccessUrl("/").permitAll());
		
		return http.build();
	}
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.withUsername("user1@gmail.com")
            .password(passwordEncoder().encode("user1Pass"))
            .roles("USER")
            .build();
        UserDetails user2 = User.withUsername("user2")
            .password(passwordEncoder().encode("user2Pass"))
            .roles("USER")
            .build();
        UserDetails admin = User.withUsername("admin")
            .password(passwordEncoder().encode("adminPass"))
            .roles("ADMIN")
            .build();       
        
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Bean
	public SessionRegistry sessionRegistry() {
	    return new SessionRegistryImpl();
	}
	
}
