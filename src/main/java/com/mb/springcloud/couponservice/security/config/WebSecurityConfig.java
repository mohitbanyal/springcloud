package com.mb.springcloud.couponservice.security.config;

import com.mb.springcloud.couponservice.security.UserSecurityImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserDetailsService userDetailsService;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Z]*$}", "/")
                        .hasAnyRole("USER","ADMIN")
                        .requestMatchers(HttpMethod.POST,"/couponapi/coupons","/saveCoupon","/createResponse")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"showCreateCoupon")
                        .hasRole("ADMIN")
        );
       http.csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }


}
