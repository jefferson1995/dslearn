package com.devsuperior.dslearnbds.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.devsuperior.dslearnbds.entities.User;
import com.devsuperior.dslearnbds.repositories.UserRepository;

//Classe para adicionar mais objetos no token
@Component
public class JwtTokenEnhancer implements TokenEnhancer{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		
		//Objetos que serão adicionados   //Busca o usuário por e-mail
		User user = userRepository.findByEmail(authentication.getName()); 
		
		Map<String, Object> map = new HashMap<>(); //Para adicionar no corpo do Token
		map.put("userId", user.getId());
		map.put("name: ", user.getName());
		
		//Para adicionar no token		//troca o tipo da classe
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken; 
		token.setAdditionalInformation(map); 
		
		return token;
	}
	
	//Depois instanciar na classe AuthorizationServerConfig

	
}
