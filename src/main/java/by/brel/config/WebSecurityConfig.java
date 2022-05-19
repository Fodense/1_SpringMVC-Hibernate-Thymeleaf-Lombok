package by.brel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;

    @Autowired
    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RequestMatcher csrfRequestMatcher = new RequestMatcher() {
            private final AntPathRequestMatcher[] requestMatchers = {
                    new AntPathRequestMatcher("/swagger-ui.html#/**")
            };

            @Override
            public boolean matches(HttpServletRequest request) {
                for (AntPathRequestMatcher requestMatcher : requestMatchers) {
                    if (requestMatcher.matches(request)) {
                        return false;
                    }
                }

                return true;
            }
        };

        http
                .csrf()
                    .ignoringRequestMatchers(csrfRequestMatcher)
                .and()
                    .authorizeRequests()
                        .antMatchers("/").hasAnyRole("USER", "MANAGER")
                        .antMatchers("/customers/new").hasRole("MANAGER")
                        .antMatchers("/tariffs/new").hasRole("MANAGER")
                        .antMatchers("/balances/new").hasRole("MANAGER")
                        .antMatchers("/swagger-ui.html").hasRole("MANAGER")
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .usernameParameter("login")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
