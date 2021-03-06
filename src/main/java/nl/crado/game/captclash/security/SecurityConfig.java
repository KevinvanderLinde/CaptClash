package nl.crado.game.captclash.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import nl.crado.game.captclash.model.userservice.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userService)
			.passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/assets/**");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
				.antMatchers("/register")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/search")
				.permitAll()
				.and()


				.authorizeRequests()
				.antMatchers("/v2/**")
				.permitAll()
				.and()

				.authorizeRequests()
				.antMatchers("/swagger-ui.html")
				.permitAll()
				.and()
			.authorizeRequests()
				.antMatchers("/api/**")
				.permitAll()
				.and()


			.authorizeRequests()
				.antMatchers("/**").hasRole("USER")
				.anyRequest().hasRole("USER")
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.failureHandler((request, response, exception) -> {
					response.sendRedirect("/login");
					request.getSession().setAttribute("error", "Unable to login!");
				}) /*TODO add a message to notify the login failed*/
				.successHandler((request, response, authentification) -> response.sendRedirect("/index"))
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/login")
				.and()
			.csrf()
			;
	}
	
	@Bean
	public EvaluationContextExtension securityExtension() {
		return new EvaluationContextExtensionSupport() {
			@Override
			public String getExtensionId() {
				return "security";
			}

			@Override
			public Object getRootObject() {
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				return new SecurityExpressionRoot(auth) {};
			}
		};
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
}
