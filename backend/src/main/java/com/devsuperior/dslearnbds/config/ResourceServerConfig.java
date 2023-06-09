package com.devsuperior.dslearnbds.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//Classe que verifica a requisição e token e diz se pode entregar o recurso



@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Value("${cors.origins}") //Pega os routs que pode acessar o backend
	private String corsOrigins;

	@Autowired //ambiente de execuçaõ da aplicação
	private Environment env;
	
	@Autowired
	private JwtTokenStore tokenStore; 
	
	//endpoints publicos - pode adicionar mais rotas caso necessário
	
		private static final String[] PUBLIC = {"/oauth/token", "/h2-console/**"}; //Precisa ser publico pro usuário logar
		

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// H2 frames
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.authorizeRequests()
		.antMatchers(PUBLIC).permitAll() //Com este perfil está liberado para todo mundo
		.anyRequest().authenticated(); //Quem for acessar qualquer outra rota, precisa estar logado
		
		http.cors().configurationSource(corsConfigurationSource());
		
		//antMatchers define as autorizações 
	}
	
	//Configuração para liberar que o backend seja acessado por outros routs
	@Bean
	CorsConfigurationSource corsConfigurationSource() {

		String[] origins = corsOrigins.split(",");

	    CorsConfiguration corsConfig = new CorsConfiguration();
	    corsConfig.setAllowedOriginPatterns(Arrays.asList(origins));
	    corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
	    corsConfig.setAllowCredentials(true);
	    corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
	 
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", corsConfig);
	    return source;
	}

	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
	    FilterRegistrationBean<CorsFilter> bean
	            = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
	    bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return bean;
	}
}


