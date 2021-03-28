package am.bigshopdemo.demo.config;

import am.bigshopdemo.demo.security.JwtAuthenticationEntryPoint;
import am.bigshopdemo.demo.security.JwtAuthenticationTokenFilter;
import am.bigshopdemo.demo.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/address/add").hasAnyAuthority("USER")
                .antMatchers("/address/{userId}").hasAnyAuthority("ADMIN")
                .antMatchers("/delete/user/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/add/cart").hasAnyAuthority("ADMIN")
                .antMatchers("/add/order").hasAnyAuthority("ADMIN")
                .antMatchers("/add/product").hasAnyAuthority("ADMIN")
                .antMatchers("/product/update/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/product/delete/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/cart/{id}").hasAnyAuthority("ADMIN")
                .antMatchers("/addToCart/{userId}/{productId}").hasAnyAuthority("USER")
                .antMatchers("/ordersByUserId/{userId}").hasAnyAuthority("ADMIN")
                .antMatchers("/buy/{userId}/{productId}").hasAnyAuthority("USER")
                .antMatchers("/register").hasAnyAuthority("USER")
                .antMatchers("/activate/{email},{token}").hasAnyAuthority("USER")
                .antMatchers("/allUsers").hasAnyAuthority("ADMIN")
                .antMatchers("/user/{id}").hasAnyAuthority("ADMIN")
                .anyRequest().permitAll();
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        http.headers().cacheControl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
        return new JwtAuthenticationTokenFilter();
    }

}
