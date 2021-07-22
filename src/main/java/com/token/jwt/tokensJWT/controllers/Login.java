package com.token.jwt.tokensJWT.controllers;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.token.jwt.tokensJWT.models.User;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping(produces = "application/json")
public class Login {
    @PostMapping("/authJWT")
    public String login(@RequestParam("user") String username, @RequestParam("password") String pwd){
        User user = new User();
        
        user.setUsuario(username);
        user.setPassword(pwd);
        user.setRole("admin");
        String tokenGen = getJWToken(user);

        return  tokenGen;

    }
    @GetMapping("/testLogin")
	public String helloOne() {
	return "Login";
	}

    //Con uso de token
    @GetMapping("/authLogin")
	public String auth() {
	return "Its ok";
	}

    private String getJWToken(User user){
        UUID uuid = UUID.randomUUID();
        String secretKey = "developAN";

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
        .builder()
        .setId(uuid.toString())
        //.setHeaderParam("header","example")
        .setSubject(user.getUsuario())
        .claim("usuario", user.getUsuario())
        .claim("rol", user.getRole())
        .claim("authorities", grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + 6000000))
        .signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

        return "Bearer " +token;
    }
}
