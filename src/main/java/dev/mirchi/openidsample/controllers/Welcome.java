package dev.mirchi.openidsample.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class Welcome {

    @GetMapping
    public Object getUserAuthority(Principal principal) {

        if (principal instanceof OAuth2AuthenticationToken) {
            var authToken = (OAuth2AuthenticationToken) principal;
            var userAuthority = authToken.getAuthorities().stream()
            .filter(authority -> authority.getAuthority().equals("OIDC_USER"))
            .findAny().orElse(null);
            if (null != userAuthority && userAuthority instanceof OAuth2UserAuthority) {
                var auth = (OAuth2UserAuthority) userAuthority;
                return auth.getAttributes();
            }
        }
        return "NULL";
    }
    
}
