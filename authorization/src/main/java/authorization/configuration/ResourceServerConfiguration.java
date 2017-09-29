package authorization.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final String resourceId;

    private final DefaultTokenServices tokenServices;

    private final TokenStore tokenStore;

    public ResourceServerConfiguration(@Value("${security.oauth2.resource.id}")
                                               String resourceId,
                                       DefaultTokenServices tokenServices,
                                       TokenStore tokenStore) {
        this.resourceId = resourceId;
        this.tokenServices = tokenServices;
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .requestMatcher(new OAuthRequestedMatcher())
//                .authorizeRequests()
//                .antMatchers("/admin/**", "/user/**")
//                .authenticated();

        http
                .requestMatcher(new OAuthRequestedMatcher())
                .csrf().disable()
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/api/hello").access("hasAnyRole('USER')")
                .antMatchers("/api/me").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/admin").hasRole("ADMIN")
                .antMatchers("/api/registerUser").hasAuthority("ROLE_REGISTER")
                .antMatchers("/api/**").authenticated();
    }

//    private static class OAuthRequestedMatcher implements RequestMatcher {
//        public boolean matches(HttpServletRequest request) {
//            // Determine if the resource called is "/api/**"
//            String path = request.getServletPath();
//            if (path.length() >= 5) {
//                path = path.substring(0, 5);
//                return path.equals("/api/");
//            } else return false;
//        }
//    }

    private static class OAuthRequestedMatcher implements RequestMatcher {
        public boolean matches(HttpServletRequest request) {
            String auth = request.getHeader("Authorization");
            // Determine if the client request contained an OAuth Authorization
            boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
            boolean haveAccessToken = request.getParameter("access_token") != null;
            return haveOauth2Token || haveAccessToken;
        }
    }
}

