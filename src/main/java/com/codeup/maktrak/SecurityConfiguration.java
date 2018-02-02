package com.codeup.maktrak;


import com.codeup.maktrak.services.UserDetailsLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader userDetails;

    public SecurityConfiguration(UserDetailsLoader userDetails) {
        this.userDetails = userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }



    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetails)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            /* Login configuration */
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard") // user's home page, it can be any URL
                .permitAll()

            .and()
                .logout()
                .logoutSuccessUrl("/login?logout")

            .and()
                .authorizeRequests()
                .antMatchers("/", "/sign-up", "/login")
                .permitAll()

            .and()
                .authorizeRequests()
                .antMatchers(
                       "/dashboard"
                )
            .authenticated();
    }

}
