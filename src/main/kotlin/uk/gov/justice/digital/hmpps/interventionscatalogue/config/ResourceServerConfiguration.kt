package uk.gov.justice.digital.hmpps.interventionscatalogue.config

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
open class ResourceServerConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.headers().frameOptions().sameOrigin().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                // Can't have CSRF protection as requires session
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/webjars/**", "/favicon.ico", "/csrf",
                        "/health", "/health/ping", "/info", "/ping", "/h2-console/**",
                        "/v2/api-docs",
                        "/swagger-ui.html", "/swagger-resources", "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security").permitAll()
                .anyRequest()
                .authenticated();
    }
}