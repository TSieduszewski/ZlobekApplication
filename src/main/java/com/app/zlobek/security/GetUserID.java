package com.app.zlobek.security;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.core.Authentication;

import java.security.Principal;
import java.util.Map;

public class GetUserID {

    private Authentication authentication;

    public GetUserID(Authentication authentication){
        this.authentication = authentication;
    }

    public Integer get() throws Exception {
        Principal principal = (Principal) authentication.getPrincipal();

        KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) principal;

        AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();

        Map<String, Object> customClaims = accessToken.getOtherClaims();

        if (customClaims.containsKey("clientid")) {
            return Integer.parseInt(String.valueOf(customClaims.get("clientid")));
        } else {
            throw new Exception("Tu jest pies pogrzebany");
        }
    }
}
