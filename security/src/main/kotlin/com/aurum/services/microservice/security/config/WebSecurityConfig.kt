package com.aurum.services.microservice.security.config

import com.aurum.services.microservice.security.filter.SecurityFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class WebSecurityConfig {

    @Autowired
    private val securityFilter : SecurityFilter? = null

    @Autowired
    private val customAuthenticationEntryPoint : CustomAuthenticationEntryPoint? = null

    @Autowired
    fun configureGlobalSecurity(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser(DefaultUser().user).password("{noop}${DefaultUser().password}").roles("ADMIN")
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/v3/api-docs/**").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)


        http.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**")
            web.ignoring().mvcMatchers("/swagger-ui.html/**", "/configuration/**", "/swagger-resources/**", "/v3/api-docs")
        }
    }

}
