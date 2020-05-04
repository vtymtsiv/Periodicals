package ua.lviv.lgs.periodicals.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private UserDetailsService userDetailsService;

  @Autowired
  public WebSecurityConfig(@Qualifier("customUserDetailsService") UserDetailsService userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/periodicals/create","/periodicals/save").hasRole( "ADMIN")
      .antMatchers("/periodicals/all").hasAnyRole( "ADMIN", "USER")
      .anyRequest().permitAll()
      .and()
      .formLogin().loginPage("/login").loginProcessingUrl("/login")
      .usernameParameter("username").passwordParameter("password")
      .defaultSuccessUrl("/home", true).and()
      .logout().logoutSuccessUrl("/home")
      .and()
      .exceptionHandling().accessDeniedPage("/403")
      .and()
      .csrf();
  }

  @Bean
  public PasswordEncoder passwordencoder() {
    return new BCryptPasswordEncoder();
  }
}
