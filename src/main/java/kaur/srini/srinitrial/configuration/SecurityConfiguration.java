package kaur.srini.srinitrial.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import kaur.srini.srinitrial.service.SriniUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	@Autowired
	SriniUserDetailsService sriniUserDetailsService;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((authz) -> {
			authz.antMatchers("/h2-console/**", "/login")
	          .permitAll()
	          .anyRequest()
	          .authenticated();
			})
			.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				.ignoringAntMatchers("/h2-console/**", "/login")
			.and()
			.headers().frameOptions().disable()
			.and()
			.cors()
			.and().apply(MyCustomDsl.customDsl());
		return http.build();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder, DataSource dataSource) throws Exception {
		builder
			.userDetailsService(sriniUserDetailsService);
	}
}
