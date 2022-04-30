package by.brel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("vlad").password("{noop}vlad").roles("MANAGER")
                .and()
                .withUser("ivan").password("{noop}ivan").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("MANAGER", "USER")
                .antMatchers("/customers/new").hasRole("MANAGER")
                .antMatchers("/tariffs/new").hasRole("MANAGER")
                .antMatchers("/balances/new").hasRole("MANAGER")
                .and()
                .formLogin()
                .permitAll();
    }
}
