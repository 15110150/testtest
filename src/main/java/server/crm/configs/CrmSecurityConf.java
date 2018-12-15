package server.crm.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import server.crm.filters.JWTAuthenticationFilter;
import server.crm.filters.JWTLoginFilter;

@Configuration
@EnableWebSecurity
public class CrmSecurityConf extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    /**
     * @author: trungntm
     * @create: 20/10/2018
     */
    @Qualifier("customUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService customUserDetailsService;
    private final long MAX_AGE_SECS = 864_000;
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder (){return new BCryptPasswordEncoder();}
    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: block configure AuthenticationManagerBuilder by Tung
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin")
//                .password("{noop}8GpD3LaST58hvzMJ")
//                .roles("ADMIN");
//    }

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: add configureGlobal
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{

        auth.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: block configure HttpSecurity by Tung
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//    }

    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: edit configure HttpSecurity
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                // No need authentication.
                .antMatchers("/api/v1/register/**",
                        "/v2/api-docs",
                        "/h2-console/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources",
                        "/files/**",
                        "/login")
                        .permitAll() //
                .antMatchers("/api/v1.0/students/**",
                        "/api/v1.0/roles/**")
                        .hasRole("ADMIN")
                .antMatchers("/api/v1.0/potential-students/**")
                        .hasAnyRole("USER","ADMIN")
                .antMatchers("/api/v1/admin/**","/admin")
                        .hasAnyAuthority("READ_PRIVILEGE")
                .antMatchers("/api/v1.0/users/**","/user")
                        .hasAnyAuthority("READ_PRIVILEGE")
                //.antMatchers(HttpMethod.POST, "/login").permitAll() //
//                .antMatchers(HttpMethod.GET, "/login").permitAll() // For Test on Browser
                // Need authentication.
                .anyRequest().authenticated();
        //
        // Add Filter 1 - JWTLoginFilter
        //
        http
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                //
                // Add Filter 2 - JWTAuthenticationFilter
                //
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
    /**
     * @author: trungntm
     * @create: 20/10/2018
     * @content: add addCorsMappings
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .exposedHeaders("Authorization")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
