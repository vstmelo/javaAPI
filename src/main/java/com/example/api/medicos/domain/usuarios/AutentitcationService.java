package com.example.api.medicos.domain.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutentitcationService implements UserDetailsService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuariosRepository.findByUsername(username);
    }
}
