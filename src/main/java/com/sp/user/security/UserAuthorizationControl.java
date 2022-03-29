package com.sp.user.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserAuthorizationControl {

    private static final String USER_ROLE = "USER";

    public UserAuthorizationControl() {
    }

    public boolean checkAccessBasedOnRole(Authentication auth){
        if(auth.isAuthenticated()) {
            return ((User)auth.getPrincipal()).getAuthorities().contains(new SimpleGrantedAuthority(USER_ROLE));
        }
        return false;
    }
}
