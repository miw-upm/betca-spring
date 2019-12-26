package es.upm.miw.restControllers;

import es.upm.miw.restControllers.filter.MyFilter;
import es.upm.miw.restControllers.filter.TimeAccessFilter;
import es.upm.miw.restControllers.jwt.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // auth.inMemoryAuthentication()// static users
        // .withUser("user").password("123456").roles("USER").and()//
        // .withUser("manager").password("123456").roles("MANAGER").and()//
        // .withUser("admin").password("123456").roles("ADMIN", "MANAGER", "USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilter(jwtAuthorizationFilter());
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns(ExceptionResource.EXCEPTIONS + ExceptionResource.MY_FILTER);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<TimeAccessFilter> timeAccessFilter() {
        FilterRegistrationBean<TimeAccessFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new TimeAccessFilter());
        filterRegistrationBean.addUrlPatterns(ExceptionResource.EXCEPTIONS + ExceptionResource.OUT_OF_TIME);
        filterRegistrationBean.setOrder(Ordered.LOWEST_PRECEDENCE - 1);
        return filterRegistrationBean;
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(this.authenticationManager());
    }

}
