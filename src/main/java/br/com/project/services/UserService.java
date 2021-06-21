package br.com.project.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.project.security.UserDetail;
import br.com.project.services.exceptions.AuthorizationException;

public class UserService {

    public static UserDetail authenticated() {
        try {
            return (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();            
        } catch (Exception e) {
            throw new AuthorizationException("Acesso negado");
        }
    }
}
