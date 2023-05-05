package com.example.j_project.config;


import org.apache.tomcat.util.descriptor.web.WebXml;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http
/*                .oauth2Login().
                loginPage("/login").permitAll().and()
                .formLogin()
                .permitAll().and()*/
                .logout(logout -> logout
                .logoutUrl("/my/logout")
                .logoutSuccessUrl("/my/index")

        )
                .authorizeHttpRequests()
                .requestMatchers("/","/register").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login();

        return (SecurityFilterChain)http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(7);}
}
/*https://accounts.google.com/o/oauth2/v2/auth/
oauthchooseaccount?response_type=code&client_id=
968740976971-8pr6u7t6hduhrnssk7r6run4al39v3ib.apps.googleusercontent.com&
scope=openid%20profile%20email&state=kWZ2lHHY0I30wNNFHF0eKzrAqVc67ExmmfLeleLX3SA%3D&redirect_uri=
http%3A%2F%2Flocalhost%3A8988%2Flogin%2Foauth2%2Fcode%2Fgoogle&nonce=wN7zU54NIHyuqh7NR76GPMah4mdA5SF7F2ZOJWFe36s&service=lso&o2v=2&flowName=GeneralOAuthFlow*/