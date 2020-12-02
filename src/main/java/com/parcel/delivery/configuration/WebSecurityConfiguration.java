package com.parcel.delivery.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin")
                .password(passwordEncoder().encode("admin")).roles("ADMIN")
        .and().withUser("agent")
                .password(passwordEncoder().encode("agent")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http)  {
        try {
            http.authorizeRequests()
                    .antMatchers("/login").permitAll()
                    .antMatchers(HttpMethod.GET,"/metrics").permitAll()
                    .antMatchers(HttpMethod.GET,"/health").permitAll()
                    .antMatchers(HttpMethod.POST,"/delivery/parcel/admin").hasRole("ADMIN")
                    .antMatchers(HttpMethod.GET,"/delivery/parcel").hasAnyRole("ADMIN","USER")
                    .antMatchers(HttpMethod.PUT,"/delivery/parcel/update").hasAnyRole("USER","ADMIN")
                    .anyRequest().fullyAuthenticated()
                    .and()
                    .httpBasic()
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
