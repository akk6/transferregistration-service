package au.gov.nsw.revenue.transferregistrationservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration {

    @Profile("!qa")
    @EnableWebSecurity
    public class SecurityDisabledConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll();
            httpSecurity.headers().contentSecurityPolicy("default-src 'self' style-src 'self' 'unsafe-inline'");
        }
    }

    @Profile("qa")
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @EnableWebSecurity
    public class SecurityEnabledConfig extends WebSecurityConfigurerAdapter {

        @Value("${basic.auth.username}")
        private String username;

        @Value("${basic.auth.pwd}")
        private String cred;
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/transfer-registration/**").authenticated()
                    .and()
                    .httpBasic();
            httpSecurity.headers().contentSecurityPolicy("default-src 'self' style-src 'self' 'unsafe-inline'");
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
            authBuilder.inMemoryAuthentication()
                    .withUser(username.trim())
                    .password(passwordEncoder().encode(cred.trim()))
                    .roles("USER");
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
    }
}