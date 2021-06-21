package br.com.project.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.project.security.UserDetail;

public class UserService {

    public static UserDetail authenticated() {
        try {
            return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();            
        } catch (Exception e) {
            return null;
        }
    }
}
