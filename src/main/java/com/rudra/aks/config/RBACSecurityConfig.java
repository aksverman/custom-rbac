package com.rudra.aks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@Configuration
@ComponentScan({ "com.rudra.aks.security" })
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true )
public class RBACSecurityConfig extends WebSecurityConfigurerAdapter {


	@Autowired
	@Qualifier("userDetailsServiceImpl")
	UserDetailsService userDetailsService;
	
	@Autowired
	DataSource dataSource;
	
	/*@Bean
	public DaoAuthenticationProvider	daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		return daoAuthenticationProvider;
	}*/
	
	@Override
 	public void configure(WebSecurity web) throws Exception {
 		web.ignoring()
 		// Spring Security should completely ignore URLs starting with /public/
 				.antMatchers("/public/**", "/resources/**");
 	}


	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http.authorizeRequests()
 			.antMatchers("/rest-user", "/rest-accessdenied/**", "/rest-admin/resetpassword/**", "/login/**").permitAll()
			.mvcMatchers("/rest-user/addUser/**", "/rest-user/registerForm/**", "/role/**").permitAll()
			.antMatchers("/rest-user/**").authenticated()
			.and()
			.httpBasic()
			.authenticationEntryPoint(new CustomEntryPoint())
			.and().csrf().disable();
			
 			
			/*.antMatchers("/", "/accessdenied/**", "/admin/resetpassword/**", "/login/**").permitAll()
 			.mvcMatchers("/user/addUser/**", "/user/registerForm/**", "/role/**").permitAll()
 			.antMatchers("/user/**").authenticated()//.anyRequest()//.fullyAuthenticated()
 			.antMatchers("/admin/**").fullyAuthenticated()
 			.antMatchers("/admin/test/**").fullyAuthenticated()
 			.and()
 			.exceptionHandling()
 			.accessDeniedHandler(new CustomAccessDeniedHandler())
 			.and()
 			.formLogin()
 			//.loginPage("/loginpage") // enable custome form based log in
 			.defaultSuccessUrl("/user/successlogin")
 			//.loginProcessingUrl("/user/login")
 			.permitAll()
 			.and()
 			.logout()//.logoutUrl("/logout")
 			.deleteCookies("remember-me", "JSESSIONID")
 			.logoutSuccessUrl("/")
 			.permitAll()
 			.and()
 			.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(12000)
 			//.rememberMe().tokenRepository(tokenRepository).tokenValiditySeconds(12000)
 			.and().sessionManagement()
 			//.invalidSessionUrl("/accessdenied/sessionExpired")
 			.maximumSessions(1)
 			.expiredUrl("/accessdenied/tooManySessions");*/
 	}

	@Override
 	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
 		auth.userDetailsService(userDetailsService);
 		//auth.authenticationProvider(daoAuthenticationProvider());
 		// enable in memory based authentication with a user named "user" and "admin"
 		/*auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
 				.and().withUser("admin").password("admin").roles("USER", "ADMIN");*/
 	}
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		//db.setCreateTableOnStartup(true);
		db.setDataSource(dataSource);
		return db;
	}

}
